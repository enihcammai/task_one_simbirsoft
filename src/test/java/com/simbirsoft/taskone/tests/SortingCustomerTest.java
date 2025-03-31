package com.simbirsoft.taskone.tests;

import com.simbirsoft.taskone.page.BankManagerLoginPage;
import com.simbirsoft.taskone.service.PropertyService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;


public class SortingCustomerTest {

    public static WebDriver driver;
    public static BankManagerLoginPage bankManagerLoginPage;


    @BeforeAll
    public static void setup() {
        System.setProperty(PropertyService.getInstance().getProperty("driver_name"), PropertyService.getInstance().getProperty("driver_path"));
        driver = new ChromeDriver();
        bankManagerLoginPage = new BankManagerLoginPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(PropertyService.getInstance().getProperty("web.url"));
    }

    @Test
    @Epic("Web Interface")
    @Feature("Customer Management")
    @Story("Sorting customers as a bank manager")
    @DisplayName("Verify that a bank manager can successfully sort customers by first name")
    public void showAndSortCustomerTest() {
        bankManagerLoginPage.clickBtnShowCustomer();

        List<String> expectedList = bankManagerLoginPage.getReversedOrderFirstNames();
        List<String> actualList = bankManagerLoginPage.sortByFirstName();

        Assertions.assertLinesMatch(expectedList, actualList);
    }


    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
