package com.twoitesting.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {

    private WebDriver driver;

    private final SharedDictionary dict;

    public Hooks(SharedDictionary dict){
        this.dict = dict;
    }

    @Before
    public void setUp(){

        String browser = System.getProperty("browser");
        System.out.println("Browser from commandline: " + browser);
        if(browser==null){browser="";}
        switch (browser){
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Invalid browser selection. Using Chrome");
                driver = new ChromeDriver();
                break;
        }

        dict.addDict("mydriver", driver);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
