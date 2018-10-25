package net.test;

import com.test.actions.Actions;
import com.test.base.BaseTest;
import com.test.entity.Book;
import com.test.pages.Pages;
import com.test.util.Constants;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class FirstTest extends BaseTest {

    @Test
    public void openAmazonPage() {
        driver.get(Constants.BASE_URL);
    }

    @Test
    @Parameters({"searchText"})
    public void openResultPage(String searchText) {
        List<Book> searchBooks = Pages.searchResultPage().getBookSearchResult(searchText);
        Assert.assertTrue(Actions.searchActions().checkBooksResult(searchBooks), "Head First Java, 2nd Edition is NOT on the list");
    }
}
