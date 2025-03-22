package elements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class Dropdown {
    String label;

    private static final String DROPDOWN_XPATH = "//*[text()='%s']/../div";
    private static final String DROPDOWN_OPTIONS_XPATH = "//*[text()='%s']";

    public Dropdown(String label) {
        this.label = label;
    }

    public void dropdownSelectOptions(String option) {
        $x(String.format(DROPDOWN_XPATH,label)).click();
        $x(String.format(DROPDOWN_OPTIONS_XPATH,option)).shouldBe(Condition.visible).click();
    }
}
