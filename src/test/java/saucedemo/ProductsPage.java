package saucedemo;

import common.core;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductsPage {
    WebDriver driver;
    core core;
    public ProductsPage(WebDriver driver) { //truyền vào driver đã khởi tạo ở BaseSetup
        this.driver = driver;
        core = new core(driver);
    }

    //Element at Product Page
    By firstItem = By.xpath("//div[normalize-space()='Sauce Labs Backpack']");
    By addBtn = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
    By shoppingCart = By.xpath("//a[@class='shopping_cart_link']");

    public void products() {
        String actName = "Sauce Labs Backpack";
        Assert.assertTrue(core.verifyElementText(firstItem,actName),"Tên sản phẩm không trùng khớp");
        core.clickElement(addBtn);
        core.clickElement(shoppingCart);
    }
}
