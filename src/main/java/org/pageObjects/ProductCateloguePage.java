package org.pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCateloguePage {

    private AndroidDriver lDriver;
    public ProductCateloguePage(AndroidDriver rDriver)
    {
        this.lDriver=rDriver;
        PageFactory.initElements(new AppiumFieldDecorator(rDriver),this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCart;
    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartPage;

    public void addProductToCart(int index)
    {
        addToCart.get(index).click();
    }
    public CartPage goToCartPage()
    {
        cartPage.click();
        return new CartPage(lDriver);
    }
}
