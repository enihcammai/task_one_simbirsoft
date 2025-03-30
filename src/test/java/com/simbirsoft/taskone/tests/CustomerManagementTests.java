package com.simbirsoft.taskone.tests;

import com.simbirsoft.taskone.dto.Customer;
import com.simbirsoft.taskone.page.BankManagerLoginPage;
import com.simbirsoft.taskone.service.CustomerService;
import com.simbirsoft.taskone.service.PropertyService;
import com.simbirsoft.taskone.utils.CustomerUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

public class CustomerManagementTests {

    public static WebDriver driver;
    public static BankManagerLoginPage bankManagerLoginPage;
    public static CustomerService customerService;


    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", PropertyService.getInstance().getProperty("driver"));
        driver = new ChromeDriver();
        bankManagerLoginPage = new BankManagerLoginPage(driver);
        customerService = new CustomerService();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(PropertyService.getInstance().getProperty("web.url"));
    }

    @Test
    public void addCustomerTest() {
        String postCode = CustomerUtils.generatePostCode();
        String firstName = CustomerUtils.generateFirstName(postCode);
        String lastName = CustomerUtils.generateLastName(postCode);

        bankManagerLoginPage.inputFirstName(firstName);
        bankManagerLoginPage.inputLastName(lastName);
        bankManagerLoginPage.inputPostCode(postCode);
        bankManagerLoginPage.clickAddCustomerBtn();

        Alert alert = driver.switchTo().alert();
        Assertions.assertNotNull(alert.getText());
        alert.accept();
    }

    @Test
    public void showAndSortCustomerTest() {
        bankManagerLoginPage.showCustomerBtn();

        List<String> expectedList = bankManagerLoginPage.fillCustomerList().stream()
                .map(Customer::getName)
                .sorted(Comparator.reverseOrder())
                .toList();

        List<String> actualList = bankManagerLoginPage.firstNameSorter();

        Assertions.assertLinesMatch(expectedList, actualList);
    }

    @Test
    public void showAndDeleteCustomerTest() {
        bankManagerLoginPage.showCustomerBtn();
        Customer customerOnDelete = customerService.getClosestToAverageCustomer(bankManagerLoginPage.fillCustomerList());
        Assertions.assertTrue(bankManagerLoginPage.removeCustomer(customerOnDelete));
    }


    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}