package com.globalsqa.tests;


import com.globalsqa.pages.AddCustomerPage;
import com.globalsqa.pages.ListCustomersPage;
import com.globalsqa.pages.ManagerPage;
import com.globalsqa.utils.ConfigurationProperties;
import com.globalsqa.utils.WorkWebDriver;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;

public class SmokeTests {

    static final Logger logger = LoggerFactory.getLogger(SmokeTests.class);
    static WebDriver webDriver;
    static ManagerPage managerPage;
    static AddCustomerPage addCustomerPage;
    static ListCustomersPage listCustomersPage;


    @BeforeAll
    static void setupAll() {
        logger.info("start setupAll method");
        webDriver = WorkWebDriver.getChromeDriver();
        managerPage = new ManagerPage(webDriver);
        addCustomerPage = new AddCustomerPage(webDriver);
        listCustomersPage = new ListCustomersPage(webDriver);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @Description("Create customer test")
    void createCustomerTest() {
        logger.info("start create customer test");
        webDriver.get(ConfigurationProperties.getProperty("managerpage"));
        managerPage.clickAddCustomerBtn();

        webDriver.get(ConfigurationProperties.getProperty("adcustomerspage"));
        addCustomerPage.inputFirstName(ConfigurationProperties.getProperty("firstname"));
        addCustomerPage.inputLastName(ConfigurationProperties.getProperty("lastname"));
        addCustomerPage.inputPostCode(ConfigurationProperties.getProperty("postcode"));
        addCustomerPage.clickAddBtn();

        Alert alert = webDriver.switchTo().alert();
        //todo use regular expression
        String expected = "Customer added successfully with customer id :6";
        String actual = alert.getText();
        System.out.println(alert.getText());
        Assertions.assertEquals(expected, actual, "Текст после сохранения customer не верный");
        logger.info("finish create customer test");
    }

    @Test
    @Description("Sort customer")
    void sortCustomersTest(){
        logger.info("start sortCustomersTest");
        webDriver.get(ConfigurationProperties.getProperty("managerpage"));
        managerPage.clickListCustomerBtn();
        WebElement tableCustomer = listCustomersPage.getTableCustomer();
        List<String> expectedList = getListFromTable(tableCustomer);
        Collections.sort(expectedList);

        List<String> actualList = getListFromTable(tableCustomer);
        while(!actualList.equals(expectedList)){
            logger.info("clickkk ");
            listCustomersPage.clickSort();
            actualList = getListFromTable(tableCustomer);
        }

        Assertions.assertEquals(expectedList, actualList, "Текст после сохранения customer не верный");
        logger.info("finish sortCustomersTest");
    }

    private List<String> getListFromTable(WebElement tbl){
        List<WebElement> elements = tbl.findElements(By.tagName("tr"));
        List<String> textsList = new ArrayList<>();
        elements.stream().map(WebElement::getText).forEach(textsList::add);
        System.out.println("Actual list" + textsList );
        return textsList;
    }

//    @AfterAll
//    public static void cleanUp(){
//        logger.info("cleanUp");
//        webDriver.quit();
//    }

}
