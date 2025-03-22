package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Input {
    String label;
    public String inputLoginLocator = "//*[@name='%s']";
    public String inputNewProjectModalLocator = "//*[@id='%s']";
    public String inputTitleTestCaseLocator = "//*[@id='title']";
    public String inputTestCaseLocator = "//*[text()='%s']/parent::*//p";


    public Input(String label) {
        this.label = label;
    }

    public Input writeSignIn(String text) {
        $x(String.format(inputLoginLocator, label)).shouldBe(Condition.visible).setValue(text);
        return this;
    }

    public Input writeNewProject(String text) {
        $x(String.format(inputNewProjectModalLocator, label)).shouldBe(Condition.visible).setValue(text);
        return this;
    }

    public Input writeTitleTestCase(String text) {
        $x(String.format(inputTitleTestCaseLocator)).shouldBe(Condition.visible).setValue(text);
        return this;
    }

    public Input writeTestCase(String text) {
        $x(String.format(inputTestCaseLocator, label)).shouldBe(Condition.visible).setValue(text);
        return this;
    }

    public Input clearLogin() {
        SelenideElement element = $x(String.format(inputLoginLocator, label));
        element.click();
        element.clear();
        return this;
    }

    public Input clearProject() {
        SelenideElement element = $x(String.format(inputNewProjectModalLocator, label));
        element.click();
        element.clear();
        return this;
    }
}
