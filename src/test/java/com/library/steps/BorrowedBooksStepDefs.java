package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

public class BorrowedBooksStepDefs {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBookCount;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userName) {
        loginPage.login(userName);
        BrowserUtil.waitFor(5);
        //BrowserUtil.waitForVisibility(dashBoardPage.borrowedBooksNumber, 10);

    }




    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        actualBorrowedBookCount = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookCount = " + actualBorrowedBookCount);



       /*
        actualBorrowedBookNumber = dashBoardPage.getBorrowedBooksNumber2.getAttribute("textContent");
        System.out.println("dashBoardPage.booksNumber.getText() = " + dashBoardPage.booksNumber.getText());
        System.out.println("dashBoardPage.usersNumber.getText() = " + dashBoardPage.usersNumber.getText());
        System.out.println("actualBorrowedBookNumber = " + actualBorrowedBookNumber);
        System.out.println("dashBoardPage.getModuleCount(\"Borrowed Books\") = " + dashBoardPage.getModuleCount("Borrowed Books"));

        System.out.println("-----------------------------");
        String actualBorrowedBooksNumber3 = Driver.getDriver().findElement(By.xpath("//h2[@id='borrowed_books']")).getText();
        System.out.println("actualBorrowedBooksNumber3 = " + actualBorrowedBooksNumber3);
        */


    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        String query = "select count(*) from book_borrow where is_returned = 0";
        DB_Util.runQuery(query);
        String expectedBorrowedBooksCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowedBooksCount = " + expectedBorrowedBooksCount);

        Assert.assertEquals(expectedBorrowedBooksCount, actualBorrowedBookCount);

    }

}
