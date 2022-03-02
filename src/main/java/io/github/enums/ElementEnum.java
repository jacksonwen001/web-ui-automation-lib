package io.github.enums;

import lombok.Getter;

@Getter
public enum ElementEnum {
    DIV("(//div[contains(string(), '%s')])[last()]",
            "//div[@class=\"%s\" and contains(string(), '%s')]"),
    H1("(//h1[contains(string(), '%s')])[last()]",
            "//h1[@class=\"%s\" and contains(string(), '%s')]"),
    H2("(//h2[contains(string(), '%s')])[last()]",
            "//h2[@class=\"%s\" and contains(string(), '%s')]"),
    H3("(//h3[contains(string(), '%s')])[last()]",
            "//h3[@class=\"%s\" and contains(string(), '%s')]"),
    H4("(//h4[contains(string(), '%s')])[last()]",
            "//h4[@class=\"%s\" and contains(string(), '%s')]"),
    H5("(//h5[contains(string(), '%s')])[last()]",
            "//h5[@class=\"%s\" and contains(string(), '%s')]"),

    BUTTON("(//button[contains(string(), '%s')])[last()]",
            "//button[@class=\"%s\" and contains(string(), '%s')]"),
    SPAN("(//span[contains(string(), '%s')])[last()]",
            "//span[@class=\"%s\" and contains(string(), '%s')]"),
    LABEL("(//label[contains(string(), '%s')])[last()]",
            "//label[@class=\"%s\" and contains(string(), '%s')]"),
    TD("(//td[contains(string(), '%s')])[last()]",
            "//td[@class=\"%s\" and contains(string(), '%s')]"),
    P("(//p[contains(string(), '%s')])[last()]",
            "//p[@class=\"%s\" and contains(string(), '%s')]"),
    A("(//a[contains(string(), '%s')])[last()]",
            "//a[@class=\"%s\" and contains(string(), '%s')]"),
    TALE("(//table[contains(string(), '%s')])[last()]",
            "//table[@class=\"%s\" and contains(string(), '%s')]"),
    LI("(//li[contains(string(), '%s')])[last()]",
            "//li[@class=\"%s\" and contains(string(), '%s')]");


    private final String name;
    private final String nameAndClass;

    ElementEnum(String name, String nameAndClass) {
        this.name = name;
        this.nameAndClass = nameAndClass;
    }
}
