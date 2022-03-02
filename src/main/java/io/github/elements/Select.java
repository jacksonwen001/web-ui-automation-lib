package io.github.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Select {
    private SelenideElement element;
    private SelenideElement controllerElement;
    private SelenideElement arrowElement;

    public Select(String idName){
        this.element = BaseElements.id(idName);
        this.controllerElement = this.element.find(By.xpath(".//div[@aria-autocomplete=\"list\"]"));
        this.arrowElement = this.element.find(By.xpath(".//i"));
    }

    /**
     * 需要输入字符后才出现下拉选项
     * @param text
     */
    public Select send(String text) {
        SelenideElement closeButton = this.element.find(By.xpath(".//i"));
        SelenideElement inputElement = this.element.find(By.xpath(".//input"));
        this.element.click();
        closeButton.click();
        this.element.click();
        inputElement.sendKeys(text);
        return this;
    }

    /**
     * 需要点击才会出现下拉框
     * @return
     */
    public Select click(){
        element.find(By.xpath(".//i")).click();
        return this;
    }


    /**
     * 选择第一个
     */
    public void selectFirst(){
        String controlId = this.controllerElement
                .shouldBe(Condition.visible).getAttribute("aria-controls");
        String pageXpath = String.format("(//div[@id=\"%s\"]//li[@role=\"option\"])[last()]", controlId);
        SelenideElement option = $(By.xpath(pageXpath));
        option.shouldBe(Condition.visible).click();
    }

    /**
     * 选择含某些字符的第一个选项
     * @param text
     */
    public void selectContains(String text){
        String controlId = this.controllerElement
                .shouldBe(Condition.visible).getAttribute("aria-controls");
        String pageXpath = String.format("(//div[@id=\"%s\"]//li[@role=\"option\" and string() = '%s'])[last()]", controlId, text);
        SelenideElement option = $(By.xpath(pageXpath));
        option.shouldBe(Condition.visible).click();
    }

    /**
     * 选择不含某些字符的第一个选项
     * @param text
     */
    public void selectNoContains(String text) {
        String controlId = this.controllerElement
                .shouldBe(Condition.visible).getAttribute("aria-controls");
        String pageXpath = String.format("(//div[@id=\"%s\"]//li[@role=\"option\" and not(contains(string(),'%s'))])[last()]", controlId, text);
        SelenideElement option = $(By.xpath(pageXpath));
        option.shouldBe(Condition.visible).click();
    }

    /**
     * 选择第 num 个 选择
     * @param num
     */
    public void select(int num){
        String controlId = this.controllerElement
                .shouldBe(Condition.visible).getAttribute("aria-controls");
        String pageXpath = String.format("(//div[@id=\"%s\"]//li[@role=\"option\"])[last()]", controlId);
        ElementsCollection option = $$(By.xpath(pageXpath));
        option.get(num).shouldBe(Condition.visible).click();
    }


}
