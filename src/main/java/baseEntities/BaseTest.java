package baseEntities;

import core.BrowsersService;
import core.ReadProperties;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    protected BrowsersService browsersService;
    protected ReadProperties readProperties;

    @BeforeSuite
    public void setupSuite() {
        System.out.println("BeforeSuite: ");
    }

    @BeforeGroups
    public void setupGroup() {
        Reporter.log("BeforeGroups: ");
        System.out.println("BeforeGroups: ");
        browsersService = new BrowsersService();
    }

    @BeforeTest
    public void setupTest() {
        Reporter.log("Reporter -> BeforeTest: ");
        System.out.println("BeforeTest: ");
        readProperties = new ReadProperties();
    }

    @BeforeClass
    public void setupClass() {
        System.out.println("BeforeClass: ");
    }

    @BeforeMethod
    public void setupMethod() {
        System.out.println("BeforeMethod: ");
        browsersService = new BrowsersService();
        browsersService.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        browsersService.getDriver().get(readProperties.getURL());
    }

    @AfterMethod
    public void tearDownMethod() {
        System.out.println("AfterMethod: ");
        browsersService.getDriver().quit();
        browsersService = null;
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println("AfterClass: ");
    }

    @AfterTest
    public void tearDownTest() {
        System.out.println("AfterTest: ");
    }

    @AfterGroups
    public void tearDownGroups() {
        System.out.println("AfterGroups: ");
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("AfterSuite: ");
    }
}
