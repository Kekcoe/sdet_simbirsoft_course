package com.globalsqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCustomerPage {

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input")
    private WebElement firstNameField;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input")
    private WebElement lastNameField;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/form/div[3]/input")
    private WebElement postCodeField;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/form/button")
    private WebElement addBtn;


    public void inputFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void inputPostCode(String postCode) {
        postCodeField.sendKeys(postCode);
    }

    public void clickAddBtn() {addBtn.click();
    }
}