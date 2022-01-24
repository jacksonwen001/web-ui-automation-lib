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
     * @return
     */
    public static ElementsCollection getAllElements(String element) {
        return $$(By.xpath(element));
    }

    /**
     * Get Element By Id
     *
     * @param id
     * @return
     */
    public static SelenideElement id(String id) {
        return $(By.id(id));
    }

    /**
     * search element by exact text
     *
     * @param text
     * @return
     */
    public static SelenideElement getElementByText(String text) {
        return $(byText(text));
    }

    /**
     * search element by contained text (substring)
     *
     * @param text
     * @return
     */
    public static SelenideElement getElementWithText(String text) {
        return $(withText(text));
    }

    /**
     * select
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
