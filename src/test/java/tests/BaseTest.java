package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;

public class BaseTest {
   public WebDriver driver;
   LoginPage loginPage;
   ProductsPage productsPage;
   CartPage cartPage;

   @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
       if (browser.equalsIgnoreCase("chrome")) {
           WebDriverManager.chromedriver().setup();
           ChromeOptions options = new ChromeOptions();
           options.addArguments("start-maximized");
           options.addArguments("--guest");
           options.addArguments("headless");

           driver = new ChromeDriver(options);
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
       } else if (browser.equalsIgnoreCase("firefox")) {
           WebDriverManager.firefoxdriver().setup();
           driver = new FirefoxDriver();
       }

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod
    public void close() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
