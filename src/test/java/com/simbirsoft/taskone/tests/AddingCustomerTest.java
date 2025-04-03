package com.simbirsoft.taskone.tests;

import com.simbirsoft.taskone.page.BankManagerLoginPage;
import com.simbirsoft.taskone.utils.CustomerUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

public class AddingCustomerTest extends BaseTest {

    public static BankManagerLoginPage bankManagerLoginPage;

    @BeforeAll
    public static void setup() {
        init();
        bankManagerLoginPage = new BankManagerLoginPage(getDriver());
    }

    @Test
    @Epic("Web Interface")
    @Feature("Customer Management")
    @Story("Adding a new customer as a bank manager")
    @DisplayName("Verify that a bank manager can successfully add a new customer")
    public void addCustomerTest() {
        String postCode = CustomerUtils.generatePostCode();
        String actualAlertText = bankManagerLoginPage
                .inputFirstName(CustomerUtils.generateFirstName(postCode))
                .inputLastName(CustomerUtils.generateLastName(postCode))
                .inputPostCode(postCode)
                .clickBtnAddCustomer()
                .getAlertText();

        Assertions.assertNotNull(actualAlertText);
        getDriver().switchTo().alert().accept();
    }

}