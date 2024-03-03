package org.pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.utils.AndroidActions;

public class FormPage extends AndroidActions {
    private AndroidDriver lDriver;
    public FormPage(AndroidDriver rDriver)
    {
        super(rDriver);
        this.lDriver=rDriver;
        PageFactory.initElements(new AppiumFieldDecorator(rDriver),this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
    private WebElement maleGender;
    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
    private WebElement femaleGender;
    @AndroidFindBy(id="android:id/text1")
    private WebElement dropdownClick;
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement loginButton;
    @AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
    private WebElement productTitle;

    public void setNameField(String name)
    {
        nameField.sendKeys(name);
        lDriver.hideKeyboard();
    }

    public void setGender(String gender)
    {
        if(gender.equalsIgnoreCase("male")) {
            maleGender.click();
        } else if(gender.equalsIgnoreCase("Female"))
        {
            femaleGender.click();
        }
    }
    public void selectCountry(String countryName) {
        dropdownClick.click();
        scrollToText(countryName);
    }
    public void submitForm() {
        loginButton.click();
    }
    public String goToProductPage()
    {
        String prodPageTitle = productTitle.getText();
        return prodPageTitle;
    }







}
