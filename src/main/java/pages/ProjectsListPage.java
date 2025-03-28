package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProjectsListPage extends BasePage {

    private static final SelenideElement CREATE_NEW_PROJECT_BUTTON = $x("//*[text()='Create new project']");
//    private static final SelenideElement ACTION_MENU = $x("//*[text()='Main Project']/ancestor::tr//*[@aria-label=\"Open action menu\"]");
    private static final String ACTION_MENU = "//*[text()='%s']/ancestor::tr//*[@aria-label=\"Open action menu\"]";
    private static final SelenideElement DELETE_BUTTON = $x("//*[@data-testid=\"remove\"]");
    private static final SelenideElement MODAL_WINDOW = $x("//*[@id='modals']");
    private static final SelenideElement DELETE_BUTTON_ON_MODAL = $x("//span[text()='Delete project']");

    public ProjectsListPage isOpened() {
        waiters.waitForPageLoaded();
        CREATE_NEW_PROJECT_BUTTON.shouldBe(Condition.visible);
        return this;
    }

    public NewProjectModalPage createNewProject() {
        new Button().click(CREATE_NEW_PROJECT_BUTTON);
        waiters.waitForPageLoaded();
        return new NewProjectModalPage();
    }

    public ProjectsListPage open(String url) {
        open(url);
        return this;
    }

    public ProjectsListPage deleteProject () {
        DELETE_BUTTON.shouldBe(Condition.visible);
        new Button().click(DELETE_BUTTON);
        MODAL_WINDOW.shouldBe(Condition.visible);
        new Button().click(DELETE_BUTTON_ON_MODAL);
        MODAL_WINDOW.shouldNotBe(Condition.visible);
        return this;
    }

    public ProjectsListPage deleteProjectByName(String projectName) {
        new Button().click($x(String.format(ACTION_MENU, projectName)));
        deleteProject();
        return this;
    }


    public ProjectsListPage deleteLastProject() {
        new Button().click(($x("//table//tbody/tr[2]//*[@aria-label=\"Open action menu\"]")));
        deleteProject();
        return this;
    }
}
