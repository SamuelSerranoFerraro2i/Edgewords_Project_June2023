package com.twoitesting.pompages;

import com.twoitesting.utils.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoSiteShopPOM {

    WebDriver driver;

//    Constructor
    public DemoSiteShopPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    Locators
    @FindBy(id = "menu-item-43")
    WebElement shopButton;


//    Service methods
    public DemoSiteShopPOM goToShopPage(){

        Helpers help = new Helpers(driver);

        shopButton.click();
        help.helperScroller();
        return this;
    }

    public DemoSiteShopPOM addItemToCart(String item){

        Helpers help = new Helpers(driver);

        driver.findElement(By.partialLinkText(item)).click();
        driver.findElement(By.cssSelector("div.summary.entry-summary > form > button")).click();
        help.waitForPageLoadOut(2, By.linkText("View cart"), "View cart");
        driver.findElement(By.linkText("Cart")).click();
        return this;
    }

}
