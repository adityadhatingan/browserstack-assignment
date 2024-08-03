package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.BrowserUtil;

public class DriverFactory {
    public WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver initDriver(String scenarioName) {

        switch (BrowserUtil.setBrowser()) {
            case "chrome":
                // WebDriverManager.chromedriver().setup();
                // Create an instance of ChromeOptions
                ChromeOptions options = new ChromeOptions();

                // Disable notifications
                options.addArguments("--disable-notifications");
                tlDriver.set(new ChromeDriver(options));
                break;
            case "firefox":
                // WebDriverManager.firefoxdriver().setup();
                tlDriver.set(new FirefoxDriver());
                break;
            default:
                System.out.println("Please pass correct browser value");
                break;
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}
