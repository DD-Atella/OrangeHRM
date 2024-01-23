package com.orangehrmlive.opensource.testcases;

import com.orangehrmlive.opensource.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.orangehrmlive.opensource.pages.LeavePage;

public class LeaveTest extends BaseTest {
    @Test
    public void applyForLeave() throws InterruptedException {
        LeavePage.getInstance().loadLeavePage(driver.get());
        LeavePage.getInstance().fillLeaveForm(driver.get());
        LeavePage.getInstance().confirmLeave(driver.get());
        Assert.assertTrue(LeavePage.getInstance().isTheLeaveSubmittedSuccessfully(driver.get()));
    }
}
