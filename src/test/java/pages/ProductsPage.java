package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    private final By pageTitle = By.cssSelector("[data-test='title']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }
}
