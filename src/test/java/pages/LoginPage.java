package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By usernameInput = By.cssSelector("[placeholder='Username']");
    private final By passwordInput = By.cssSelector("[placeholder='Password']");
    private final By loginButton = By.cssSelector("[data-test='login-button']");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем соответствующий браузер")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Авторизация под кредами пользователя")
    public void login(User user) {
        driver.findElement(usernameInput).sendKeys(user.getUser());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();
    }

    @Step("Проверяем, что сообщение об ошибке отображается")
    public boolean isErrorVisible() {
       return driver.findElement(errorMessage).isDisplayed();
    }

    @Step("Проверяем текст сообщения об ошибке")
    public String getErrorText() {
      return driver.findElement(errorMessage).getText();
    }
}
