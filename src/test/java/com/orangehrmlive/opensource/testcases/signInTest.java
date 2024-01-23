package com.orangehrmlive.opensource.testcases;

import com.orangehrmlive.opensource.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.orangehrmlive.opensource.pages.SigninPage;


public class signInTest extends BaseTest {
    @Test //Sign in Case
    public void shouldBeAbleToSignIn(){
        SigninPage.getInstance().load(driver.get());
        SigninPage.getInstance().signUp(driver.get());
        boolean dashboardMessage = SigninPage.getInstance().isDashboardDisplayed(driver.get());
        Assert.assertTrue(dashboardMessage);
    }

    @Test // Logout Case
    public void LogoutAction(){
        SigninPage.getInstance().logout(driver.get());
        boolean sigIn = SigninPage.getInstance().isTheLoginPageLoaded(driver.get());
        Assert.assertTrue(sigIn);
    }
}
