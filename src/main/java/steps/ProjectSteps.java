package steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import objects.TestCase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HeaderPage;
import pages.NewProjectModalPage;
import pages.ProjectPage;
import pages.ProjectsListPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProjectSteps extends  BaseSteps{

    ProjectsListPage projectsListPage;
    ProjectPage projectPage;
    NewProjectModalPage newProjectModalPage;
    HeaderPage headerPage;
    int projectsQuantity;

    public ProjectSteps() {
        this.projectsListPage = new ProjectsListPage();
        this.projectPage = new ProjectPage();
        this.newProjectModalPage = new NewProjectModalPage();
        this.headerPage = new HeaderPage();
    }

    @Step
    public int getProjectsQuantity() {
        projectsListPage.isOpened();
//        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//table//tbody//tr[2]")));
        ElementsCollection table = $$x("//table//tbody/tr");
        table.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
        //найти как сделать ожидалку с коллекцией
        System.out.println(table.size());
        return table.size();
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
    public ProjectSteps addSuite (String suiteName,String text, String preconditions) {
        projectPage
                .isOpened()
                .addSuite()
                .createSuite(suiteName,text,preconditions);
        return this;
    }

    @Step
    public ProjectSteps checkSuiteInformation(String suiteName, String text) {
        Assert.assertEquals(projectPage.getSuiteName(), suiteName);
        Assert.assertEquals(projectPage.getSuiteDescription(), text);
        return this;
    }

    @Step
    public ProjectSteps checkTestInformation(TestCase testCase) {
        projectPage
                .closeTestCart()
                .openTestCart(testCase.getTitle());
        Assert.assertEquals(projectPage.getTestName(), testCase.getTitle());
        Assert.assertEquals(projectPage.getTestInformation("Description"), testCase.getDescription());
        Assert.assertEquals(projectPage.getTestInformation("Pre-conditions"), testCase.getPreConditions());
        Assert.assertEquals(projectPage.getTestInformation("Post-conditions"), testCase.getPostConditions());
        return this;
    }

    @Step
    public ProjectSteps deleteProject(String projectName) {
        headerPage
                .openProjectListPage()
                .deleteProjectByName(projectName);
        return this;
    }

    @Step
    public ProjectSteps checkRepositoryName(String projectCode) {

        String expectedName = projectCode.toUpperCase() + " repository";
        Assert.assertEquals(projectPage.getRepositoryName(),expectedName);
        return this;
    }

    @Step
    public ProjectSteps isProjectDeleted(int projectsQuantity) {
          projectsListPage.isOpened();
//        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//table//tbody//tr[2]")));
//        ElementsCollection table = $$x("//table//tbody/tr");
        if(projectsQuantity > 1){
            projectsQuantity--;
        }
        Assert.assertEquals(getProjectsQuantity(), projectsQuantity);
        return this;
    }

    @Step
    public ProjectSteps isSuiteDeleted() {
        projectPage.isOpened();
        Assert.assertEquals(projectPage.getSuiteQuantity(), "0");
        return this;
    }

    @Step
    public ProjectSteps deleteSuite() {
        projectPage.isOpened();
        projectPage.deleteSuite();
        return this;
    }

    @Step
    public ProjectSteps checkQuantityOfProjects(int projectsQuantity) {
        if (projectsQuantity > 1) {
            projectsListPage.deleteLastProject();
        }
        return this;
    }
}
