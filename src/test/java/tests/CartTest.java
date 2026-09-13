package tests;

import enums.TitleNaming;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import user.UserFactory;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    SoftAssert soft = new SoftAssert();

    @Test()
    public void checkGoodsInCart() {
        List<String> goodsList =
                List.of("Sauce Labs Fleece Jacket",
                        "Test.allTheThings() T-Shirt (Red)",
                        "Sauce Labs Bolt T-Shirt");
        System.out.println("checkGoodsInCart is running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

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
