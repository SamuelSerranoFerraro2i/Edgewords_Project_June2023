package com.twoitesting.pompages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class DemoSiteLoginPOM {

    WebDriver driver;

//  Constructor
    public DemoSiteLoginPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    Locator
    @FindBy(id = "username")
    WebElement usernameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(name = "login")
    WebElement submitButton;
    @FindBy(linkText = "Dismiss")
    WebElement dismissButton;
    @FindBy(id = "menu-item-46")
    WebElement myAccountButton;


//  Service methods

    public DemoSiteLoginPOM getWebsite(){
        driver.get("https://www.edgewordstraining.co.uk/demo-site/");
        return this;
    }

    public DemoSiteLoginPOM goToMyAccount(){
        dismissButton.click();
        myAccountButton.click();
        driver.manage().window().fullscreen();
        return this;
    }

    public DemoSiteLoginPOM setUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    public DemoSiteLoginPOM setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public void submitLoginButton(){
        submitButton.click();
    }


}
