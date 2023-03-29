package com.globalsqa.tests;


import com.globalsqa.pages.AddCustomerPage;
import com.globalsqa.pages.ListCustomersPage;
import com.globalsqa.pages.ManagerPage;
import com.globalsqa.pages.PageManager;
import com.globalsqa.utils.ConfigurationProperties;
import com.globalsqa.utils.WorkWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;

public class SmokeTests {

    static final Logger logger = LoggerFactory.getLogger(SmokeTests.class);
    private WebDriver webDriver;
    private PageManager pages;

    @BeforeAll
    static void setupAll() {
        logger.info("start setupAll method");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        webDriver = WorkWebDriver.getChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        pages = new PageManager(webDriver);
    }

    @AfterEach
    public void cleanUp() {
        logger.info("cleanUp");
        webDriver.quit();
    }

    @Test
    @Description("Case 1: Создание клиента (Customer)")
    void createCustomerTest() {
        logger.info("start create customer test");
        ManagerPage managerPage = pages.getManagerPage();
        AddCustomerPage addCustomerPage = pages.getAddCustomerPage();
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
        alert.accept();
        Assertions.assertEquals(expected, actual, "Текст после сохранения customer не верный");
        logger.info("finish create customer test");
    }

    @Test
    @Description("Case 2: Сортировка клиентов по имени (FirstName)")
    void sortCustomersTest() {
        logger.info("start sortCustomersTest");
        webDriver.get(ConfigurationProperties.getProperty("managerpage"));

        ManagerPage managerPage = pages.getManagerPage();
        managerPage.clickListCustomerBtn();
        webDriver.get(ConfigurationProperties.getProperty("listcustomerspage"));
        ListCustomersPage listCustomersPage = pages.getListCustomersPage();
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
    void findCustomerTest() {
        logger.info("start findCustomerTest");
        ListCustomersPage listCustomersPage = pages.getListCustomersPage();
        webDriver.get(ConfigurationProperties.getProperty("listcustomerspage"));

        String expectedFirstName = ConfigurationProperties.getProperty("searchFirstName");
        String expectedAccountNumber = ConfigurationProperties.getProperty("accountnumber");
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


}