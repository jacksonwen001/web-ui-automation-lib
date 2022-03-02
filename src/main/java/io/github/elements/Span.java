package io.github.elements;

import com.codeborne.selenide.SelenideElement;
import io.github.enums.ElementEnum;
import org.openqa.selenium.By;

public class Span extends BaseElements{
    /**
     * 通过名字寻找元素
     * @param name
     * @return
     */
    public static SelenideElement span(String name){
        String element = String.format(ElementEnum.SPAN.getName(), name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过 class 和 文本 寻找元素
     * @param className
     * @param name
     * @return
     */
    public static SelenideElement span(String className, String name){
        String element = String.format(ElementEnum.SPAN.getNameAndClass(), className, name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

}
