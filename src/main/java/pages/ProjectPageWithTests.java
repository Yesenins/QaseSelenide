package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPageWithTests extends BasePage{

    private static final SelenideElement TEST_NAME = $x("//h1/div");

    public ProjectPageWithTests() {
    }

    public ProjectPageWithTests isOpened() {
        TEST_NAME.shouldBe(Condition.visible);
        return this;
    }
}
