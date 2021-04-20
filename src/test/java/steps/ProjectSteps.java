package steps;

import baseEntities.BaseStep;
import core.BrowsersService;
import models.Project;
import pages.AddProjectPage;

import java.sql.Driver;

public class ProjectSteps extends BaseStep {

    public ProjectSteps(BrowsersService browsersService) {
        super(browsersService);
    }

    public void AddProject(Project project) {
        AddProjectPage addProjectPage = new AddProjectPage(browsersService, false);
        addProjectPage.getAddProjectButton().click();


        // ЗАйти на страницу
        // Заполнить форму
        // Сохранить запись
    }

    public void UpdateProject(Project project) {

    }

    public void DeleteProject(Project project) {

    }
}
