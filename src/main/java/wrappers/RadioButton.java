package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RadioButton {
    private List<UIElement> optionsList = new ArrayList<>();
    private List<String> optionsTextList = new ArrayList<>();
    private WebDriver webDriver;

    public RadioButton(WebDriver webDriver, By by) {
        this.webDriver = webDriver;
        List<WebElement> options = webDriver.findElements(by);
        optionsTextList = getAllOptions(options);
    }

    private List<String> getAllOptions(List<WebElement> radioList) {
        List<String> resultList = new ArrayList<>();

        for (WebElement webElement : radioList) {
            UIElement uiElement = new UIElement(webDriver, webElement);
            optionsList.add(uiElement);
            resultList.add(uiElement.getParent().findElement(By.tagName("strong")).getText());
        }

        return resultList;
    }

    public void selectByOption(String optionName) {
        int index = optionsTextList.indexOf(optionName);
        optionsList.get(index).click();
    }

    public void selectByIndex(int index) {
        optionsList.get(index).click();
    }
}
