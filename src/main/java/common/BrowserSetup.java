package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BrowserSetup {
    private static WebDriver driver;
    static String chromePath = "src/main/resources/chromedriver.exe";
    static String firefoxPath = "src/main/resources/geckodriver.exe";
    static String edgePath = "src/main/resources/msedgedriver.exe";
    static String operaPath = "src/main/resources/geckodriver.exe";

    //Dùng để các class khác gọi đến nó, để nhận đc driver đã khởi tạo.
    //lấy giá trị trả về mà driver đã khởi tạo, có thể là Chrome,Firefox,...
    public static WebDriver getDriver() {
        return driver;
    }

    //Hàm này để tuỳ chọn Browser. Chạy trước khi gọi class (BeforeClass), truyền vào trong XML để chạy
    public WebDriver setupDriver(String browserType) {
        if (browserType.trim().toLowerCase().equals("chrome")) {
            driver = initChromeDriver();
        } else if (browserType.equals("firefox")) {
            driver = initFirefoxDriver();
        } else if (browserType.equals("edge")) {
            driver = initEdgeDriver();
        } else if (browserType.equals("opera")) {
            driver = initOperaDriver();
        } else {
            driver = initChromeDriver();
        }
        return driver;
    }

    //Hàm này để tuỳ chọn Browser. Chạy trước khi gọi class (BeforeClass), truyền vào trong XML để chạy
    private void setDriver(String browserType, String webURL) {
        if (browserType.equals("chrome")) {
            driver = initChromeDriver();
            driver.navigate().to(webURL);
        } else if (browserType.equals("firefox")) {
            driver = initFirefoxDriver();
            driver.navigate().to(webURL);
        } else if (browserType.equals("edge")) {
            driver = initEdgeDriver();
            driver.navigate().to(webURL);
        } else if (browserType.equals("opera")) {
            driver = initOperaDriver();
            driver.navigate().to(webURL);
        } else {
            driver = initChromeDriver();
        }
    }

    private static WebDriver initChromeDriver() {
        System.out.println("Launching Chrome Browser...");
        System.setProperty("webdriver.chrome.driver", chromePath);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        System.out.println("Launching FireFox Browser...");
        System.setProperty("webdriver.gecko.driver", firefoxPath);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        System.out.println("Launching Edge Browser...");
        System.setProperty("webdriver.edge.driver", edgePath);
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initOperaDriver() {
        System.out.println("Launching Opera Browser...");
        System.setProperty("webdriver.opera.driver", chromePath);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @Parameters({"browserType", "webURL"})
    @BeforeClass
    public void initializeTestBaseSetup(String browserType, String webURL) {
        try {
            //Khởi tạo driver và tuỳ chọn browser và webURL
            setDriver(browserType, webURL);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
