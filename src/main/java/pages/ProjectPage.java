package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage extends BasePage{

    private static final SelenideElement NEW_TEST = $x("//*[text()='New test']");
    private static final String REPOSITORY_NAME = "//*[@id='application-content']//h1";
    private static final String SUITE_NAME = "//*[@data-suite-body-id=\"1\"]//h3";
    private static final String SUITE_DESCRIPTION = SUITE_NAME + "//ancestor::*//p";
    private static final SelenideElement ADD_SUITE_BUTTON = $x("//*[text()='Add suite']");
    private static final SelenideElement DELETE_SUITE_BUTTON = $x("//*[@aria-label=\"Delete suite\"]");
    private static final SelenideElement TEST_NAME = $x("//h1/div/span");
    private static final SelenideElement SUITE_QUANTITY = $x("//h1//ui-reset");
    private static final SelenideElement MODAL_WINDOW = $x("//*[@id='modals']");
    private static final SelenideElement DELETE_BUTTON_ON_MODAL = $x("//span[text()='Delete']");
    private static final String TEST_GENERAL_LABELS = "//*[text()='%s']/..//p";
    private static final String OPEN_TEST_CART = "//div[text()='%s']";
    private static final SelenideElement CLOSE_TEST_CART = $x("//*[@data-icon=\"xmark-large\"]/descendant::*");

    public ProjectPage() {
    }

    public ProjectPage isOpened() {
       NEW_TEST.shouldBe(Condition.visible);
        return this;
    }

    public TestCasePage openNewTest() {
        new Button().click(NEW_TEST);
        waiters.waitForPageLoaded();
        return new TestCasePage();
    }

    public NewSuiteModalPage addSuite() {
        new Button().click(ADD_SUITE_BUTTON);
        waiters.waitForPageLoaded();
        return new NewSuiteModalPage();
    }

    public String getRepositoryName() {
        String text = $x(REPOSITORY_NAME).getText();
        return text.replaceAll("\n0 cases 0 \\| 0 suites 0", "");
    }

    public String getSuiteName() {
        return  $x(SUITE_NAME).getText();
    }

    public String getTestName() {
        return  TEST_NAME.getText();
    }

    public String getTestInformation(String label) {
        return  $x(String.format(TEST_GENERAL_LABELS, label)).getText();
    }

    public String getSuiteDescription() {
        return  $x(SUITE_DESCRIPTION).getText();
    }

    public String getSuiteQuantity() {
        String text = SUITE_QUANTITY.getText();
        return text.replaceAll("0 cases 0 \\| 0 suites ", "");
    }

    public ProjectPage openTestCart(String nameTest) {
        new Button().click($x(String.format(OPEN_TEST_CART, nameTest)));
        TEST_NAME.shouldBe(Condition.visible);
        return this;
    }

    public ProjectPage closeTestCart() {
        new Button().click(CLOSE_TEST_CART);
        return this;
    }

    public ProjectPage deleteSuite() {
        DELETE_SUITE_BUTTON.shouldBe(Condition.visible);
        new Button().click(DELETE_SUITE_BUTTON);
        MODAL_WINDOW.shouldBe(Condition.visible);
        new Button().click(DELETE_BUTTON_ON_MODAL);
        return this;
    }
}
