package com.twoitesting.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Helpers {

    WebDriver driver;

    public Helpers(WebDriver driver){
        this.driver = driver;
    }

    public WebElement waitForPageLoadOut(int timeOutInSec, By locator, String text){
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        myWait.until(drv -> {
            try {
                WebElement heading = drv.findElement(locator);
                if (heading.getText().equals(text)) {
                    return heading;
                } else {
                    return null;
                }
            } catch (NoSuchElementException e) {
                return null;
            }
        });
        return null;
    }

    public void helperScroller(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
    }

}
