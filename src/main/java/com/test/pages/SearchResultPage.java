package com.test.pages;

import com.test.base.BasePage;
import com.test.entity.Book;
import com.test.locators.ID;
import com.test.locators.Locator;
import com.test.locators.XPath;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {
    private final Locator resultItems = new XPath("//li[contains(@class, 's-result-item') and contains(@class, 'celwidget')]");

    public void getSearchResult(String searchString, String searchOptionString) {
        HeaderPage page = Pages.headerPage();

        page.setSearchOption(searchOptionString);
        page.setSearchString(searchString);
        page.clickSearchButton();
    }

    public List<Book> getBookSearchResult(String searchString) {
        getSearchResult(searchString, "Books");

        List<Book> books = new ArrayList<>();
        int resultItemsSize = getElements(resultItems).size();
        for (int i = 0; i < resultItemsSize; i++) {
            Locator resultBlockLocator = new XPath("//li[@id=\"result_" + i + "\"]");
            String dataAsin = getElement(resultBlockLocator).getAttribute("data-asin");

            Locator nameLocator = new XPath("//li[@id=\"result_" + i + "\"]//h2");
            Locator authorLocator = new XPath("//li[@id=\"result_" + i + "\"]//div[@class='a-row a-spacing-small']//div[contains(.,'by')]");
            Locator priceLocator = new XPath("//li[@id=\"result_" + i + "\"]//div[@class='a-column a-span7']//span[@class='sx-price sx-price-large']");
            Locator bestSellerLocator = new ID("BESTSELLER_" + dataAsin);

            String name = getElement(nameLocator).getText();
            String author = getElement(authorLocator).getText().replace("by ", "");
            double price = (double) Integer.valueOf(getElement(priceLocator).getText().replace(" ", "").replace("$", "")) / 100;
            String ratio = setRatio(dataAsin);
            boolean bestSeller = isElementPresent(bestSellerLocator);

            Book book = new Book(name, author, price, ratio, bestSeller);
            Book.logBook(book);
            books.add(book);
        }

        return books;
    }

    private String setRatio(String dataAsin) {
        Locator ratioBlock = new XPath("//span[@name='" + dataAsin + "']");
        Locator ratio = new XPath("//span[@name='" + dataAsin + "']//i[1]//span");
        String result = "No ratio";

        if (isElementPresent(ratioBlock)) {
            result = executeJSWithReturn("return arguments[0].textContent;", getElement(ratio));
            result = result.replace(" out of 5 stars", "");
        }
        return result;
    }
}
