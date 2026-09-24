package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART_PATTERN = "//*[text()='%s']" +
            "/ancestor::div[@class='inventory_item']//child::button[text()='Add to cart']";
    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By cartBadge = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверить, что заголовок страницы товаров отображается")
    public boolean isPageTitleVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    @Step("Получить заголовок страницы товаров")
    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }

    @Step("Добавить в корзину товар: {goodsName}")
    public void addGoodsToCart(String goodsName) {
        By addToCartBtn = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));
        driver.findElement(addToCartBtn).click();
    }

    @Step("Добавить в корзину товар с индексом: {goodsIndex}")
    public void addGoodsToCart(int goodsIndex) {
        By addToCartBtn = By.xpath("//button[text()='Add to cart']");
        driver.findElements(addToCartBtn).get(goodsIndex).click();
    }

    @Step("Получить значение счетчика корзины")
    public String checkCountersValue() {
      return driver.findElement(cartBadge).getText();
    }

    @Step("Получить цвет счетчика корзины")
    public String checkCountersColor() {
      return driver.findElement(cartBadge).getCssValue("background-color");
    }

    @Step("Проверить, что счетчик корзины отображается")
    public Boolean isCartBadgeVisible() {
      return driver.findElement(cartBadge).isDisplayed();
    }

    @Step("Перейти в корзину")
    public void switchToCart() {
         driver.findElement(cartLink).click();
    }
}
