package io.github.components.enums;

import lombok.Getter;

/**
 * @author: Jackson.Wen
 */
@Getter
public enum Elements {
    DIV("(//div[contains(string(), '%s')])[last()]"),
    H1("(//h1[contains(string(), '%s')])[last()]"),
    H2("(//h2[contains(string(), '%s')])[last()]"),
    H3("(//h3[contains(string(), '%s')])[last()]"),
    H4("(//h4[contains(string(), '%s')])[last()]"),
    H5("(//h5[contains(string(), '%s')])[last()]"),
    BUTTON("(//button[contains(string(), '%s')])[last()]"),
    SPAN("(//span[contains(string(), '%s')])[last()]"),
    LABEL("(//label[contains(string(), '%s')])[last()]"),
    TD("(//td[contains(string(), '%s')])[last()]"),
    P("(//p[contains(string(), '%s')])[last()]"),
    A("(//a[contains(string(), '%s')])[last()]"),
    LI("(//li[contains(string(), '%s')])[last()]");

    private String xpath;
    Elements(String xpath){
        this.xpath = xpath;
    }
}
