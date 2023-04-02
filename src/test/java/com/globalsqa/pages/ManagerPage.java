package com.globalsqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagerPage {

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[1]/button[1]")
    private WebElement addCustomerButton;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[1]/button[3]")
    private WebElement listCustomersBtn;

    public void clickAddCustomerBtn() {
        addCustomerButton.click();
    }

    public void clickListCustomerBtn() {
        listCustomersBtn.click();
    }

}