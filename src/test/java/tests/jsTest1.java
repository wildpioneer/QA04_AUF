package tests;

import baseEntities.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class jsTest1 extends BaseTest {

    @Test
    public void jsTest() {
        WebDriver driver = browsersService.getDriver();

        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        WebElement webElement = driver.findElement(By.xpath("//button[@onclick='addElement()']"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

        javascriptExecutor.executeScript("arguments[0].click();", webElement);

        //javascriptExecutor.executeScript("alert('Всем Привет!!!')");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jsTest1() {
        WebDriver driver = browsersService.getDriver();

        driver.get("http://the-internet.herokuapp.com/infinite_scroll");

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0, 1600);");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
