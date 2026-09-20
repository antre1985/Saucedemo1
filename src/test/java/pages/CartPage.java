package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private By continueShoppingButton = By.id("continue-shopping");
    private By productsName = By.cssSelector(".inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить названия товаров в корзине")
    public ArrayList<String> getProductsNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton));

                List<WebElement> allProducts = driver.findElements(productsName);
                ArrayList<String> names = new ArrayList<>();

                for (WebElement product : allProducts) {
                    names.add(product.getText());
                }
          return names;
    }
}
