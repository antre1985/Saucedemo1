package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final By usernameInput = By.cssSelector("[placeholder='Username']");
    private final By passwordInput = By.cssSelector("[placeholder='Password']");
    private final By loginButton = By.cssSelector("[data-test='login-button']");

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com");
    }

    public void login(String user, String password) {
        driver.findElement(usernameInput).sendKeys(user);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isErrorVisible() {
       return driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
    }

    public String getErrorText() {
      return driver.findElement(By.cssSelector("[data-test='error']")).getText();
    }
}
