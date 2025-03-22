package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Dropdown;
import elements.Input;
import objects.TestCase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestCasePage extends BasePage {

    private static final SelenideElement PAGE_NAME = $x("//*[text()='Create test case']");
    private static final SelenideElement SAVE_BUTTON = $(By.id("save-case"));
    private static final SelenideElement SAVE_AND_CREATE_ANOTHER_BUTTON = $x("//*[text()='Save and create another']");
    private static final SelenideElement CANCEL_BUTTON = $x("//*[text()='Cancel']");
    private static final SelenideElement CHECKBOX_TO_BE_AUTOMATED = $x("//*[text()='To be automated']/../span");

    public TestCasePage() {
    }

    public TestCasePage isOpened() {
        PAGE_NAME.shouldBe(Condition.visible);
        return this;
    }

    public TestCasePage fillCreateTestCaseForm(TestCase testCase) {

        new Input("Title").writeTitleTestCase(testCase.getTitle());
        new Dropdown("Status").dropdownSelectOptions(testCase.getStatus());
//        new Dropdown("Suite").dropdownSelectOptions(testCase.getSuite());
        new Dropdown("Severity").dropdownSelectOptions(testCase.getSeverity());
        new Dropdown("Priority").dropdownSelectOptions(testCase.getPriority());
        new Dropdown("Type").dropdownSelectOptions(testCase.getType());
        new Dropdown("Layer").dropdownSelectOptions(testCase.getLayer());
        new Dropdown("Is flaky").dropdownSelectOptions(testCase.getIsFlaky());
//        new Dropdown("Milestone").dropdownSelectOptions(testCase.getMilestone());
        new Dropdown("Behavior").dropdownSelectOptions(testCase.getBehavior());
        new Dropdown("Automation status").dropdownSelectOptions(testCase.getAutomationStatus());
        new Input("Description").writeTestCase(testCase.getDescription());
        new Button().click(CHECKBOX_TO_BE_AUTOMATED);
        new Input("Pre-conditions").writeTestCase(testCase.getPreConditions());
        new Input("Post-conditions").writeTestCase(testCase.getPostConditions());
        new Button().click(SAVE_BUTTON);

        return this;
    }

    public ProjectPageWithTests createTestCase(TestCase testCase) {
        fillCreateTestCaseForm(testCase);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = Selenide.switchTo().alert();
            alert.accept();
        } catch (TimeoutException e) {

        }
        return new ProjectPageWithTests();
    }
}
