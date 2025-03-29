package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Dropdown;
import elements.Input;

import static com.codeborne.selenide.Selenide.$x;

public class NewSuiteModalPage extends BasePage{

    private static final SelenideElement CREATE_BUTTON = $x("//*[text()='Create']");
    public NewSuiteModalPage() {
    }

    public NewSuiteModalPage isOpened() {
        CREATE_BUTTON.shouldBe(Condition.visible);
        return this;
    }

    public NewSuiteModalPage fillCreateSuiteForm(String suiteName, String text, String preconditions) {
        new Input("Suite name").writeSuiteName(suiteName);
        new Input("Description").writeSuiteForm(text);
        new Input("Preconditions").writeSuiteForm(preconditions);
        new Button().click(CREATE_BUTTON);
        return this;
    }

    public ProjectPage createSuite(String suiteName, String text, String preconditions) {
        isOpened();
        fillCreateSuiteForm(suiteName,text,preconditions);
        waiters.waitForPageLoaded();
        return new ProjectPage();
    }
}
