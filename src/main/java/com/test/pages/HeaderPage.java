package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.ClassName;
import com.test.locators.ID;
import com.test.locators.Locator;

public class HeaderPage extends BasePage {

    private final Locator dropdownBox = new ID("searchDropdownBox");
    private final Locator searchField = new ID("twotabsearchtextbox");
    private final Locator searchButton = new ClassName("nav-input");

    public void setSearchOption(String dropdownOptionText) {
        click("Click on dropdown button", dropdownBox);
        selectDropDownListOptionByText("Select option " + dropdownOptionText, dropdownOptionText, dropdownBox);
    }

    public void setSearchString(String searchString) {
        type(
                "Set search string: \"" + searchString + "\" to search field",
                searchString,
                searchField
        );
    }

    public void clickSearchButton() {
        waitForElementVisibility(searchButton);
        click("Click at search button", searchButton);
    }

}
