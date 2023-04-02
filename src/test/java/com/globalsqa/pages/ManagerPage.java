package com.globalsqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage {

    WebDriver driver;

    public ManagerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@ng-click='addCust()']")
    private WebElement addCustomerButton;

    @FindBy(xpath = "//button[@ng-click='showCust()']")
    private WebElement listCustomersBtn;

    @Step("Click button to add customer")
    public void clickAddCustomerBtn() {
        addCustomerButton.click();
    }

    @Step("Click button to get page with table of custoers")
    public void clickListCustomerBtn() {
        listCustomersBtn.click();
    }
}