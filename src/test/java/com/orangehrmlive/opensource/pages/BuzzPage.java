package com.orangehrmlive.opensource.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BuzzPage {
    private static BuzzPage buzzPage;
    //Elements
    private final By buzzLink = By.cssSelector("[href='/web/index.php/buzz/viewBuzz']");
    private final By buzzComment = By.cssSelector("textarea[class='oxd-buzz-post-input']");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By successToast = By.cssSelector("div[class='oxd-toast-content oxd-toast-content--success']");
    //Constructor
    private BuzzPage(){}
    public static BuzzPage getInstance(){
        if(buzzPage == null){
            buzzPage = new BuzzPage();
        }
        return buzzPage;
    }
    //Methods
    public void loadBuzz(WebDriver driver){
        SigninPage.getInstance().load(driver);
        SigninPage.getInstance().signUp(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz");
        waitLoadBuzz(driver);
    }
    public void waitLoadBuzz(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                ExpectedConditions.visibilityOfElementLocated(buzzLink));
    }

    public void buzzLoadForm(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                ExpectedConditions.visibilityOfElementLocated(submitButton));
    }

    public void loadToastMessage(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                ExpectedConditions.visibilityOfElementLocated(successToast));
    }

    public void buzzClick(WebDriver driver){
        driver.findElement(By.cssSelector("[href='/web/index.php/buzz/viewBuzz']")).click();
        buzzLoadForm(driver);
    }

    public void postABuzz(WebDriver driver){
        driver.findElement(buzzComment).sendKeys("Test By Alawneh");
        driver.findElement(submitButton).click();
        loadToastMessage(driver);
    }

    public boolean isToastMessageLoaded(WebDriver driver){
        return driver.findElement(successToast).isDisplayed();
    }

}
