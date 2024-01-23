package com.orangehrmlive.opensource.testcases;

import com.orangehrmlive.opensource.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.orangehrmlive.opensource.pages.BuzzPage;

public class BuzzTest extends BaseTest {
    @Test
    public void postABuzz(){
        BuzzPage.getInstance().loadBuzz(driver.get());
        BuzzPage.getInstance().buzzClick(driver.get());
        BuzzPage.getInstance().postABuzz(driver.get());
        Assert.assertTrue(BuzzPage.getInstance().isToastMessageLoaded(driver.get()));
    }
}
