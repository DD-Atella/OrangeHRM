package com.orangehrmlive.opensource.testcases;

import com.orangehrmlive.opensource.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.orangehrmlive.opensource.pages.PIMPage;

public class EmployeeTest extends BaseTest {
    @Test
    public void AddEmployee(){
        PIMPage.getInstance().loadPIM(driver.get());
        PIMPage.getInstance().clickOnAddEmployeeTab(driver.get());
        PIMPage.getInstance().addEmployee(driver.get());
        Assert.assertTrue(PIMPage.getInstance().isPersonalInformationPageLoaded(driver.get()));
    }
}
