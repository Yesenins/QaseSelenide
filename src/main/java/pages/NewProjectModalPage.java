package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;

import static com.codeborne.selenide.Selenide.$x;

public class NewProjectModalPage extends BasePage{

    private static final SelenideElement CREATE_PROJECT = $x("//*[text()='Create project']");
    public NewProjectModalPage() {
    }

    public NewProjectModalPage isOpened() {
        CREATE_PROJECT.shouldBe(Condition.visible);
        return this;
    }

//    private NewProjectModalPage fillNewProjectForm(String projectName, String projectCode, String text) {
//        isOpened();
//        new Input("project-name").writeNewProject(projectName);
//        new Input("project-code")
//                .clearProject()
//                .writeNewProject(projectCode);
//        new Input("description-area").writeNewProject(text);
//        new Button().click(CREATE_PROJECT);
//        return this;
//    }

    private NewProjectModalPage fillNewProjectForm(String projectName, String projectCode, String text) {
        isOpened();
        new Input("project-name").writeNewProject(projectName);
        new Input("project-code")
                .clearProject()
                .writeNewProject(projectCode);
        new Input("description-area").writeNewProject(text);
        new Button().click(CREATE_PROJECT);
        return this;
    }

    public ProjectPage createProject(String projectName, String projectCode, String text) {
        fillNewProjectForm(projectName, projectCode, text);
        waiters.waitForPageLoaded();
        return new ProjectPage();
    }

}
