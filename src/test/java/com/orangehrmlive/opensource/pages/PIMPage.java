package com.orangehrmlive.opensource.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMPage {
    private static PIMPage pimPage;
    //Elements
    private final By pimLink = By.cssSelector("[href='/web/index.php/pim/viewPimModule']");
    private final By pimDashboard = By.cssSelector(".oxd-topbar-header-title");
    private final By addEmployee = By.xpath("//a[normalize-space()='Add Employee']");
    private final By addEmployeeForm = By.cssSelector(".oxd-text.oxd-text--h6.orangehrm-main-title");
    private final By firstName = By.cssSelector("input[placeholder='First Name']");
    private final By middleName= By.cssSelector("input[placeholder='Middle Name']");
    private final By lastName = By.cssSelector("input[placeholder='Last Name']");
    private final By loginDetailsSwitch = By.cssSelector(".oxd-switch-input.oxd-switch-input--active.--label-right");
    private final By userNameForm = By.xpath("//label[normalize-space()='Username']");
    private final By userName = By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)");
    private final By password = By.cssSelector("div[class='oxd-grid-item oxd-grid-item--gutters user-password-cell'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']");
    private final By confirmPassword = By.cssSelector("div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By personalDetailsForm = By.xpath("//h6[normalize-space()='Personal Details']");

    //Constructor
    private PIMPage(){}
    //Methods
    public static PIMPage getInstance(){
        if(pimPage == null){
            pimPage = new PIMPage();
        }
        return pimPage;
    }

    public void loadPIM(WebDriver driver){
        SigninPage.getInstance().load(driver);
        SigninPage.getInstance().signUp(driver);
        driver.findElement(pimLink).click();
        waitUntilPIMDashboardToBeLoaded(driver);
    }

    public void waitUntilPIMDashboardToBeLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(pimDashboard));
    }

    public void clickOnAddEmployeeTab(WebDriver driver){
        driver.findElement(addEmployee).click();
        waitUntilAddEmployeeFormToBeLoaded(driver);
    }

    public void waitUntilAddEmployeeFormToBeLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(addEmployeeForm));
    }

    public void addEmployee(WebDriver driver){
        Faker faker = new Faker();
        driver.findElement(firstName).sendKeys(faker.name().firstName());
        driver.findElement(middleName).sendKeys(faker.name().username());
        driver.findElement(lastName).sendKeys(faker.name().lastName());
        driver.findElement(loginDetailsSwitch).click();
        waitUntilUserNameFormToBeLoaded(driver);
        String userNameInput = faker.name().username();
        String passwordInput = "Test@123";
        driver.findElement(userName).sendKeys(userNameInput);
        driver.findElement(password).sendKeys(passwordInput);
        driver.findElement(confirmPassword).sendKeys(passwordInput);
        driver.findElement(saveButton).click();
        waitUntilThePersonalDetailsFormToBeLoaded(driver);
    }

    public void waitUntilUserNameFormToBeLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                ExpectedConditions.visibilityOfElementLocated(userNameForm));
    }

    public void waitUntilThePersonalDetailsFormToBeLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(7)).until(
                ExpectedConditions.visibilityOfElementLocated(personalDetailsForm));
    }
    public boolean isPersonalInformationPageLoaded(WebDriver driver){
        return driver.findElement(personalDetailsForm).isDisplayed();
    }
}
