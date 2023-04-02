package com.globalsqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

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

    public List<WebElement> getRowsTableCustomer(WebElement tableCustomer){
        return tableCustomer.findElements(By.tagName("tr"));
    }

    public List<WebElement> getColumnsTableByIndex(List<WebElement> rowsTableCustomer, int index){
        return rowsTableCustomer.get(index).findElements(By.tagName("td"));
    }

    public List<String> getListFromTable(WebElement tbl) {
        List<WebElement> elements = tbl.findElements(By.tagName("tr"));
        List<String> textsList = new ArrayList<>();
        elements.stream().map(WebElement::getText).forEach(textsList::add);
        return textsList;
    }

}