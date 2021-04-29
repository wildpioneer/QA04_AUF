package tests.uiTests;

import baseEntities.BaseTest;
import core.BrowsersService;
import enums.ProjectType;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.LoginSteps;
import steps.ProjectSteps;
import testData.StaticProvider;

public class ParamTest extends BaseTest {

    @Test(dataProvider = "Create Project", dataProviderClass = StaticProvider.class)
    public void createProjectTest(String projectName, ProjectType projectType) {
        System.out.println("Project Name: " + projectName);
        System.out.println("Project Type: " + projectType.toString());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Parameters({"first-name", "last-name"})
    @Test
    public void paramTest(@Optional("Alex1") String firstName, @Optional("Test") String lastName) {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
    }

    @Test
    public void  AddProjectTest() {
        Project project = Project.builder()
                .name("sdasdasd")
                .type(ProjectType.MULTIPLE)
                .build();

        LoginSteps loginSteps = new LoginSteps(browsersService);
        loginSteps.loginWithCorrectCredentials("dsdsd", "sasdasd");

        ProjectSteps projectSteps = new ProjectSteps(browsersService);
        projectSteps.AddProject(project);
    }
}
