package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser) {
            case FIREFOX:
                driver = WebDriverManager.firefoxdriver().create();
                break;

            case CHROME:
                driver = WebDriverManager.chromedriver().create();
                break;

            case EDGE:
                driver = WebDriverManager.edgedriver().create();
                break;

            default:
                throw new RuntimeException("Browser name is not valid.");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://demo.nopcommerce.com/");

        return driver;
    }

    protected String getEmailRandom() {
        return "john" + new Random().nextInt(99999) + "@kennedy.us";
    }

    protected void closeBrowser() {
        if (driver == null) {
            System.out.println("Browser is closed.");
        } else {
            driver.quit();
        }
    }
}
