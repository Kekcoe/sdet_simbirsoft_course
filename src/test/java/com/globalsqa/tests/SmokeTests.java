package com.globalsqa.tests;


import com.globalsqa.pages.ManagerPage;
import com.globalsqa.utils.ConfigurationProperties;
import com.globalsqa.utils.WorkWebDriver;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SmokeTests {

    static final Logger logger = LoggerFactory.getLogger(SmokeTests.class);
    static ManagerPage managerPage;
    static WebDriver webDriver;

    @BeforeAll
    static void setupAll() {
        logger.info("start setupAll method");
        webDriver = WorkWebDriver.getChromeDriver();
        managerPage = new ManagerPage(webDriver);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @Description("Create customer")
    void createCustomerTest() {
        logger.info("start create customer test");
        webDriver.get(ConfigurationProperties.getProperty("managerpage"));
        managerPage.clickAddCustomerBtn();
    }


}
