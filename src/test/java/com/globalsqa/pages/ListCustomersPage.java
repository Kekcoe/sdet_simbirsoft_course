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

    public WebElement getTableCustomer() {
        return tableCustomer;
    }

    public void clickSort() {
        firstNameColumn.click();
    }
}
