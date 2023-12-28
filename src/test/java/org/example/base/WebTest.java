package org.example.base;

import com.aventstack.extentreports.Status;
import org.example.pages.DashboardPage;
import org.example.pages.ForgotPasswordPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WebTest extends Base{
    private static final Logger logger = LogManager.getLogger(WebTest.class.getName());
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
        logger.info("launch url");
        driver.get(properties.getProperty("baseUrl"));
    }

    @Test(testName = "verify forgot your password interface",description ="Password should be verified.user able to click on forgot your password.",priority = 0)
    public void forgotPasswordTest() throws InterruptedException {
        logger.info("forgotPasswordTest started");
        test = extent.createTest("forgotPasswordTest")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed(),"Password field should be displayed");
        test.log(loginPage.getPasswordField().isDisplayed() ? Status.PASS : Status.FAIL, loginPage.getPasswordField().isDisplayed() ? "PasswordFeild is displayed" : "PasswordFeild is not displayed");
        loginPage.goToForgotPassword();
        Thread.sleep(5000);
        logger.info("forgotPasswordTest ended");
    }

    @Test(testName = "password reset",description ="If the user forgot password they have to select reset password.They change their password and login with new credentials.",priority = 1)
    public void passwordResetTest()  {
        logger.info("passwordResetTest started");
        test = extent.createTest("passwordResetTest")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        Assert.assertTrue(loginPage.getPasswordField().isDisplayed(),"Password field should be displayed");
        test.log(loginPage.getPasswordField().isDisplayed() ? Status.PASS : Status.FAIL, loginPage.getPasswordField().isDisplayed() ? "PasswordFeild is displayed" : "PasswordFeild is not displayed");
        loginPage.goToForgotPassword();
        String username= jsonObject.get("userName").getAsString();
        forgotPasswordPage.restPassword(username);
        logger.info("passwordResetTest ended");
    }

    @Test(testName = "new login functionality",description = "user should be logged in username and new password",priority = 2)
    public void loginTest(){
        logger.info("loginTest started");
        test = extent.createTest("loginTest")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        String username= jsonObject.get("userName").getAsString();
        String password = jsonObject.get("password").getAsString();
        test.log(Status.INFO, "started entering the username and password");
        loginPage.orangeLogin(username,password);
        dashboardPage.logout();
        logger.info("loginTest ended");
    }

    @Test(testName = "create user",description = "to create user",priority = 3)
    public void createUserTest() throws InterruptedException {
        logger.info("createUserTest started");
        test = extent.createTest("createUserTest")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        String username= jsonObject.get("userName").getAsString();
        String password = jsonObject.get("password").getAsString();
        String user= jsonObject.get("user").getAsString();
        test.log(Status.INFO, "started entering the username and password");
        loginPage.orangeLogin(username,password);
        dashboardPage.createUser(user,password);
        Thread.sleep(5000);
        dashboardPage.logout();
        logger.info("createUserTest ended");

    }

    @Test(testName = "Make user an admin",description = "create user and make that user admin ",priority = 4)
    public void editUserTest(){
        logger.info("editUserTest started");
        test = extent.createTest("editUserTest")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        String username= jsonObject.get("userName").getAsString();
        String password = jsonObject.get("password").getAsString();
        String user= jsonObject.get("user").getAsString();
        test.log(Status.INFO, "started entering the username and password");
        loginPage.orangeLogin(username,password);
        dashboardPage.editUser(user);
        dashboardPage.logout();
        logger.info("editUserTest ended");
    }
    @Test(testName = "Verify that new user have all permisssions as admin",description = " Check new user have all permissions as admin.",priority = 5)
    public void adminUserTest(){
        logger.info("adminUserTest started");
        test = extent.createTest("adminUserTest")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        String password = jsonObject.get("password").getAsString();
        String user= jsonObject.get("user").getAsString();
        test.log(Status.INFO, "started entering the new username and password");
        loginPage.orangeLogin(user,password);
        Assert.assertTrue(dashboardPage.getAdmin().isDisplayed(),"Admin interface should be displayed");
        test.log(dashboardPage.getAdmin().isDisplayed() ? Status.PASS : Status.FAIL, dashboardPage.getAdmin().isDisplayed() ? "Admin interface is displayed" : "Admin interface is not displayed");
        dashboardPage.adminCheck();
        Assert.assertTrue(dashboardPage.getAdd().isDisplayed(),"Add user should be displayed");
        test.log(dashboardPage.getAdd().isDisplayed() ? Status.PASS : Status.FAIL, dashboardPage.getAdd().isDisplayed() ? "Add user is displayed" : "Add user is not displayed");
        dashboardPage.logout();
        logger.info("adminUserTest ended");
    }
    @Test(testName = "delete user",description = "to delete user",priority = 6)
    public void deleteUserTest(){
        logger.info("deleteUserTest started");
        test = extent.createTest("deleteUserTest")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        String username= jsonObject.get("userName").getAsString();
        String password = jsonObject.get("password").getAsString();
        String user= jsonObject.get("user").getAsString();
        test.log(Status.INFO, "started entering the username and password");
        loginPage.orangeLogin(username,password);
        Assert.assertTrue(dashboardPage.getAdmin().isDisplayed(),"Admin interface should be displayed");
        test.log(dashboardPage.getAdmin().isDisplayed() ? Status.PASS : Status.FAIL, dashboardPage.getAdmin().isDisplayed() ? "Admin interface is displayed" : "Admin interface is not displayed");
        dashboardPage.deleteUser(user);
        dashboardPage.logout();
        logger.info("deleteUserTest ended");
    }


}
