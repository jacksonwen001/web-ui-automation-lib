package io.github.elements;

import com.codeborne.selenide.SelenideElement;
import io.github.enums.ElementEnum;
import org.openqa.selenium.By;

public class Label extends BaseElements{
    /**
     * 通过名字寻找元素
     * @param name
     * @return
     */
    public static SelenideElement label(String name){
        String element = String.format(ElementEnum.LABEL.getName(), name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过 class 和 文本 寻找元素
     * @param className
     * @param name
     * @return
     */
    public static SelenideElement label(String className, String name){
        String element = String.format(ElementEnum.LABEL.getNameAndClass(), className, name);
        By byElement = By.xpath(element);
        return by(byElement);
    }


}
