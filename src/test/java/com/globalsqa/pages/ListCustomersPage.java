package com.globalsqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ListCustomersPage {

    WebDriver driver;

    public ListCustomersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[contains(text(), 'First Name')]")
    private WebElement firstNameColumn;

    @FindBy(css = "table.table-bordered.table-striped tbody")
    private WebElement tableCustomer;

    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    private WebElement searchField;

    @Step("Input search param")
    public void inputSearchParam(String searchParam) {
        searchField.sendKeys(searchParam);
    }

    public WebElement getTableCustomer() {
        return tableCustomer;
    }

    @Step("Click sort")
    public void clickSort() {
        firstNameColumn.click();
    }
}