package org.example.base;

import org.example.pages.DashboardPage;
import org.example.pages.ForgotPasswordPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebTest extends Base{
    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;
    private DashboardPage dashboardPage;

    @BeforeTest
    public void beforeConditions() {
        loginPage=new LoginPage(driver);
        forgotPasswordPage=new ForgotPasswordPage(driver);
        dashboardPage=new DashboardPage(driver);
    }
    @BeforeMethod
    public void launchUrl(){
        driver.get(properties.getProperty("baseUrl"));
    }

    @Test(testName = "verify forgot your password interface",description ="Password should be verified.user able to click on forgot your password.",priority = 0)
    public void forgotPasswordTest() throws InterruptedException {
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed(),"Password field should be displayed");
        loginPage.goToForgotPassword();
        Thread.sleep(5000);
    }

    @Test(testName = "password reset",description ="If the user forgot password they have to select reset password.They change their password and login with new credentials.",priority = 1)
    public void passwordResetTest()  {
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed(),"Password field should be displayed");
        loginPage.goToForgotPassword();
        String username= jsonObject.get("userName").getAsString();
        forgotPasswordPage.restPassword(username);
    }

    @Test(testName = "new login functionality",description = "user should be logged in username and new password",priority = 2)
    public void loginTest(){
        String username= jsonObject.get("userName").getAsString();
        String password = jsonObject.get("password").getAsString();
        loginPage.orangeLogin(username,password);
        dashboardPage.logout();
    }

    @Test(testName = "create user",description = "to create user",priority = 3)
    public void createUserTest() throws InterruptedException {
        String username= jsonObject.get("userName").getAsString();
        String password = jsonObject.get("password").getAsString();
        String user= jsonObject.get("user").getAsString();
        loginPage.orangeLogin(username,password);
        dashboardPage.createUser(user,password);
        Thread.sleep(5000);
        dashboardPage.logout();

    }

    @Test(testName = "Make user an admin",description = "create user and make that user admin ",priority = 4)
    public void editUserTest(){
        String username= jsonObject.get("userName").getAsString();
        String password = jsonObject.get("password").getAsString();
        String user= jsonObject.get("user").getAsString();
        loginPage.orangeLogin(username,password);
        dashboardPage.editUser(user);
        dashboardPage.logout();
    }
    @Test(testName = "Verify that new user have all permisssions as admin",description = " Check new user have all permissions as admin.",priority = 5)
    public void adminUserTest(){
        String password = jsonObject.get("password").getAsString();
        String user= jsonObject.get("user").getAsString();
        loginPage.orangeLogin(user,password);
        Assert.assertTrue(dashboardPage.getAdmin().isDisplayed(),"Admin interface should be displayed");
        dashboardPage.adminCheck();
        Assert.assertTrue(dashboardPage.getAdd().isDisplayed(),"Add user should be displayed");
        dashboardPage.logout();
    }


}
