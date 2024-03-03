package org.frameworkDemo;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eCommerce_ErrorValidation extends BaseTest{

    @BeforeMethod
    public void runBeforeEachMethod()
    {
        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"

        ));
    }

    @Test
    public void FillingForm_ErrorValidation()
    {
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage,"Please enter your name");
    }

    @Test
    public void FillingForm_PositiveFlow() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Kiran");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        //scrolling to particular country
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

}
