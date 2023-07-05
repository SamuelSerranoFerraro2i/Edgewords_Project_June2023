package com.twoitesting;

import com.twoitesting.pompages.*;
import com.twoitesting.utils.SharedDictionary;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ScenarioOne {

    private WebDriver driver;

    private final SharedDictionary dict;

    public ScenarioOne(SharedDictionary dict){
        this.dict = dict;
        this.driver = (WebDriver)dict.readDict("mydriver");
    }

    @Given("I am logged into my account on demo-site")
    public void iAmLoggedIntoMyAccountOnECommerceSite() {

        DemoSiteLoginPOM login = new DemoSiteLoginPOM(driver);

        login.getWebsite().goToMyAccount();
        login.setUsername("samuel.ferraro@2itesting.com").setPassword("Testing1x5Edgewords!").submitLoginButton();
    }

    @When("I navigate to the shop I add an item of clothing to my basket")
    public void iNavigateToTheShopAndAddAnItemOfClothingToMyBasket() {

        DemoSiteShopPOM shop = new DemoSiteShopPOM(driver);

        shop.goToShopPage().addItemToCart("Cap");
    }

    @And("I apply a discount code")
    public void iApplyADiscountCode() throws InterruptedException {

        DemoSiteCartPOM cart = new DemoSiteCartPOM(driver);

        driver.manage().window().fullscreen();
        cart.applyDiscountToCart("edgewords");
        cart.checkCorrectPrice();
    }

    @Then("I will place the order")
    public void iWillPlaceTheOrder() {

        DemoSiteCheckoutPOM checkout = new DemoSiteCheckoutPOM(driver);

        driver.manage().window().fullscreen();
        checkout.fillBillingNames();
        checkout.fillInCheckoutBillingAddress();

    }

    @And("I go to My Orders section")
    public void iGoToMyOrdersSection() throws InterruptedException {

        DemoSiteOrderDetailsPOM orderDetails = new DemoSiteOrderDetailsPOM(driver);

        orderDetails.checkOrderNumberMatches();
    }

    @Then("I can check the order number is present")
    public void iCanCheckTheOrderNumberIsPresent() {

        DemoSiteOrderDetailsPOM orderDetails = new DemoSiteOrderDetailsPOM(driver);

        driver.manage().window().fullscreen();
        orderDetails.logOutAtTheEnd();



    }


}
