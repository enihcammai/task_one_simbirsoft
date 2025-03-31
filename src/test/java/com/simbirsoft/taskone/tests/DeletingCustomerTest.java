package com.simbirsoft.taskone.tests;

import com.simbirsoft.taskone.page.BankManagerLoginPage;
import com.simbirsoft.taskone.model.Customer;
import com.simbirsoft.taskone.service.CustomerService;
import com.simbirsoft.taskone.service.PropertyService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DeletingCustomerTest {

    public static WebDriver driver;
    public static BankManagerLoginPage bankManagerLoginPage;
    public static CustomerService customerService;


    @BeforeAll
    public static void setup() {
        System.setProperty(PropertyService.getInstance().getProperty("driver_name"), PropertyService.getInstance().getProperty("driver_path"));
        driver = new ChromeDriver();
        bankManagerLoginPage = new BankManagerLoginPage(driver);
        customerService = new CustomerService();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(PropertyService.getInstance().getProperty("web.url"));
    }


    @Test
    @Epic("Web Interface")
    @Feature("Customer Management")
    @Story("Deleting a customer as a bank manager")
    @DisplayName("Verify that a bank manager can view and delete a customer")
    public void showAndDeleteCustomerTest() {
        bankManagerLoginPage.clickBtnShowCustomer();
        Customer customerOnDelete = customerService.getClosestToAverageCustomer(bankManagerLoginPage.fillCustomerList());
        Assertions.assertTrue(bankManagerLoginPage.removeCustomer(customerOnDelete));
    }


    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
