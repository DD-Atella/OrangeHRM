package com.orangehrmlive.opensource.pages;

import com.orangehrmlive.opensource.utils.ConfigUtils;
import io.opentelemetry.api.internal.ConfigUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SigninPage {
    private static SigninPage signinPage;
    //Elements
    //private final By userText = By.cssSelector("div[class='orangehrm-login-error'] p:nth-child(1)");
    //private final By userPassword = By.cssSelector("div[class='orangehrm-login-error'] p:nth-child(2)");
    private final By userName = By.cssSelector("input[placeholder='Username']");
    private final By password = By.cssSelector("input[placeholder='Password']");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By dashboard = By.cssSelector(".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");
    private final By userDDLOptions = By.cssSelector(".oxd-userdropdown-name");
    private final By logoutOption = By.xpath("//a[normalize-space()='Logout']");
    private final By loginPageTitle = By.cssSelector(".oxd-text.oxd-text--h5.orangehrm-login-title");


    //Methods
    private SigninPage(){}
    public static SigninPage getInstance(){
        if(signinPage == null){
            signinPage = new SigninPage();
        }
        return signinPage;
    }

    @Step("Load the website")
    public void load(WebDriver driver){
        driver.get(ConfigUtils.getInstance().getBaseURL() + "/web/index.php/auth/login");
    }

    @Step("Sign Up")
    public void signUp(WebDriver driver){
        driver.findElement(userName).sendKeys(ConfigUtils.getInstance().getFirstName());
        driver.findElement(password).sendKeys(ConfigUtils.getInstance().getPassword());
        driver.findElement(loginButton).click();
    }

    @Step("Checking if the dashboard loading successfully")
    public boolean isDashboardDisplayed(WebDriver driver){
        return driver.findElement(dashboard).isDisplayed();
    }

    public void waitUntilTheUserOptionsAreLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                ExpectedConditions.visibilityOfElementLocated(logoutOption));
    }

    public void waitUntilTheDashboardIsLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(dashboard));
    }

    public void waitUntilTheLoginPageIsLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPageTitle));
    }

    public void logout(WebDriver driver){
        load(driver);
        signUp(driver);
        waitUntilTheDashboardIsLoaded(driver);
        driver.findElement(userDDLOptions).click();
        waitUntilTheUserOptionsAreLoaded(driver);
        driver.findElement(logoutOption).click();
    }

    public boolean isTheLoginPageLoaded(WebDriver driver){
        waitUntilTheLoginPageIsLoaded(driver);
        return driver.findElement(loginPageTitle).isDisplayed();
    }
}
