package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withLockedPermission;
import static user.UserFactory.withInvalidCredentials;
import static user.UserFactory.withEmptyUsername;
import static user.UserFactory.withEmptyPassword;

public class LoginTest extends BaseTest {

        @DataProvider
        public Object[][] loginData() {
            return new Object[][]{
                    {withLockedPermission(),   "Epic sadface: Sorry, this user has been locked out."},
                    {withInvalidCredentials(), "Epic sadface: Username and password do not match any user in this service"},
                    {withEmptyUsername(),      "Epic sadface: Username is required"},
                    {withEmptyPassword(),      "Epic sadface: Password is required"}
            };
    }

    @Test(dataProvider = "loginData")
    public void incorrectDataLoginTest(User user, String errorMessage) {
        System.out.println("incorrectDataLoginTest is running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(user);

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message does not appear");
        assertEquals(errorText, errorMessage, "Error text does not match");
    }

    @Test
    public void correctUserTest() {
        System.out.println("correctUserTest is running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());

        boolean pageTitleVisible = productsPage.isPageTitleVisible();
        assertTrue(pageTitleVisible);
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());
    }
}
