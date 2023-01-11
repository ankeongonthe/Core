package saucedemo;

import common.core;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ShoppingCartPage {
    WebDriver driver;
    core core;
    public ShoppingCartPage(WebDriver driver){
        this.driver = driver;
        core = new core(driver);
    }
    By product = By.xpath("//div[@class='inventory_item_name']");
    By checkoutButton = By.xpath("//button[@id='checkout']");

    public void shoppingCart() {
        String actName = "Sauce Labs Backpack";
        Assert.assertTrue(core.verifyElementText(product,actName),"The product name does not match");
        core.clickElement(checkoutButton);
    }
}
