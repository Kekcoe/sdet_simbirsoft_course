package com.globalsqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListCustomersPage {

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/table/thead/tr/td[1]/a")
    private WebElement firstNameColumn;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody")
    private WebElement tableCustomer;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody")
    private WebElement accountNumber;

    @FindBy(xpath ="/html/body/div/div/div[2]/div/div[2]/div/form/div/div/input")
    private WebElement searchField;

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
