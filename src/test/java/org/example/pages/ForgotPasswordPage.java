package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
    @FindBy(xpath ="//input[@placeholder='Username']")
    WebElement usernameField;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement resetPasswordButton;
    public ForgotPasswordPage(ChromiumDriver driver) {
        PageFactory.initElements(driver, this); // Initialize WebElements
    }
    public void restPassword(String username) {
        usernameField.sendKeys(username);
        resetPasswordButton.click();
    }
}
