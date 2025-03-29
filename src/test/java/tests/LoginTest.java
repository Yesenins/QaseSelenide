package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void successLogin() {
        loginSteps.login(USER,PASSWORD,LOGIN_URL);
    }
}
