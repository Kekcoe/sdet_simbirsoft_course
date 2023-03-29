package com.globalsqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageManager {
    private WebDriver driver;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public ManagerPage getManagerPage() {
        ManagerPage managerPage = new ManagerPage();
        PageFactory.initElements(driver, managerPage);
        return managerPage;
    }

    public AddCustomerPage getAddCustomerPage() {
        AddCustomerPage addCustomerPage = new AddCustomerPage();
        PageFactory.initElements(driver, addCustomerPage);
        return addCustomerPage;
    }

    public ListCustomersPage getListCustomersPage() {
        ListCustomersPage listCustomersPage = new ListCustomersPage();
        PageFactory.initElements(driver, listCustomersPage);
        return listCustomersPage;
    }
}
