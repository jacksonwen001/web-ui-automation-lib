package io.github.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.github.components.enums.Elements;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * @author: Jackson.Wen
 */
public class Element {
    protected SelenideElement element;

    public Element() {
    }

    public Element(String xpathStr) {
        this.element = xpath(xpathStr);
    }

    public Element(SelenideElement element) {
        this.element = element;
    }

    public static SelenideElement byText(String text){
        return xpath(String.format("(//*[string()=\"%s\"])[last()]", text));
    }

    public static SelenideElement withText(String text) {
        return xpath(String.format("(//*[contains(string(), '%s')])[last()]", text));
    }

    public static SelenideElement byClass(String className) {
        return xpath(String.format("//*[@class=\"%s\"]", className));
    }

    public static SelenideElement withClass(String className) {
        return xpath(String.format("//*[contains(@class, '%s')]", className));

    }

    public static By by(String xpath) {
        return By.xpath(xpath);
    }

    /**
     * xpath for elements
     *
     * @param elements
     * @return
     */
    public static SelenideElement xpath(String elements) {
        return $(By.xpath(elements));
    }

    /**
     * Get All Elements By xpath
     *
     * @param element
     * @return 返回 SelenideElement 类
     */
    public static ElementsCollection getAllElements(String element) {
        return $$(By.xpath(element));
    }

    /**
     * Get Element By Id
     *
     * @param id
     * @return 返回 SelenideElement 类
     */
    public static SelenideElement id(String id) {
        return $(By.id(id));
    }


    public static SelenideElement button(String name) {
        return xpath(String.format(Elements.BUTTON.getXpath(), name));
    }

    public static SelenideElement span(String name) {
        return xpath(String.format(Elements.SPAN.getXpath(), name));
    }

    public static SelenideElement p(String name) {
        return xpath(String.format(Elements.P.getXpath(), name));
    }

    public static SelenideElement li(String name) {
        return xpath(String.format(Elements.LI.getXpath(), name));
    }

    public static SelenideElement a(String name) {
        return xpath(String.format(Elements.A.getXpath(), name));
    }

    public static SelenideElement div(String name) {
        return xpath(String.format(Elements.DIV.getXpath(), name));
    }

    public static SelenideElement td(String name) {
        return xpath(String.format(Elements.TD.getXpath(), name));
    }

    public static SelenideElement label(String name) {
        return xpath(String.format(Elements.LABEL.getXpath(), name));
    }

    public static SelenideElement h1(String name) {
        return xpath(String.format(Elements.H1.getXpath(), name));
    }

    public static SelenideElement h2(String name) {
        return xpath(String.format(Elements.H2.getXpath(), name));
    }

    public static SelenideElement h3(String name) {
        return xpath(String.format(Elements.H3.getXpath(), name));
    }

    public static SelenideElement h4(String name) {
        return xpath(String.format(Elements.H4.getXpath(), name));
    }
}
