package io.github.components;

import com.codeborne.selenide.SelenideElement;

/**
 * @author: Jackson.Wen
 */
public class Select extends Element {
    private final SelenideElement element;

    public Select(String id) {
        this.element = id(id);
    }

    public Select(SelenideElement element) {
        this.element = element;
    }

    public void selectOption(String optionName) {
        this.select(this.element, optionName);
    }
}
