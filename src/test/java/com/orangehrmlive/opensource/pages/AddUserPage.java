package com.orangehrmlive.opensource.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddUserPage {
    private static AddUserPage addUserPage;
    //Elements
    private final By adminLink = By.cssSelector("[href$='/web/index.php/admin/viewAdminModule']");
    private final By addUserButton =  By.xpath("//button[normalize-space()='Add']");
    private final By addUserDashboard = By.cssSelector(".oxd-text.oxd-text--h6.orangehrm-main-title");
    private final By userRole = By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)");
    private final By Options = By.cssSelector("div[role='listbox'] div");
    private final By employeeName = By.cssSelector("input[placeholder='Type for hints...']");
    private final By statusesDDL = By.xpath("//div[contains(text(),'-- Select --')]");
    private final By userNameInput = By.cssSelector("div[class='oxd-form-row'] div[class='oxd-grid-2 orangehrm-full-width-grid'] div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[class='oxd-input oxd-input--active']");
    private final By passwordInput = By.cssSelector("div[class='oxd-grid-item oxd-grid-item--gutters user-password-cell'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']");
    private final By confirmPasswordInput = By.cssSelector("div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By adminDashboard = By.cssSelector(".oxd-text.oxd-text--h5.oxd-table-filter-title");


    //Constructor
    private AddUserPage(){}
    //Methods
    public static AddUserPage getInstance(){
        if(addUserPage == null){
            addUserPage = new AddUserPage();
        }
        return addUserPage;
    }

    public void loadAddUserPage(WebDriver driver){
        SigninPage.getInstance().load(driver);
        SigninPage.getInstance().signUp(driver);
        driver.findElement(adminLink).click();
        waitUntilTheUserManagementDashboardToBeLoaded(driver);

    }

    public void waitUntilTheUserManagementDashboardToBeLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until( // Wain until the admin form to load
                ExpectedConditions.visibilityOfElementLocated(addUserButton));
    }

    public void clickOnAddUserButton(WebDriver driver){
        driver.findElement(addUserButton).click();
        waitUntilAddUserDashboardToBeLoaded(driver);
    }

    public void waitUntilAddUserDashboardToBeLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until( // Wain until the add user form to be loaded
                ExpectedConditions.visibilityOfElementLocated(addUserDashboard));
    }

    public void addUser(WebDriver driver){
        driver.findElement(userRole).click();
        List<WebElement> userRoles = driver.findElements(Options);
        for(WebElement userRole:userRoles){
            if (userRole.getText().equalsIgnoreCase("Admin")){
                userRole.click();
                break;
            }
        }
        driver.findElement(employeeName).sendKeys("S");
        waitUntilEmployeeNameToBeLoaded(driver);
        List<WebElement> employeesNames = driver.findElements(Options);
        employeesNames.get(0).click();
        driver.findElement(statusesDDL).click();
        List<WebElement> statuses = driver.findElements(Options);
        for(WebElement status: statuses){
            if (status.getText().equalsIgnoreCase("Enabled")){
                status.click();
                break;
            }
        }
        Faker faker = new Faker();
        String userName = faker.name().username();
        String password = "Test@123";
        driver.findElement(userNameInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(confirmPasswordInput).sendKeys(password);
        driver.findElement(submitButton).click();
        waitUntilTheUserFormToBeLoaded(driver);

    }

    public void waitUntilEmployeeNameToBeLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until( // Wain until the employees names to be loaded
                ExpectedConditions.invisibilityOfElementWithText(Options,"Searching...."));
    }

    public void waitUntilTheUserFormToBeLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until( // Wain until the add user form to be loaded
                ExpectedConditions.visibilityOfElementLocated(adminDashboard));
    }

    public boolean isTheUserAdded(WebDriver driver){
        return driver.findElement(adminDashboard).isDisplayed();
    }

}
