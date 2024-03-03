package org.frameworkDemo;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.JavascriptExecutor;
import org.pageObjects.CartPage;
import org.pageObjects.FormPage;
import org.pageObjects.ProductCateloguePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class eCommerce_E2E extends BaseTest{

    //Adding Multiple Product to the Cart and verifying the total sum of product added in the cart
    @Test(dataProvider = "getData")
    public void addProductToCart(String name, String gender, String country) throws InterruptedException {

        FormPage formPage = new FormPage(driver);
        Thread.sleep(2000);
        formPage.setNameField(name);
        formPage.setGender(gender);
        formPage.selectCountry(country);
        formPage.submitForm();
        Thread.sleep(2000);
        String title = formPage.goToProductPage();
        Assert.assertEquals(title,"Products");

        ProductCateloguePage productCateloguePage = new ProductCateloguePage(driver);
        productCateloguePage.addProductToCart(0);
        productCateloguePage.addProductToCart(0);
        CartPage cartPage = productCateloguePage.goToCartPage();
        Thread.sleep(2000);

        double totalSum = cartPage.getProductPrices();
        double formattedSum = cartPage.getDisplayedSum();
        Assert.assertEquals(formattedSum,totalSum);
        cartPage.longPressOnElement();
        Thread.sleep(2000);
        cartPage.clickOnCheckboxAndVisitWebsite();
        Thread.sleep(2000);
        cartPage.handleHybridApplication("chatgpt");
        cartPage.backToNativeApp("Shyam");
        
    }

    //passing data using dataprovider- wants to run 2 set of data then but here we have issue we have to add
    //beforeMethod because driver wont be start from beginning
    @BeforeMethod
    public void runBeforeEachMethod()
    {
        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"

        ));
    }

    @DataProvider
    public Object[][] getData()
    {
        return new Object[][] { {"Ram" ,"Male", "Austria"}, {"Sita" ,"Female", "Bhutan"} };
    }

}
