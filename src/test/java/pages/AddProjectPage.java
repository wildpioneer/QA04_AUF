package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import wrappers.RadioButton;

import java.util.List;

public class AddProjectPage extends BasePage {
    protected By PAGEOPENEDIDENTIFIER = By.id("accept");

    protected By addProjectButtonSelector = By.id("accept");

    @FindBys({
            @FindBy(className = "form-group"),
            @FindBy(css = "input[name='name']")
    })
    public WebElement name;

    @FindBys({
            @FindBy(className = "form-group"),
            @FindBy(css = "input[name='name']")
    })
    public List<WebElement> nameList;

    @FindAll({
            @FindBy(className = "form-group"),
            @FindBy(css = "input[name='name']")
    })
    public List<WebElement> fullNameList;

    protected By suiteModeSingleSelector = By.id("suite_mode_single");
    protected By suiteModeSingleBaselineSelector = By.id("suite_mode_single_baseline");
    protected By suiteModeMultiSelector = By.id("suite_mode_multi");
    protected By radioButtonSelector = By.cssSelector("[name='suite_mode']");


    public AddProjectPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    public WebElement getAddProjectButton() {
        return driver.findElement(addProjectButtonSelector);
    }

    @Override
    protected void openPage() {
        
    }

    @Override
    public boolean isPageOpened() {
        return name.isDisplayed();
    }

    public RadioButton radioButton() {
        return new RadioButton(driver, radioButtonSelector);
    }

    public WebElement getSuiteModeSingleOption() {
        return driver.findElement(suiteModeSingleSelector);
    }

    public WebElement getSuiteModeSingleBaselineOption() {
        return driver.findElement(suiteModeSingleBaselineSelector);
    }

    public WebElement getSuiteModeMultiSelectorOption()  {
        return driver.findElement(suiteModeMultiSelector);
    }

}