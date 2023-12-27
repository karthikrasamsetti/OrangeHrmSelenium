package org.example.base;

import com.google.gson.JsonObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class Base {
    public ChromiumDriver driver;
    public JsonObject jsonObject;
    public Properties properties;
    private final JsonConverter jsonConverter = new JsonConverter();

    @BeforeSuite(alwaysRun = true)
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        properties = new Properties();
        try {
            properties.load(new FileReader(System.getProperty("user.dir") + "/src/test/resources/config/base.properties"));
            jsonObject = jsonConverter.getJson(properties.getProperty("orangeHRMDataPath"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        driver.get(properties.getProperty("baseUrl"));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        System.out.println("driver is started");
    }
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            System.out.println("driver is closing");
            driver.close();
            driver.quit();
        }
    }
}
