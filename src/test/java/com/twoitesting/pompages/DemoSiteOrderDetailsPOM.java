package com.twoitesting.pompages;

import com.twoitesting.utils.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class DemoSiteOrderDetailsPOM {

    WebDriver driver;

    //  Constructor
    public DemoSiteOrderDetailsPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    Locators

    @FindBy(css = "li.woocommerce-order-overview__order > strong")
    WebElement orderNumber;

    @FindBy(id = "menu-item-46")
    WebElement myAccountButton;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--orders > a")
    WebElement myOrdersButton;

    @FindBy(linkText = "Logout")
    WebElement logOutButton;

//    Service methods

    public DemoSiteOrderDetailsPOM checkOrderNumberMatches() throws InterruptedException {

        Helpers help = new Helpers(driver);

        String finalOrderNumber = orderNumber.getText();

        myAccountButton.click();
        help.waitForPageLoadOut(2, By.cssSelector(".woocommerce-MyAccount-navigation-link--orders > a"), "Orders");

        myOrdersButton.click();
        help.waitForPageLoadOut(2, By.cssSelector("#post-7 > header > h1"), "Orders");

        String orderNumberOnOrdersPage = driver.findElement(By.cssSelector(".woocommerce-orders-table__cell-order-number > a")).getText();

        assertThat("Order number matches the last order", ("#" + finalOrderNumber), containsString(orderNumberOnOrdersPage));

        return this;
    }

    public DemoSiteOrderDetailsPOM logOutAtTheEnd(){

        Helpers help = new Helpers(driver);

        help.helperScroller();
        logOutButton.click();

        return this;
    }





}
