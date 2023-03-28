package com.globalsqa.tests;


import com.globalsqa.pages.AddCustomerPage;
import com.globalsqa.pages.ManagerPage;
import com.globalsqa.utils.ConfigurationProperties;
import com.globalsqa.utils.WorkWebDriver;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SmokeTests {

    static final Logger logger = LoggerFactory.getLogger(SmokeTests.class);
    static WebDriver webDriver;
    static ManagerPage managerPage;
    static AddCustomerPage addCustomerPage;


    @BeforeAll
    static void setupAll() {
        logger.info("start setupAll method");
        webDriver = WorkWebDriver.getChromeDriver();
        managerPage = new ManagerPage(webDriver);
        addCustomerPage = new AddCustomerPage(webDriver);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @Description("Create customer")
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
        String expected = "Customer added successfully with customer id :6";
        String actual = alert.getText();
        System.out.println(alert.getText());
        Assertions.assertEquals(expected, actual);
    }



}
