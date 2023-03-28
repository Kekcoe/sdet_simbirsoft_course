package com.globalsqa.pages;

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

    public WebElement getFirstNameColumn() {
        return firstNameColumn;
    }

    public void clickSort() {
        firstNameColumn.click();
    }
}
