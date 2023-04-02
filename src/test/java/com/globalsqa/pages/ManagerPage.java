package com.globalsqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagerPage {

    @FindBy(xpath = "//button[@ng-click='addCust()']")
    private WebElement addCustomerButton;

    @FindBy(xpath = "//button[@ng-click='showCust()']")
    private WebElement listCustomersBtn;

    public void clickAddCustomerBtn() {
        addCustomerButton.click();
    }

    public void clickListCustomerBtn() {
        listCustomersBtn.click();
    }
}