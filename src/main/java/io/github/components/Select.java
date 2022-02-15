package io.github.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

/**
 * @author: Jackson.Wen
 */
public class Select extends Element {
    private final SelenideElement element;
    private String id;

    public Select(String id) {
        this.id = id;
        this.element = id(id);
    }

    public Select(SelenideElement element) {
        this.element = element;
    }

    private String getControllerId(){
        SelenideElement arrow = element.find(By.xpath(".//div[@aria-autocomplete=\"list\"]"));
        String controlId = arrow.shouldBe(Condition.visible).getAttribute("aria-controls");
        return controlId;
    }

    public void clickArrow() {
        element.find(By.xpath(".//i")).click();
    }

    private void select(SelenideElement option){
        element.find(By.xpath(".//i")).click();
        option.shouldBe(Condition.visible).click();
    }

    public void select(String number) {
        String controlId = getControllerId();
        String pageXpath = String.format("(//div[@id=\"%s\"]//li[@role=\"option\" and string() = '%s'])[last()]", controlId, number);
        SelenideElement option = xpath(pageXpath);
        select(option);
    }

    public void selectNotContains(String content) {
        String controlId = getControllerId();
        String pageXpath = String.format("(//div[@id=\"%s\"]//li[@role=\"option\" and not(contains(string(),'%s'))])[last()]", controlId, content);
        SelenideElement option = xpath(pageXpath);
        select(option);
    }
    public void selectContains(String number) {
        String controlId = getControllerId();
        String pageXpath = String.format("(//div[@id=\"%s\"]//li[@role=\"option\" and contains(string(),'%s')])[last()]", controlId, number);
        SelenideElement option = xpath(pageXpath);
        select(option);
    }


    public void sendKeysAndSelect(String keys, String optionName) {
        SelenideElement closeButton = this.element.find(By.xpath(".//i"));
        SelenideElement inputElement = this.element.find(By.xpath(".//input"));
        this.element.click();
        closeButton.click();
        this.element.click();
        inputElement.sendKeys(keys);
        String id = getControllerId();
        String pageXpath = String.format("//div[@id=\"%s\"]//li[@role=\"option\" and contains(string(),'%s')]", id, optionName);
        SelenideElement optionElement = xpath(pageXpath);
        optionElement.shouldBe(Condition.visible).click();
    }

    public ElementsCollection options() {
        SelenideElement list = element.find(By.xpath(".//div[@aria-autocomplete=\"list\"]"));
        String id = list.shouldBe(Condition.visible).getAttribute("aria-controls");
        String pageXpath = String.format("//div[@id=\"%s\"]//li[@role=\"option\"]", id);
        return getAllElements(pageXpath);
    }

    public int size() {
        return this.options().size();
    }
}
