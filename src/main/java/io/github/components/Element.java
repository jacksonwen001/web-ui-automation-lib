package io.github.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
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

    /**
     *  寻找 button 元素
     * @param name
     * @return 返回  button 元素
     */
    public static SelenideElement button(String name) {
        String str = String.format("//button[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     *  div 元素
     * @param name
     * @return 返回  div 元素
     */
    public static SelenideElement div(String name) {
        String str = String.format("//div[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     * 返回  p 元素
     * @param name
     * @return 返回  button 元素
     */
    public static SelenideElement p(String name) {
        String str = String.format("//p[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     * 返回  span 元素
     * @param name
     * @return 返回  span 元素
     */
    public static SelenideElement span(String name) {
        String str = String.format("//span[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     * 返回  label 元素
     * @param name
     * @return 返回  label 元素
     */
    public static SelenideElement label(String name) {
        String str = String.format("//label[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     * 返回  a 元素
     * @param name
     * @return 返回  a 元素
     */
    public static SelenideElement a(String name) {
        String str = String.format("//a[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     * 返回  h1 元素
     * @param name
     * @return 返回  h1 元素
     */
    public static SelenideElement h1(String name) {
        String str = String.format("//h1[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     * 返回  h2 元素
     * @param name
     * @return 返回  h2 元素
     */
    public static SelenideElement h2(String name) {
        String str = String.format("//h2[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     * 返回  h3 元素
     * @param name
     * @return 返回  h3 元素
     */
    public static SelenideElement h3(String name) {
        String str = String.format("//h3[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     * 返回  h4 元素
     * @param name
     * @return 返回  h4 元素
     */
    public static SelenideElement h4(String name) {
        String str = String.format("//h4[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     * 返回  td 元素
     * @param name
     * @return 返回  td 元素
     */
    public static SelenideElement td(String name) {
        String str = String.format("//td[contains(string(), '%s')]", name);
        return xpath(str);
    }

    /**
     * select 操作
     *
     * @param arrow
     * @param number
     */
    protected void select(SelenideElement arrow, String number) {
        String id = arrow.shouldBe(Condition.visible).getAttribute("aria-controls");
        arrow.find(By.xpath(".//i")).click();
        String pageXpath = String.format("//div[@id=\"%s\"]//li[string()=%s]", id, number);
        SelenideElement option = xpath(pageXpath);
        option.shouldBe(Condition.visible).click();
    }
}
