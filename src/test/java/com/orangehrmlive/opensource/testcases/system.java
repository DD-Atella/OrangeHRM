package com.orangehrmlive.opensource.testcases;

import com.orangehrmlive.opensource.base.BaseTest;
import com.orangehrmlive.opensource.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class system extends BaseTest {

    @Test (description = "The user should be able to signIn")//Sign in Case
    public void shouldBeAbleToSignIn(){
        SigninPage.getInstance().load(driver.get());
        SigninPage.getInstance().signUp(driver.get());
        boolean dashboardMessage = SigninPage.getInstance().isDashboardDisplayed(driver.get());
        Assert.assertTrue(dashboardMessage);
    }

    @Test (description = "The admin should be able to add employee")
    public void AddEmployee(){ //Add Employee Case
        PIMPage.getInstance().loadPIM(driver.get());
        PIMPage.getInstance().clickOnAddEmployeeTab(driver.get());
        PIMPage.getInstance().addEmployee(driver.get());
        Assert.assertTrue(PIMPage.getInstance().isPersonalInformationPageLoaded(driver.get()));
    }

    @Test (description = "The Admin should be able to add user")
    public void AddUser(){ // Add User Case
        AddUserPage.getInstance().loadAddUserPage(driver.get());
        AddUserPage.getInstance().clickOnAddUserButton(driver.get());
        AddUserPage.getInstance().addUser(driver.get());
        Assert.assertTrue(AddUserPage.getInstance().isTheUserAdded(driver.get()));
    }

    @Test (description = "The Admin should be able to apply leave")
    public void applyForLeave() { //Apply for leave case
        LeavePage.getInstance().loadLeavePage(driver.get());
        LeavePage.getInstance().fillLeaveForm(driver.get());
        LeavePage.getInstance().confirmLeave(driver.get());
        Assert.assertTrue(LeavePage.getInstance().isTheLeaveSubmittedSuccessfully(driver.get()));
    }

    @Test (description = "The Admin should be able to post a Buzz")
    public void postABuzz(){ //post a Buzz case
        BuzzPage.getInstance().loadBuzz(driver.get());
        BuzzPage.getInstance().buzzClick(driver.get());
        BuzzPage.getInstance().postABuzz(driver.get());
        boolean isToastLoaded = BuzzPage.getInstance().isToastMessageLoaded(driver.get());
        Assert.assertTrue(isToastLoaded);
    }

    @Test (description = "The Admin should be able upload a file")
    public void assignImmigration(){ // Immigration and upload file case
        AssignImmigrationPage.getInstance().loadImmigrationPage(driver.get());
        AssignImmigrationPage.getInstance().clickOnImmigrationForm(driver.get());
        AssignImmigrationPage.getInstance().uploadDynamicAttachment(driver.get());
        AssignImmigrationPage.getInstance().writeComment(driver.get());
        Assert.assertTrue(AssignImmigrationPage.getInstance().isTheFileUploadedSuccessfully(driver.get()));
    }

    @Test (description = "The user should be able to logout")// Logout Case
    public void LogoutAction(){
        SigninPage.getInstance().logout(driver.get());
        boolean sigin = SigninPage.getInstance().isTheLoginPageLoaded(driver.get());
        Assert.assertTrue(sigin);
    }
}
