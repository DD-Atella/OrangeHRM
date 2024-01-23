package com.orangehrmlive.opensource.testcases;

import com.orangehrmlive.opensource.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.orangehrmlive.opensource.pages.AddUserPage;

public class UserTest extends BaseTest {
    @Test
    public void AddUser(){
        AddUserPage.getInstance().loadAddUserPage(driver.get());
        AddUserPage.getInstance().clickOnAddUserButton(driver.get());
        AddUserPage.getInstance().addUser(driver.get());
        Assert.assertTrue(AddUserPage.getInstance().isTheUserAdded(driver.get()));
    }
}
