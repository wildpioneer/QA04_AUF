package steps;

import baseEntities.BaseStep;
import core.BrowsersService;
import io.qameta.allure.Step;
import models.Singleton;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginSteps extends BaseStep {

    private LoginPage loginPage;

    public LoginSteps(BrowsersService browsersService) {
        super(browsersService);
        loginPage = new LoginPage(browsersService, true);
    }

    @Step("Логирование с корректными '{email}' '{psw}'")
    public DashboardPage loginWithCorrectCredentials(String email, String psw) {
        setEmail(email);
        setPsw(psw);
        clickBtn();

        return new DashboardPage(browsersService, false);
    }

    @Step
    public LoginPage loginWithIncorrectCredentials(String email, String psw) {
        setEmail(email);
        setPsw(psw);
        clickBtn();

        return new LoginPage(browsersService, false);
    }

    private LoginSteps setEmail(String username) {
        loginPage.getEmailInput().sendKeys(username);

        return this;
    }

    private LoginSteps setPsw(String psw) {
        loginPage.getPasswordInput().sendKeys(psw);

        return this;
    }

    private LoginSteps clickBtn() {
        loginPage.getLogInButton().click();

        return this;
    }

    public LoginPage getCurrentLoginPage() {
        return loginPage;
    }
}
