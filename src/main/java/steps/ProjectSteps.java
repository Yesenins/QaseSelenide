package steps;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import objects.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HeaderPage;
import pages.NewProjectModalPage;
import pages.ProjectPage;
import pages.ProjectsListPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProjectSteps extends  BaseSteps{

    ProjectsListPage projectsListPage;
    ProjectPage projectPage;
    NewProjectModalPage newProjectModalPage;
    HeaderPage headerPage;

    public ProjectSteps() {
        this.projectsListPage = new ProjectsListPage();
        this.projectPage = new ProjectPage();
        this.newProjectModalPage = new NewProjectModalPage();
        this.headerPage = new HeaderPage();
    }

    @Step("Login by user: {email}")
    public ProjectSteps newProject(String projectName, String projectCode, String text) {
        projectsListPage
                .createNewProject()
                .createProject(projectName, projectCode, text)
                .isOpened();
        return this;
    }

    @Step
    public ProjectSteps createNewTest(TestCase testCase) {
        projectPage
                .isOpened()
                .openNewTest()
                .createTestCase(testCase)
                .isOpened();
        return this;
    }

    @Step
    public ProjectSteps deleteProject(String projectName) {
        headerPage
                .openProjectListPage()
                .deleteProject(projectName);
        return this;
    }

    @Step
    public ProjectSteps checkRepositoryName(String projectCode) {
        String expectedName = projectCode.toUpperCase() + " repository";
        Assert.assertEquals(projectPage.getRepositoryName(),expectedName);
        return this;
    }

    @Step
    public ProjectSteps isProjectDeleted() {
        projectsListPage.isOpened();
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//table//tbody//tr[2]")));
        ElementsCollection table = $$x("//table//tbody/tr");
        Assert.assertEquals(table.size(), 1);
        return this;
    }
}
