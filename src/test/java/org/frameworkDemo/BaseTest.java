package org.frameworkDemo;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    AndroidDriver driver;
    AppiumDriverLocalService service;

    @BeforeClass
    public void BaseTestMethod() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\resources\\runConfig.properties");
        properties.load(fis);

        String ipAddress = properties.getProperty("ipAddress");
        String port = properties.getProperty("port");

        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Avinash\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).withIPAddress(ipAddress).usingPort(Integer.parseInt(port)).build();
       // service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("AviPhone");
        options.setPlatformName("Android");
        options.setChromedriverExecutable("C:\\Users\\Avinash\\Documents\\chromedriver_win32\\chromedriver.exe");
        options.setApp("C:\\Users\\Avinash\\IdeaProjects\\AppiumFramework\\src\\test\\java\\APKFileRresource\\General-Store.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void longPressAction(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration",2000
        ));
    }

    @AfterClass
    public void tearDown()
    {
        //service.stop();
    }
}
