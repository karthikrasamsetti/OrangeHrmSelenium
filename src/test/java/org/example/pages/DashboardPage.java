package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    @FindBy(xpath = "//a[normalize-space()='Admin']")
    WebElement adminBtn;
    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addBtn;
    @FindBy(xpath = "(//div[contains(text(),'-- Select --')])[1]")
    WebElement roleSelect;
    @FindBy(xpath = "(//div[@role='option'])[3]")
    WebElement selectEss;
    @FindBy(xpath = "(//div[@role='option'])[2]")
    WebElement selectAdmin;
    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[2]")
    WebElement statusSelect;
    @FindBy(xpath = "//span[normalize-space()='Enabled']")
    WebElement selectEnable;
    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement usernameFeild;
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement employenameFeild;
    @FindBy(xpath = "//div[@role='listbox']//div[1]")
    WebElement selectEmployee;
    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement passwordFeild;
    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement confirmpasswordFeild;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveBtn;
    @FindBy(xpath = "(//i[@class='oxd-icon bi-pencil-fill'])[1]")
    WebElement editIcon;
    @FindBy(xpath = "(//div[contains(text(),'ESS')])[1]")
    WebElement enabledEss;
    @FindBy(xpath = "//div[@role='listbox']//div[2]")
    WebElement adminSelect;
    @FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
    WebElement profileDropdown;
    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement logoutButton;
    @FindBy(xpath = "(//i[@class='oxd-icon bi-trash'])[1]")
    WebElement deleteIcon;
    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    WebElement yesDelete;
    @FindBy(xpath = "//button[normalize-space()='No, Cancel']")
    WebElement notDelete;
    String store;
    public DashboardPage(ChromiumDriver driver) {
        PageFactory.initElements(driver, this); // Initialize WebElements
    }
    public void createUser(String username,String password){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        adminBtn.click();
        addBtn.click();
        roleSelect.click();
        selectEss.click();
        statusSelect.click();
        selectEnable.click();
        usernameFeild.sendKeys(username);
        employenameFeild.sendKeys("a");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        store= selectEmployee.getText();
        System.out.println("************"+store);
        selectEmployee.click();
        passwordFeild.sendKeys(password);
        confirmpasswordFeild.sendKeys(password);
        saveBtn.click();

    }
    public void editUser(String username){
        adminBtn.click();
        usernameFeild.sendKeys(username);
        roleSelect.click();
        selectEss.click();
        employenameFeild.sendKeys(store);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        selectEmployee.click();
        statusSelect.click();
        selectEnable.click();
        saveBtn.click();
        editIcon.click();
        enabledEss.click();
        adminSelect.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        saveBtn.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void logout(){
        profileDropdown.click();
        logoutButton.click();
    }
    public WebElement getAdmin(){
        return adminBtn;
    }
    public WebElement getAdd(){
        return addBtn;
    }

    public void adminCheck(){
        adminBtn.click();
    }
    public void deleteUser(String username){
        adminBtn.click();
        usernameFeild.sendKeys(username);
        roleSelect.click();
        selectAdmin.click();
        employenameFeild.sendKeys(store);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        selectEmployee.click();
        statusSelect.click();
        selectEnable.click();
        saveBtn.click();
        deleteIcon.click();
        yesDelete.click();

    }
}
