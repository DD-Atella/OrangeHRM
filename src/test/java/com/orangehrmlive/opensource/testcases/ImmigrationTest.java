package com.orangehrmlive.opensource.testcases;

import com.orangehrmlive.opensource.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.orangehrmlive.opensource.pages.AssignImmigrationPage;

public class ImmigrationTest extends BaseTest {

    @Test
    public void assignImmigration() {
        AssignImmigrationPage.getInstance().loadImmigrationPage(driver.get());
        AssignImmigrationPage.getInstance().clickOnImmigrationForm(driver.get());
        AssignImmigrationPage.getInstance().uploadDynamicAttachment(driver.get());
        AssignImmigrationPage.getInstance().writeComment(driver.get());
        Assert.assertTrue(AssignImmigrationPage.getInstance().isTheFileUploadedSuccessfully(driver.get()));
    }
}
