package saucedemo;

import common.core;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    core core; //khai báo đối tượng của core


    //Đây là hàm xây dựng (constructor)
    public LoginPage(WebDriver driver) { //truyền vào driver đã khởi tạo ở BaseSetup.
        this.driver = driver;
        core = new core(driver); //khởi tạo giá trị
    }

    //Element at Login Page
    private By usernameInput = By.xpath("//input[@id='user-name']");
    private By passwordInput = By.xpath("//input[@id='password']");
    private By loginBtn = By.xpath("//input[@id='login-button']");


    public void login(String username, String password) {
        core.setText(usernameInput, username);
        core.setText(passwordInput, password);
        core.clickElement(loginBtn);
    }
}
