package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

        public static User withLockedPermission() {
            return new User(PropertyReader.getProperty("saucedemo.locked.user"),
                    PropertyReader.getProperty("saucedemo.password"));
        }

    public static User withInvalidCredentials() {
        return new User("Standard_user",
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withEmptyUsername() {
        return new User("",
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withEmptyPassword() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                "");
    }
}
