package tests;

import baseEntities.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class JSTest extends BaseTest {

    @Test
    public void dragAndDropTest() {
        browsersService.getDriver().get("http://the-internet.herokuapp.com/add_remove_elements/");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement webElement = browsersService.getWaits().waitForVisibility(By.cssSelector("div > button"));

        ((JavascriptExecutor) browsersService.getDriver()).executeScript("arguments[0].click();", webElement);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Actions actions = new Actions(browsersService.getDriver());
        actions.moveToElement(webElement).build().perform();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
