package steps;

import baseEntities.BaseStep;
import core.BrowsersService;
import models.Project;
import pages.AddProjectPage;
import pages.DashboardPage;

import java.sql.Driver;

public class ProjectSteps extends BaseStep {

    public ProjectSteps(BrowsersService browsersService) {
        super(browsersService);
    }

    public void AddProject(Project project) {
/*
        DashboardPage dashboardPage = new DashboardPage(browsersService, false);
        dashboardPage.getSidebarProjectsAddButton().click();
*/

        AddProjectPage addProjectPage = new AddProjectPage(browsersService, false);
        addProjectPage.name.sendKeys(project.getName());

        switch (project.getType()) {
            case SINGLE_FOR_ALL_CASES:
                addProjectPage.getSuiteModeSingleOption().click();
                break;
            case SINGLE_WITH_BASELINE:
                addProjectPage.getSuiteModeSingleBaselineOption().click();
                break;
            case MULTIPLE:
                addProjectPage.getSuiteModeMultiSelectorOption().click();
                break;
        }

        System.out.println(addProjectPage.nameList.size());
        System.out.println(addProjectPage.fullNameList.size());

        addProjectPage.getAddProjectButton().submit();
    }

    public void UpdateProject(Project project) {

    }

    public void DeleteProject(Project project) {

    }
}
