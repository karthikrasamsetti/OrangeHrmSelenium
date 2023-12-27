package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(xpath ="//input[@placeholder='Username']")
    WebElement usernameField;
    @FindBy(xpath ="//input[@placeholder='Password']")
    WebElement passwordField;

    @FindBy(xpath ="//button[normalize-space()='Login']")
    WebElement loginButton;
    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
    WebElement forgotPasswordButton;
    @FindBy(xpath = "//img[@alt='company-branding']")
    WebElement logo;
    //    private final ChromiumDriver driver;
    public LoginPage(ChromiumDriver driver) {
        PageFactory.initElements(driver, this); // Initialize WebElements
    }
    public void orangeLogin(String username,String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
    public WebElement getLogo(){
        return logo;
    }
    public WebElement getPasswordField(){
        return passwordField;
    }
    public void goToForgotPassword() {
        forgotPasswordButton.click();
    }
}
