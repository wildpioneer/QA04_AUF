package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.Table;

public class ProjectsPage extends BasePage {
    String deleteIconForElementInTableSelector = "//a[text()='remove']/ancestor::tr/descendant::div[@class='icon-small-delete']";

    String rowElementInTableSelector = "//a[text()='remove']/ancestor::tr";

    protected static final By tableBy = By.className("grid");

    public ProjectsPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {

    }

    @Override
    public boolean isPageOpened() {
        return true;
    }

    public WebElement getDeleteIconForElementInTable(String projectName) {
        return browsersService
                .getWaits()
                .waitForVisibility(
                        By.xpath(deleteIconForElementInTableSelector.replace("remove", projectName)
                        )
                );
    }

    public WebElement getRowForElementInTable(String projectName) {
        return browsersService
                .getWaits()
                .waitForVisibility(
                        By.xpath(rowElementInTableSelector.replace("remove", projectName)
                        )
                );

    }

    public Table projectTable = new Table(driver, tableBy);
}
