package com.twoitesting.pompages;

import com.twoitesting.utils.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class DemoSiteCheckoutPOM {

    WebDriver driver;

    //  Constructor
    public DemoSiteCheckoutPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    Locators

    @FindBy(css = "#post-6 > header > h1" )
    WebElement billingPage;

    @FindBy(id = "billing_first_name")
    WebElement billingName;

    @FindBy(id = "billing_last_name")
    WebElement billingLastName;

    @FindBy(id = "billing_company")
    WebElement billingCompanyName;

    @FindBy(id = "select2-billing_country-container")
    WebElement countrySelector;

    @FindBy(css = "body > span > span > span.select2-search.select2-search--dropdown > input")
    WebElement ukInputText;

    @FindBy(id = "billing_address_1")
    WebElement billingAddress;

    @FindBy(id = "billing_address_2")
    WebElement billingAddressTwo;

    @FindBy(id = "billing_city")
    WebElement townName;

    @FindBy(id = "billing_postcode")
    WebElement billingPostCode;

    @FindBy(id = "billing_phone")
    WebElement billingPhoneNumber;

    @FindBy(id = "billing_email")
    WebElement billingEmail;

    @FindBy(id = "place_order")
    WebElement placeOrderButton;


//    Service methods

    public DemoSiteCheckoutPOM fillBillingNames(){

        Helpers help = new Helpers(driver);

        help.helperScroller();
        assertThat("We are in the checkout page", billingPage.getText(), containsString("Checkout"));

        billingName.clear();
        billingName.sendKeys("Samuel");

        billingLastName.clear();
        billingLastName.sendKeys("Ferraro");

        billingCompanyName.clear();
        billingCompanyName.sendKeys("2i Testing");

        return this;
    }

    public DemoSiteCheckoutPOM fillInCheckoutBillingAddress(){

        Helpers help = new Helpers(driver);

        countrySelector.click();
        ukInputText.click();
        ukInputText.sendKeys("United Kingdom" + Keys.ENTER);

        billingAddress.clear();
        billingAddress.sendKeys("57/1 Newington Road");
        billingAddressTwo.clear();
        billingAddressTwo.sendKeys("Unit 1F1");

        townName.clear();
        townName.sendKeys("Edinburgh");

        billingPostCode.clear();
        billingPostCode.sendKeys("EH9 1QW");

        billingPhoneNumber.clear();
        billingPhoneNumber.sendKeys("01234567890");

        billingEmail.clear();
        billingEmail.sendKeys("samuel.ferraro@2itesting.com");

        help.helperScroller();
        placeOrderButton.click();

        help.waitForPageLoadOut(2, By.cssSelector(".entry-title"), "Order received");

        return this;
    }

}
