package com.orangehrmlive.opensource.base;

import com.orangehrmlive.opensource.factory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.*;

public class BaseTest {
    //protected WebDriver driver; //protected means it will be available to the classes on the same package
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    @BeforeMethod // Before every testcase
    public void setup(){
        WebDriver driver = new DriverFactory().initializeDriver();
        this.driver.set(driver);
    }
    @AfterMethod // After any testcase
    public void tearDown(ITestResult result){
        String testCaseName = result.getMethod().getMethodName();
        File destFile = new File("target" + File.separator +"screenshots" + File.separator+testCaseName + ".png");
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenShot(destFile);
        }
        driver.get().quit();
    }

    public void takeScreenShot(File destFile){
        File file = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(file, destFile);
            InputStream is = new FileInputStream(destFile);
            Allure.addAttachment("screenshot", is);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
