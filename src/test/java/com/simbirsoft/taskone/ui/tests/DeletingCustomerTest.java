package com.simbirsoft.taskone.ui.tests;

import com.simbirsoft.taskone.ui.page.BankManagerLoginPage;
import com.simbirsoft.taskone.ui.model.Customer;
import com.simbirsoft.taskone.ui.service.CustomerService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

public class DeletingCustomerTest extends BaseTest {

    public static BankManagerLoginPage bankManagerLoginPage;
    public static CustomerService customerService;

    @BeforeAll
    public static void setup() {
        init();
        bankManagerLoginPage = new BankManagerLoginPage(getDriver());
        customerService = new CustomerService();
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
}
