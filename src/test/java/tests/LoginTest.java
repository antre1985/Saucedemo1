package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test
    public void lockedUserTest() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorVisible(), "Error message does not appear");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void correctUserTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        boolean pageTitleVisible = productsPage.isPageTitleVisible();
        assertTrue(pageTitleVisible);
        assertEquals(productsPage.getPageTitle(), "Products");
    }

    @Test
    public void emptyLoginUserTest() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible);
        assertEquals(errorText, "Epic sadface: Username is required");
    }

    @Test
    public void EmptyPasswordUserTest() {
        loginPage.open();
        loginPage.login("Standard_user", "");

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible);
        assertEquals(errorText, "Epic sadface: Password is required");
    }

    @Test
    public void loginWithUppercaseTest() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message does not appear");
        assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service");
    }
}
