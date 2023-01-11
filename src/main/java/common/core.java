package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.function.Function;

public class core {
    //Khởi tạo đối tượng
    private WebDriver driver;
    private Actions action;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Select select;
    private final int timeoutWait = 10;
    private final int timeoutWaitForPageLoaded = 30;
    private WebElement webElement;

    public core(WebDriver driver) { //khởi tạo giá trị ở hàm xây dựng (constructor)
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
//        wait = new WebDriverWait(driver,10);
    }

    public void setText(By element, String value) {
//        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click(); //click vào ô username hoặc password
        driver.findElement(element).clear(); //xoá hết những gì có trong ô username hoặc password
        driver.findElement(element).sendKeys(value); //nhập vào giá trị
    }

    public void clickElement(By element) {
//        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    public void clickElementWithJs(By element) {
        waitForPageLoaded();
        //Đợi đến khi element nó tồn tại
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        //Cuộn chuột tới 1 element nào đó với JS
        js.executeScript("argument[0].scrollIntoView(true);", driver.findElement(element));
        //Click vào element với JS
        js.executeScript("argument[0].click()", driver.findElement(element));
        //muốn chuyển từ dạng By về WebElement thì phải thêm driver.findElement.
    }

    public boolean verifyURL(String url) {
        return driver.getCurrentUrl().contains(url); //Trả về True/False
    }

    public boolean verifyElementText(By element, String text) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(text); //Trả về True/False
    }

    public boolean verifyElementTextExists(By element) {
        //Tạo list lưu tất cả đối tượng Element
        List<WebElement> listElement = driver.findElements(element); //sẽ trả về ít nhất là 1 đối tượng element
        int total = listElement.size();
        if (total > 0) {
            return true;
        }
        return false;
    }

    public String getPageTitle() {
        waitForPageLoaded();
        String title = driver.getTitle();
        return title;
    }

    public boolean verifyPageTitle(String pageTitle) { //so sánh Page title
        waitForPageLoaded();
        return getPageTitle().equals(pageTitle);
    }

    public String getElementText(By element) {
        return driver.findElement(element).getText();
    }

    //Wait
    public void waitForPageLoaded() {
        // wait for jQuery to loaded
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver)
                            .executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {
//            wait = new WebDriverWait(driver,timeoutWaitForPageLoaded);
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }
    }

    public void waitingForPageLoaded() {
        try {
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return String
                            .valueOf(((JavascriptExecutor) driver)
                                    .executeScript("return document.readyState"))
                            .equals("complete");
                }
            });
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page load request");
        }
    }

    public void rightClickElement(By element) {
//        wait.until(ExpectedConditions.elementToBeClickable(element));
        action.contextClick().build().perform();
    }

    public void selectOptionByText(By element, String text) {
        select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public void selectOptionByValue(By element, String value) {
        select = new Select(driver.findElement(element));
        select.selectByValue(value);
    }

    public void selectMultipleOptions(By element, String value, int Index) {
        select = new Select(driver.findElement(element));
        select.selectByVisibleText(value);
        select.selectByIndex(Index);
        select.selectByValue(value);
    }

    public void selectOptionByIndex(By element, int Index) {
        select = new Select(driver.findElement(element));
        select.selectByIndex(Index);
    }

    //xác minh số lượng giá trị của Dropdown list
    public void verifyOptionTotalDropDown(By element, int total) {
        select = new Select(driver.findElement(element));
        Assert.assertEquals(total, select.getOptions().size());
    }


}
