package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {

    private static final SelenideElement SIGN_IN = $x("//*[@type='submit']");

    public LoginPage() {
    }

    public LoginPage openLoginPage(String url) {
        open(url);
        waiters.waitForPageLoaded();
        return this;
    }

    public LoginPage isOpened() {
        SIGN_IN.shouldBe(Condition.visible);
        return this;
    }

    private LoginPage fillLoginForm(String email, String password) {
        isOpened();
        new Input("email").writeSignIn(email);
        new Input("password").writeSignIn(password);
        new Button().click(SIGN_IN);
        return this;
    }
    public ProjectsListPage login(String email, String password) {
        fillLoginForm(email, password);
        waiters.waitForPageLoaded();
        return new ProjectsListPage();
    }
}
