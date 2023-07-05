package com.twoitesting.pompages;

import com.twoitesting.utils.Helpers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

public class DemoSiteCartPOM {

    WebDriver driver;

    //  Constructor
    public DemoSiteCartPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    Locators
    @FindBy(id = "coupon_code")
    WebElement addCoupon;

    @FindBy(css = ".cart-subtotal > [data-title='Subtotal'] > span > bdi")
    WebElement subTotal;

    @FindBy(css = "#shipping_method > li > label > span > bdi")
    WebElement shippingTotal;

    @FindBy(css = ".order-total > td > strong > span > bdi")
    WebElement totalTotal;

//    Locator unable to find item (despite working previously). Problem to solve later
//    @FindBy(css = "[data-title=\"Coupon: edgewords\"] > span > bdi")
//    WebElement discountTotal;

    @FindBy (linkText = "Proceed to checkout")
    WebElement checkoutButton;


//    Service methods

    public DemoSiteCartPOM applyDiscountToCart(String discount){

        Helpers help = new Helpers(driver);

        addCoupon.clear();
        addCoupon.sendKeys(discount + Keys.ENTER);
        help.helperScroller();
        return this;
    }

    public DemoSiteCartPOM checkCorrectPrice(){

        BigDecimal discount = new BigDecimal("0.15");

        BigDecimal subTotalNumber = new BigDecimal(subTotal.getText().substring(1));
        BigDecimal shippingTotalNumber = new BigDecimal(shippingTotal.getText().substring(1));
//        BigDecimal discountTotalNumber = new BigDecimal(discountTotal.getText().substring(1));
        BigDecimal totalTotalNumber = new BigDecimal(totalTotal.getText().substring(1));


        BigDecimal discountTotal = subTotalNumber.multiply(discount);
        BigDecimal confirmTotal = subTotalNumber.subtract(discountTotal).add(shippingTotalNumber);

        assertThat("Discount is applied", confirmTotal, Matchers.comparesEqualTo(totalTotalNumber.subtract(discountTotal)));

        checkoutButton.click();

        return this;
    }

}
