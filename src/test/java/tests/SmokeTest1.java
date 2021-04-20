package tests;

import baseEntities.BaseTest;
import core.UIMethods;
import enums.ProjectType;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashboardPage;
import pages.LoginPage;
import steps.LoginSteps;
import steps.ProjectSteps;
import wrappers.UIElement;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SmokeTest1 extends BaseTest {

    @Test(groups = {"smoke", "regression"}, timeOut = 5000l)
    public void LoginTest() {
/*
        1. Запустить драйвер
        2. Перейти на сайт
        3. Ввести логин
        4. Ввести пароль
        5. Нажать Login
        6. Dashboard page отобразился
*/
        LoginSteps loginSteps = new LoginSteps(browsersService);
        loginSteps.loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        Assert.assertEquals(browsersService.getDriver().getTitle(), "All Projects - TestRail");
    }

    @Test(groups = {"regression", "smoke"}, description = "Логин с некорректными данными")
    public void LoginTestWithIncorrectCredentials() {
/*
        1. Запустить драйвер
        2. Перейти на сайт
        3. Ввести логин
        4. Ввести пароль
        5. Нажать Login
        6. Dashboard page отобразился
*/
        LoginSteps loginSteps = new LoginSteps(browsersService);
        LoginPage loginPage = loginSteps.loginWithIncorrectCredentials("test@gmail.com", "qweqwe");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(loginPage.getErrorText(),
                "Email/Login or Password is incorrect. Please try again.1");
        softAssert.assertEquals(loginPage.getErrorText(),
                "Email/Login or Password is incorrect. Please try again.2");
        softAssert.assertAll();
    }

    @Test(enabled = false)
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

    @Test
    public void waitTest() {
        LoginPage loginPage = new LoginPage(browsersService, true);
        loginPage.getEmailInput().sendKeys("atrostyanko+0401@gmail.com");
        loginPage.getPasswordInput().sendKeys("QqtRK9elseEfAk6ilYcJ");

        UIElement webElement1 = new UIElement(browsersService.getDriver(), By.id("button_primary"));

        //UIMethods.Click(browsersService.getDriver(), loginPage.getLogInButton());
        webElement1.click();


        loginPage.getLogInButton().click();

        long start = new Date().getTime();
        WebElement element = waits.waitForVisibility(
                new DashboardPage(browsersService, false).getSidebarProjectsAddButton());
        long stop = new Date().getTime();
        System.out.println("Duration:" + (stop - start));

        Assert.assertTrue(element.isDisplayed());

        browsersService.getDriver().navigate().refresh();

        Wait<WebDriver> fluent = new FluentWait<>(browsersService.getDriver())
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(600, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        start = new Date().getTime();
        WebElement foo = fluent.until(driver -> browsersService.getDriver().findElement(By.id("sidebar-projects-add")));
        stop = new Date().getTime();
        System.out.println("Duration:" + (stop - start));

        Assert.assertTrue(foo.isDisplayed());

        browsersService.getDriver().navigate().refresh();

        WebElement foo1 = fluent.until(ExpectedConditions.visibilityOfElementLocated(By.id("sidebar-projects-add")));
        Assert.assertTrue(foo1.isDisplayed());
    }

    @Test
    public void actionTest() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        DashboardPage dashboardPage = loginSteps
                .loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        dashboardPage.getSidebarProjectsAddButton().click();

        WebElement icon = waits.waitForVisibility(By.className("icon-markdown-table"));
        WebElement anon = waits.waitForVisibility(By.id("announcement"));

        Actions actions = new Actions(browsersService.getDriver());
        actions.moveToElement(icon).build().perform();
        Assert.assertTrue(waits.waitForAttributeContains(By.id("tooltip"), "style", "display: block;"));

        actions
                .moveToElement(anon)
                .click()
                .sendKeys("Test")
                .keyDown(Keys.COMMAND)
                .sendKeys("a")
                .keyUp(Keys.COMMAND)
                .keyDown(Keys.COMMAND)
                .sendKeys("c")
                .keyUp(Keys.COMMAND)
                .sendKeys(Keys.ARROW_RIGHT)
                .keyDown(Keys.COMMAND)
                .sendKeys("v")
                .keyUp(Keys.COMMAND)
                .build()
                .perform();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void wrapperTest() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        DashboardPage dashboardPage = loginSteps
                .loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        browsersService.getDriver().get("https://aqa04onl02.testrail.io/index.php?/suites/edit/545/1");

        UIElement button = new UIElement(browsersService.getDriver(), By.className("icon-markdown-table"));
        UIElement button1 = new UIElement(browsersService.getDriver(), By.className("icon-markdown-table"));
        button.hover();
        button.dragAndDrop(button1);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
