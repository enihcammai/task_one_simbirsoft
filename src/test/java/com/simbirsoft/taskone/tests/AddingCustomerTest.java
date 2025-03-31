package com.simbirsoft.taskone.tests;

import com.simbirsoft.taskone.page.BankManagerLoginPage;
import com.simbirsoft.taskone.service.PropertyService;
import com.simbirsoft.taskone.utils.CustomerUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class AddingCustomerTest {

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
    @Story("Adding a new customer as a bank manager")
    @DisplayName("Verify that a bank manager can successfully add a new customer")
    public void addCustomerTest() {
        String postCode = CustomerUtils.generatePostCode();
        String firstName = CustomerUtils.generateFirstName(postCode);
        String lastName = CustomerUtils.generateLastName(postCode);

        bankManagerLoginPage.inputFirstName(firstName);
        bankManagerLoginPage.inputLastName(lastName);
        bankManagerLoginPage.inputPostCode(postCode);
        bankManagerLoginPage.clickBtnAddCustomer();

        Alert alert = driver.switchTo().alert();
        Assertions.assertNotNull(alert.getText());
        alert.accept();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}