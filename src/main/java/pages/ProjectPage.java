package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage extends BasePage{

    private static final SelenideElement NEW_TEST = $x("//*[text()='New test']");
    private static final String REPOSITORY_NAME = "//*[@id='application-content']//h1";

    public ProjectPage() {
    }

    public ProjectPage isOpened() {
       NEW_TEST.shouldBe(Condition.visible);
        return this;
    }

    public TestCasePage openNewTest() {
        new Button().click(NEW_TEST);
        return new TestCasePage();
    }

    public String getRepositoryName() {
        String text = $x(REPOSITORY_NAME).getText();
        return text.replaceAll("(\n0 cases 0 \\| 0 suites 0)", "");
    }
}
