package tests;

import baseEntities.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.ProjectsPage;
import steps.LoginSteps;

import java.util.List;

public class ProjectsTest extends BaseTest {

    /*
    1. Найти элемент по тексту - подняться на верх до tr - спуститься до div.icon-small-delete - используя единый xpath
    2. Найти элемент по тексту - подняться на верх до tr - занести это в элемент и найти child элемент использую findElement
    3. Получить список всех tr из этой таблицы - пробежаться по каждому элементу и проверить на наличие необходимого текста
        если такой найдет - найти в нем child элемент div.icon-small-delete
     */

    @Test
    public void option1() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        loginSteps.loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        browsersService.getDriver().get("https://aqa04onl02.testrail.io/index.php?/admin/projects/overview");


        ProjectsPage projectsPage = new ProjectsPage(browsersService, false);
        projectsPage.getDeleteIconForElementInTable("ah_test_project").click();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void option2() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        loginSteps.loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        browsersService.getDriver().get("https://aqa04onl02.testrail.io/index.php?/admin/projects/overview");

        ProjectsPage projectsPage = new ProjectsPage(browsersService, false);
        System.out.println(projectsPage.projectTable.RowsCount());
        ///System.out.println(projectsPage.projectTable.(1));




        /*
        WebElement rowElement = projectsPage.getRowForElementInTable("ah_test_project");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rowElement.findElement(By.className("icon-small-delete")).click();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    @Test
    public void option3() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        loginSteps.loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        browsersService.getDriver().get("https://aqa04onl02.testrail.io/index.php?/admin/projects/overview");

        List<WebElement> list = browsersService.getDriver().findElements(By.cssSelector("table.grid tbody tr.hoverSensitive"));

        for (WebElement webElement: list) {
            if (webElement.findElement(By.tagName("a")).getText().equals("ah_test_project")) {
                webElement.findElement(By.className("icon-small-delete")).click();
                break;
            }
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
