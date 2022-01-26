package io.github.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.sleep;

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

    /**
     * select 操作
     *
     * @param number
     */
    private void select(String number) {
        SelenideElement arrow = element.find(By.xpath(".//div[@aria-autocomplete=\"list\"]"));
        String id = arrow.shouldBe(Condition.visible).getAttribute("aria-controls");
        element.find(By.xpath(".//i")).click();
        String pageXpath = String.format("//div[@id=\"%s\"]//li[contains(string(),'%s')]", id, number);
        SelenideElement option = xpath(pageXpath);
        option.shouldBe(Condition.visible).click();
    }


    public void selectOption(String optionName) {
        this.select(optionName);
    }


    public void sendKeysAndSelect(String keys, String optionName) {
        SelenideElement closeButton = this.element.find(By.xpath(".//i"));
        SelenideElement inputElement =this.element.find(By.xpath(".//input"));
        this.element.click();
        closeButton.click();
        this.element.click();
        inputElement.sendKeys(keys);
        SelenideElement list = element.find(By.xpath(".//div[@aria-autocomplete=\"list\"]"));
        String id = list.shouldBe(Condition.visible).getAttribute("aria-controls");
        String pageXpath = String.format("//div[@id=\"%s\"]//li[contains(string(),'%s')]", id, optionName);
        SelenideElement optionElement = xpath(pageXpath);
        optionElement.shouldBe(Condition.visible).click();
    }

    public ElementsCollection options(){
        SelenideElement list = element.find(By.xpath(".//div[@aria-autocomplete=\"list\"]"));
        String id = list.shouldBe(Condition.visible).getAttribute("aria-controls");
        String pageXpath = String.format("//div[@id=\"%s\"]//li", id);
        return getAllElements(pageXpath);
    }

    public int size(){
       return this.options().size();
    }
}
