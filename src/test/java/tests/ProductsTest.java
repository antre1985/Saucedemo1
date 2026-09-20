package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("Saucedemo")
@Feature("Товары")
public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Fleece Jacket",
                    "Test.allTheThings() T-Shirt (Red)",
                    "Sauce Labs Bolt T-Shirt");

    @Test(description = "Проверка добавления товаров в корзину")
    @Story("Добавление товаров в корзину")
    public void checkGoodsAdded() {

        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        for (String goodsName : goodsList) {
            productsPage.addGoodsToCart(goodsName);
        }

        productsPage.addGoodsToCart(0);

        assertTrue(productsPage.isCartBadgeVisible(), "Cart badge should be visible");
        assertEquals(productsPage.checkCountersValue(), "4");
        assertEquals(productsPage.checkCountersColor(), "rgba(226, 35, 26, 1)");
    }
}
