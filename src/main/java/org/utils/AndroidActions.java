package org.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions {
    AndroidDriver lDriver;
    public AndroidActions(AndroidDriver rDriver)
    {
        this.lDriver=rDriver;
    }

    public void longPressAction(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor)lDriver;
        js.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration",2000
        ));
    }
    public void scrollToText(String textName)
    {
        lDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+textName+"\"));")).click();
    }
    public double getFormattedAmount(String price)
    {
        String displayPrice = price.substring(1);
        return Double.parseDouble(displayPrice);
    }
}
