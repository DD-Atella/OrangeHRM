package com.orangehrmlive.opensource.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class AssignImmigrationPage {
    private static AssignImmigrationPage assignImmigrationPage;
    //Elements
    private final By myInfoLink = By.cssSelector("a[href$='/web/index.php/pim/viewMyDetails']");
    private final By personalDetailsForm = By.xpath("//h6[normalize-space()='Personal Details']");
    private final By immigrationLink = By.xpath("//a[normalize-space()='Immigration']");
    private final By immigrationForm = By.xpath("//h6[normalize-space()='Assigned Immigration Records']");
    private final By uploadAttachmentButton = By.cssSelector("div[class='orangehrm-attachment'] div[class='orangehrm-horizontal-padding orangehrm-vertical-padding'] button[type='button']");
    private final By attachmentField = By.cssSelector("input[type='file']");
    private final By comment = By.cssSelector("textarea[placeholder='Type comment here']");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By toastMessage = By.cssSelector("button[type='submit']");
    String localFilePath = "C:/Users/USER/Desktop/API Test Plan.pdf";

    //Constructor
    private AssignImmigrationPage(){}
    //Methods
    public static AssignImmigrationPage getInstance(){
        if(assignImmigrationPage == null){
            assignImmigrationPage = new AssignImmigrationPage();
        }
        return assignImmigrationPage;
    }

    @Step("Load immigration page")
    public void loadImmigrationPage(WebDriver driver){
        SigninPage.getInstance().load(driver);
        SigninPage.getInstance().signUp(driver);
        driver.findElement(myInfoLink).click();
        waitUntilPersonalDetailsFormToBeLoaded(driver);
    }

    public void waitUntilPersonalDetailsFormToBeLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(personalDetailsForm)
        );
    }

    @Step("The user should be able to click on the immigration form")
    public void clickOnImmigrationForm(WebDriver driver){
        driver.findElement(immigrationLink).click();
        waitUntilImmigrationFormToBeLoaded(driver);
    }

    public void waitUntilImmigrationFormToBeLoaded(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(immigrationForm));
    }

    public void uploadAttachment(WebDriver driver){
        driver.findElement(uploadAttachmentButton).click();
        WebElement fileInput = driver.findElement(attachmentField);
        String filePath = localFilePath;
        fileInput.sendKeys(filePath);
    }


    @Step("The user should be able to upload the attachment")
    public void uploadDynamicAttachment(WebDriver driver){
        driver.findElement(uploadAttachmentButton).click();
        File file = new File("src/main/resources/docs/Mohammad Alawneh.pdf");
        WebElement fileInput = driver.findElement(attachmentField);
        String absoluteFilePath = file.getAbsolutePath();
        fileInput.sendKeys(absoluteFilePath);
    }

    @Step("The user should be able to write a comment")
    public void writeComment(WebDriver driver){
        driver.findElement(comment).sendKeys("Test By Alawneh");
        driver.findElement(saveButton).click();
    }

    public boolean isTheFileUploadedSuccessfully(WebDriver driver){
        waitUntilTheToastMessageToAppear(driver);
        return driver.findElement(toastMessage).isDisplayed();
    }

    public void waitUntilTheToastMessageToAppear(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(toastMessage)
        );
    }
}
