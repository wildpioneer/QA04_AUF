package tests;

import baseEntities.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import steps.LoginSteps;
import wrappers.RadioButton;

public class RadioTest extends BaseTest {

    @Test
    public void radioTest() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        loginSteps.loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        browsersService.getDriver().get("https://aqa04onl03.testrail.io/index.php?/admin/projects/add/1");

        RadioButton radioButton = new RadioButton(browsersService.getDriver(), By.name("suite_mode"));
        radioButton.selectByOption("Use a single repository with baseline support");
    }
}
