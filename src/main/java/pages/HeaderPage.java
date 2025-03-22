package pages;

import com.codeborne.selenide.SelenideElement;
import elements.Button;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderPage extends BasePage{

    private static final SelenideElement PROJECTS_BUTTON = $x("//a[text()='Projects']");

    public HeaderPage() {
    }

    public ProjectsListPage openProjectListPage() {
        new Button().click(PROJECTS_BUTTON);
        return new ProjectsListPage();
    }
}
