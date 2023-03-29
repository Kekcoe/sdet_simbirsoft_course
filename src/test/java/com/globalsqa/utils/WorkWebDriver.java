package com.globalsqa.utils;

import com.globalsqa.tests.SmokeTests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WorkWebDriver {

    static final Logger logger = LoggerFactory.getLogger(WorkWebDriver.class);

    public static ChromeDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriver"));
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-dev-shm-usage");
        logger.info("return new ChromeDriver");
        return  new ChromeDriver(option);
    }

}
