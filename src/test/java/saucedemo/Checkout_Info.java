package saucedemo;

import common.core;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout_Info {
    WebDriver driver;
    core core;
    public Checkout_Info(WebDriver driver){
        this.driver = driver;
        core = new core(driver);
    }
    By firstName = By.xpath("//input[@id='first-name']");
    By lastName = By.xpath("//input[@id='last-name']");
    By zipCode = By.xpath("//input[@id='postal-code']");
    By continueBtn = By.xpath("//input[@id='continue']");

    public void information(String fname, String lname, String zipcode) {
        core.setText(firstName,fname);
        core.setText(lastName,lname);
        core.setText(zipCode,zipcode);
        core.clickElement(continueBtn);
    }
}
