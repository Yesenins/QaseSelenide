package steps;

import io.qameta.allure.Step;
import pages.LoginPage;


public class LoginSteps extends BaseSteps{

    LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
    }

    @Step("Login by user: {email}")
    public void login(String email, String password, String url) {
        loginPage
                .openLoginPage(url)
                .login(email, password)
                .isOpened();
    }
}
