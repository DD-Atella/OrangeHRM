package com.orangehrmlive.opensource.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LeavePage {
    private static LeavePage leavePage;
    //Elements
    private final By leaveLinkTab = By.cssSelector("[href$='/web/index.php/leave/viewLeaveModule']");
    private final By assignLeaveTab = By.xpath("//a[normalize-space()='Assign Leave']");
    private final By leaveDashboard = By.cssSelector(".oxd-text.oxd-text--h6.orangehrm-main-title");
    private final By employeeName = By.cssSelector("input[placeholder='Type for hints...']");
    private final By options = By.cssSelector("div[role='listbox'] div");
    private final By leaveType = By.cssSelector(".oxd-select-text-input");
    private final By fromDate = By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > i:nth-child(2)");
    private final By todayDate = By.cssSelector(".oxd-calendar-date.--selected.--today");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By warningPopup = By.cssSelector("div[role='document']");
    private final By confirmPopupButton = By.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-button-margin']");
    private final By toastMessage = By.id("oxd-toaster_1");
    String filePath = "C:/Users/USER/Desktop/API Test Plan.pdf";



    //Constructor
    private LeavePage(){}
    //Methods
    public static LeavePage getInstance(){
        if(leavePage == null){
            leavePage = new LeavePage();
        }
        return leavePage;
    }
 public void loadLeavePage(WebDriver driver){
     SigninPage.getInstance().load(driver);
     SigninPage.getInstance().signUp(driver);
     driver.findElement(leaveLinkTab).click();
     driver.findElement(assignLeaveTab).click();
     waitUntilLeaveFormToBeLoaded(driver);
 }

 public void waitUntilLeaveFormToBeLoaded(WebDriver driver){
     new WebDriverWait(driver, Duration.ofSeconds(5)).until(
             ExpectedConditions.visibilityOfElementLocated(leaveDashboard));
 }

 public void fillLeaveForm(WebDriver driver){
     driver.findElement(employeeName).sendKeys("M");
     waitUntilEmployeeNamesToBeLoaded(driver);
     List<WebElement> employeesNames = driver.findElements(options);
     employeesNames.get(0).click();// click on the first name on the list
     driver.findElement(leaveType).click();
     List<WebElement> leaveTypeOptions = driver.findElements(options);
     leaveTypeOptions.get(0).click();//click on the leave type
     driver.findElement(fromDate).click();
     driver.findElement(todayDate).click();
     driver.findElement(By.cssSelector(".oxd-select-text.oxd-select-text--active")).click();
     List<WebElement> leaveTypes = driver.findElements(options);
     leaveTypes.get(1).click();
     driver.findElement(submitButton).click();
 }
 public void waitUntilEmployeeNamesToBeLoaded(WebDriver driver){
     new WebDriverWait(driver, Duration.ofSeconds(3)).until( // Wain until the employees names to be loaded
             ExpectedConditions.invisibilityOfElementWithText(options,"Searching...."));
 }

 public void confirmLeave(WebDriver driver){
     waitUntilPopupToAppear(driver);
     driver.findElement(confirmPopupButton).click();
 }

 public void waitUntilPopupToAppear(WebDriver driver){
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
     wait.until(ExpectedConditions.visibilityOfElementLocated(warningPopup));
 }

 public boolean isTheLeaveSubmittedSuccessfully(WebDriver driver){
        waitUntilToastMessageToAppear(driver);
        return driver.findElement(toastMessage).isDisplayed();
 }

 public void waitUntilToastMessageToAppear(WebDriver driver){
     new WebDriverWait(driver, Duration.ofSeconds(5)).until(
             ExpectedConditions.visibilityOfElementLocated(toastMessage));
 }



}
