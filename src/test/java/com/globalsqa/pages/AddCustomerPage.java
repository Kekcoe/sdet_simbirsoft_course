package com.globalsqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

    WebDriver driver;

    public AddCustomerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath="//input[@placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(xpath="//input[@placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(xpath="//input[@placeholder='Post Code']")
    private WebElement postCodeField;

    @FindBy(css = "button[type='submit']")
    private WebElement addBtn;

    @Step("Input first name")
    public void inputFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    @Step("Input last name")
    public void inputLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    @Step("input post code")
    public void inputPostCode(String postCode) {
        postCodeField.sendKeys(postCode);
    }

    @Step("Click button to add new customer")
    public void clickAddBtn() {addBtn.click();
    }
}