package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.Waits;

public class BrowsersService {
    private WebDriver driver = null;
    private DriverManagerType driverManagerType;
    private Waits waits;

    public BrowsersService() {
        ReadProperties readProperties = new ReadProperties();

        driver = new BrowsersService(readProperties.getBrowserName()).getDriver();
        waits = new Waits(driver);
    }

    public BrowsersService(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                driverManagerType = DriverManagerType.CHROME;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                driverManagerType = DriverManagerType.FIREFOX;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new FirefoxDriver();
                break;
            case "ie":
                driverManagerType = DriverManagerType.IEXPLORER;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new InternetExplorerDriver();
                break;
            case "safari":
                driverManagerType = DriverManagerType.SAFARI;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new SafariDriver();
                break;
            case "remote":
                break;
            case "edge":
                driverManagerType = DriverManagerType.EDGE;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Browser " + browserName + " is not supported.");
                //ToDo: Надо ли выбрасывать exception?
                break;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Waits getWaits() { return waits; }

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
