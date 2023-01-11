package saucedemo;

import common.core;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Checkout_Overview {
    WebDriver driver;
    core core;

    public Checkout_Overview(WebDriver driver) {
        this.driver = driver;
        core = new core(driver);
    }

    By titlePage = By.xpath("//span[@class='title']");
    By nameProduct = By.xpath("//div[@class='inventory_item_name']");
    By finishButton = By.xpath("//button[@id='finish']");

    public void Overview() {
        String expectedTitle = "CHECKOUT: OVERVIEW";
        Assert.assertTrue(core.verifyElementText(titlePage, expectedTitle), "Title does not match");

        String expTitle = "Sauce Labs Backpack";
        Assert.assertTrue(core.verifyElementText(nameProduct, expTitle), "Product name does not match");

        driver.findElement(finishButton).click();
    }
}
