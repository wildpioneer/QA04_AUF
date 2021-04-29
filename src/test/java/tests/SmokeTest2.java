package tests;

import baseEntities.BaseTest;
import core.BrowsersService;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import steps.LoginSteps;

import java.util.concurrent.TimeUnit;

public class SmokeTest2 extends BaseTest {

    @Test
    public void LoginTest() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        loginSteps.loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        Assert.assertEquals(browsersService.getDriver().getTitle(), "All Projects - TestRail");
    }

    @Test
    public void LoginTestWithIncorrectCredentials() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        LoginPage loginPage = loginSteps.loginWithIncorrectCredentials("test@gmail.com", "qweqwe");

        Assert.assertEquals(loginPage.getErrorText(),
                "Email/Login or Password is incorrect. Please try again.");
    }

    @Test
    public void AddNewProjectTes() {
        LoginSteps loginSteps = new LoginSteps(browsersService);
        DashboardPage dashboardPage = loginSteps
                .loginWithCorrectCredentials("atrostyanko+0401@gmail.com", "QqtRK9elseEfAk6ilYcJ");

        dashboardPage.getSidebarProjectsAddButton().click();

    }
}
