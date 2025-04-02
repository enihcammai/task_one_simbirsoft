package com.simbirsoft.taskone.tests;

import com.simbirsoft.taskone.page.BankManagerLoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import java.util.List;


public class SortingCustomerTest extends BaseTest {

    public static BankManagerLoginPage bankManagerLoginPage;

    @BeforeAll
    public static void setup() {
        init();
        bankManagerLoginPage = new BankManagerLoginPage(getDriver());
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
}
