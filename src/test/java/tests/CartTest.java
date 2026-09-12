package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {
    SoftAssert soft = new SoftAssert();

    @Test()
    public void checkGoodsInCart() {
        List<String> goodsList =
                List.of("Sauce Labs Fleece Jacket",
                        "Test.allTheThings() T-Shirt (Red)",
                        "Sauce Labs Bolt T-Shirt");

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), "Products");

        for (String goodsName : goodsList) {
            productsPage.addGoodsToCart(goodsName);
        }
        productsPage.switchToCart();

        soft.assertFalse(cartPage.getProductsNames().isEmpty());
        soft.assertEquals(cartPage.getProductsNames().size(), 3);
        soft.assertTrue(cartPage.getProductsNames().contains("Sauce Labs Fleece Jооо"));
        soft.assertTrue(cartPage.getProductsNames().contains("Test.allTheThings() T-Shirt (Red)"));
        soft.assertTrue(cartPage.getProductsNames().contains("Sauce Labs Bolt T-Shirt"));

        soft.assertAll();
    }
}
