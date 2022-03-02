package io.github.elements;

import com.codeborne.selenide.SelenideElement;
import io.github.enums.ElementEnum;
import org.openqa.selenium.By;

public class Li extends BaseElements{
    /**
     * 通过名字寻找元素
     * @param name
     * @return
     */
    public static SelenideElement li(String name){
        String element = String.format(ElementEnum.LI.getName(), name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过 class 和 文本 寻找元素
     * @param className
     * @param name
     * @return
     */
    public static SelenideElement li(String className, String name){
        String element = String.format(ElementEnum.LI.getNameAndClass(), className, name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

}
