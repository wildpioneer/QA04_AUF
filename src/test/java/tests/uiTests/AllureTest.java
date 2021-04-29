package tests.uiTests;

import baseEntities.BaseTest;
import enums.ProjectType;
import io.qameta.allure.*;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.LoginSteps;
import steps.ProjectSteps;

@Epic("Main Epic")
public class AllureTest extends BaseTest {

    @Feature("Feature 1")
    @Story("Story 1")
    @Test(description = "Login Test")
    @Description("Проверка с корректными данными.")
    @Flaky
    @TmsLink("623")
    @Issue("AQ-123")
    @Link(name = "Test Link", url = "https://thumbs.dreamstime.com/z/funny-cartoon-bug-vector-illustration-cute-beetle-50577038.jpg")
    @Severity(SeverityLevel.BLOCKER)
    public void LoginTest() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        loginSteps.loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        Assert.assertEquals(browsersService.getDriver().getTitle(), "All Projects - TestRail");
    }

    @Features(value = {@Feature("Feature 1"), @Feature("Feature 2")})
    @Stories(value = {@Story("Story 1"), @Story("Story 2")})
    @Test(description = "Тест добавления проекта")
    @Severity(SeverityLevel.CRITICAL)
    public void AddNewProjectTest() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        loginSteps.loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        Project project = Project.builder()
                .name("Test Project AT")
                .announcement("Test Project Definition")
                .isShowAnnouncement(true)
                .type(ProjectType.MULTIPLE)
                .build();

        ProjectSteps projectSteps = new ProjectSteps(browsersService);
        projectSteps.AddProject(project);
    }
}
