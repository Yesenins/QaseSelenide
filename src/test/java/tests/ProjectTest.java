package tests;

import objects.TestCase;
import org.testng.annotations.Test;
import pages.TestCasePage;

public class ProjectTest extends BaseTest{

    @Test
    public void createProjectTest() {
        TestCase testCase = new TestCase();
        testCase.setTitle("Authorization");
        testCase.setStatus("Actual");
        testCase.setDescription("authorize in page lalala");
//        testCase.setSuite();
        testCase.setSeverity("Major");
        testCase.setPriority("Medium");
        testCase.setType("Usability");
        testCase.setLayer("E2E");
        testCase.setIsFlaky("No");
//        testCase.setMilestone();
        testCase.setBehavior("Positive");
        testCase.setAutomationStatus("Manual");
        testCase.setPreConditions("preConditions");
        testCase.setPostConditions("postConditions");
        loginSteps.login(USER,PASSWORD,LOGIN_URL);
        projectSteps
                .newProject("Main Project", "WoW", "description ahahaha")
                .checkRepositoryName("WoW")
                .createNewTest(testCase)
                .deleteProject("Main Project")
                .isProjectDeleted();
    }
}
