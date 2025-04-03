package com.simbirsoft.taskone.page;

import com.simbirsoft.taskone.model.Customer;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BankManagerLoginPage {

    public WebDriver driver;

    private final static String CUSTOMER_TABLE_ROWS_XPATH = "//tbody/tr";
    private final static String CUSTOMER_FIRST_NAME_CELL_XPATH = "td[1]";
    private final static String CUSTOMER_DELETE_BUTTON_XPATH = "td[5]/button";
    private final static String CUSTOMER_SORT_LINK_XPATH = "//td[1]/a";
    private final static String CUSTOMER_FIRST_NAME_COLUMN_XPATH = "//tbody/tr/td[1]";

    @FindBy(css = "input[placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(css = "input[placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(css = "input[placeholder='Post Code']")
    private WebElement postCodeField;

    @FindBy(css = "button[type='submit']")
    private WebElement btnAddCustomer;

    @FindBy(css = "button[ng-click='showCust()']")
    private WebElement btnShowCustomer;


    public BankManagerLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step
    public BankManagerLoginPage inputFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    @Step
    public BankManagerLoginPage inputLastName(String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    @Step
    public BankManagerLoginPage inputPostCode(String postCode) {
        postCodeField.sendKeys(postCode);
        return this;
    }

    @Step
    public BankManagerLoginPage clickBtnAddCustomer() {
        btnAddCustomer.submit();
        return this;
    }

    @Step
    public List<Customer> fillCustomerList() {
        List<WebElement> customerList = driver.findElements(By.xpath(CUSTOMER_TABLE_ROWS_XPATH));

        return customerList.stream()
                .map(e -> new Customer(e.findElement(By.xpath(CUSTOMER_FIRST_NAME_CELL_XPATH)).getText()))
                .toList();
    }

    @Step
    public boolean removeCustomer(Customer customer) {
        List<WebElement> customerList = driver.findElements(By.xpath(CUSTOMER_TABLE_ROWS_XPATH));
        WebElement customerToRemove = customerList.stream()
                .filter(e -> customer.getName().equals(e.findElement(By.xpath(CUSTOMER_FIRST_NAME_CELL_XPATH)).getText()))
                .findFirst()
                .orElse(null);

        if (customerToRemove != null) {
            customerToRemove.findElement(By.xpath(CUSTOMER_DELETE_BUTTON_XPATH)).click();
            return true;
        }

        return false;
    }

    @Step
    public List<String> sortByFirstName() {
        driver.findElement(By.xpath(CUSTOMER_SORT_LINK_XPATH)).click();

        return driver.findElements(By.xpath(CUSTOMER_FIRST_NAME_COLUMN_XPATH)).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step
    public List<String> getReversedOrderFirstNames() {
        return fillCustomerList().stream()
                .map(Customer::getName)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    @Step
    public void clickBtnShowCustomer() {
        btnShowCustomer.click();
    }

    @Step
    public String getAlertText(){
        return driver.switchTo().alert().getText();
    }



}