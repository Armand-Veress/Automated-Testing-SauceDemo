package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "validLoginCredentials", parallel = true)
    public static Object[][] getValidLoginData() {
        return new Object[][] {
             // [0] Username, [1] Password
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidLoginCredentials", parallel = true)
    public static Object[][] getInvalidLoginData() {
        return new Object[][] {
             // [0] Username, [1] Password, [2] Expected error response
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "requiredUsername", "Epic sadface: Username is required"},
                {"requiredPassword", "", "Epic sadface: Password is required"},
                {"invalidUsername", "invalidPassword", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @DataProvider(name = "protectedPages", parallel = true)
    public static Object[][] getProtectedPagesData() {
        return new Object[][] {
             // [0] Page URL without BASE_URL
                {Routes.INVENTORY},
                {Routes.CART}
        };
    }
}
