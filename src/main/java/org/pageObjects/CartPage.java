package org.pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.AndroidActions;

import java.util.List;
import java.util.Set;

public class CartPage extends AndroidActions {

    private AndroidDriver lDriver;
    public CartPage(AndroidDriver rDriver)
    {
        super(rDriver);
        this.lDriver=rDriver;
        PageFactory.initElements(new AppiumFieldDecorator(rDriver),this);
    }
    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrice;
    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement displayedSum;
    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement element;
    @AndroidFindBy(id="android:id/button1")
    private WebElement closeButton;
    @FindBy(name="q")
    private WebElement googleSearch;

    public double getProductPrices()
    {
        double totalSum=0;
        for(int i=0;i<productPrice.size();i++)
        {
            String prodPrice = productPrice.get(i).getText();
            //$160.97
            String price = prodPrice.substring(1);
            double sum = Double.parseDouble(price);
            totalSum = totalSum+sum;
        }
        System.out.println("IndividualProductSum::"+totalSum);
        return totalSum;
    }
    public double getDisplayedSum()
    {
        String displaySum = displayedSum.getText();
        return getFormattedAmount(displaySum);
    }
    public void longPressOnElement() throws InterruptedException {
        longPressAction(element);
        Thread.sleep(1000);
        closeButton.click();
    }
    @AndroidFindBy(xpath="//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")
    private WebElement checkbox;
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement visitWebsite;
    public void clickOnCheckboxAndVisitWebsite()
    {
        checkbox.click();
        visitWebsite.click();
    }
    public void handleHybridApplication(String text) throws InterruptedException
    {
        Set<String> contexts = lDriver.getContextHandles();
        for (String contextName : contexts) {
            System.out.println("contextname::" + contextName);
        }
        //optput: contextname::NATIVE_APP
        //contextname::WEBVIEW_com.androidsample.generalstore
        Thread.sleep(2000);
        lDriver.context("WEBVIEW_com.androidsample.generalstore");
        //now perform any operation on webpage
        googleSearch.sendKeys(text);
        googleSearch.sendKeys(Keys.ENTER);
        Thread.sleep(4000);
    }
    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nativeApp;
    public void backToNativeApp(String name)
    {
        lDriver.pressKey(new KeyEvent(AndroidKey.BACK));
        //back to default-or native app
        lDriver.context("NATIVE_APP");
        nativeApp.sendKeys(name);
    }

}
