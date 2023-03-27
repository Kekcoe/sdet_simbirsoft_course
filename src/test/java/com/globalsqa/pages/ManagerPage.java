package com.globalsqa.pages;

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

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[1]/button[1]")
    private WebElement addCustomerButton;

    public void clickAddCustomerBtn() {
        addCustomerButton.click();
    }

}
