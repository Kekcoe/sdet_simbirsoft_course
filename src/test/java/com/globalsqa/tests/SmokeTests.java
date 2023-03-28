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
    private static WebDriver webDriver;
    private static ManagerPage managerPage;
    private static AddCustomerPage addCustomerPage;
    private static ListCustomersPage listCustomersPage;


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
    @Description("Case 1: Создание клиента (Customer)")
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
    @Description("Case 2: Сортировка клиентов по имени (FirstName)")
    void sortCustomersTest() {
        logger.info("start sortCustomersTest");
        webDriver.get(ConfigurationProperties.getProperty("managerpage"));
        managerPage.clickListCustomerBtn();

        WebElement tableCustomer = listCustomersPage.getTableCustomer();
        List<String> expectedList = getListFromTable(tableCustomer);
        Collections.sort(expectedList);

        List<String> actualList = getListFromTable(tableCustomer);
        while (!actualList.equals(expectedList)) {
            listCustomersPage.clickSort();
            actualList = getListFromTable(tableCustomer);
        }

        Assertions.assertEquals(expectedList, actualList, "Таблица не отсортирована");
        logger.info("finish sortCustomersTest");
    }

    @Test
    @Description("Case 3: Поиск клиента")
    void findCustomerTest(){
        logger.info("start findCustomerTest");
        webDriver.get(ConfigurationProperties.getProperty("listcustomerspage"));

        String expectedFirstName = ConfigurationProperties.getProperty("searchFirstName");
        String expectedAccountNumber= ConfigurationProperties.getProperty("accountnumber");
        listCustomersPage.inputSearchParam(expectedFirstName);

        WebElement tableCustomer = listCustomersPage.getTableCustomer();
        List<WebElement> rowsTableCustomer = tableCustomer.findElements(By.tagName("tr"));
        List<WebElement> cols = rowsTableCustomer.get(0).findElements(By.tagName("td"));
        String actualFirstName = cols.get(0).getText();
        String actualAccountNumber = cols.get(3).getText();

        Assertions.assertEquals(1, rowsTableCustomer.size(), "Поиск вернул более одной строки");
        Assertions.assertEquals(expectedFirstName, actualFirstName, "Строка не найдена");
        Assertions.assertEquals(expectedAccountNumber, actualAccountNumber, "Account number не найден для " +
                "искомого параметра FirstName");
    }

    private List<String> getListFromTable(WebElement tbl) {
        List<WebElement> elements = tbl.findElements(By.tagName("tr"));
        List<String> textsList = new ArrayList<>();
        elements.stream().map(WebElement::getText).forEach(textsList::add);
        System.out.println("Actual list" + textsList);
        return textsList;
    }

    @AfterAll
    public static void cleanUp() {
        logger.info("cleanUp");
        webDriver.quit();
    }

}