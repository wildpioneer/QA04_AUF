package tests;

import baseEntities.BaseTest;
import enums.ProjectType;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import steps.LoginSteps;
import steps.ProjectSteps;

public class SmokeTest1 extends BaseTest {

    @Test (groups = "smoketest")
    public void LoginTest() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        loginSteps.loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        Assert.assertEquals(browsersService.getDriver().getTitle(), "All Projects - TestRail");
    }

    @Test (groups = "smoketest", description = "Login Test With Incorrect Credentials")
    public void LoginTestWithIncorrectCredentials() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        LoginPage loginPage = loginSteps.loginWithIncorrectCredentials("test@gmail.com", "qweqwe");

        Assert.assertEquals(loginPage.getErrorText(),
                "Email/Login or Password is incorrect. Please try again.");
    }

    @Test (enabled = false)
    public void AddNewProjectTes() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        DashboardPage dashboardPage = loginSteps
                .loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        dashboardPage.getSidebarProjectsAddButton().click();

        Project project = new Project();
        project.setName("Test Project AT");
        project.setAnnouncement("Test Project Definition");
        project.setShowAnnouncement(true);
        project.setType(ProjectType.MULTIPLE);

        ProjectSteps projectSteps = new ProjectSteps(browsersService);
        projectSteps.AddProject(project);
    }
}
