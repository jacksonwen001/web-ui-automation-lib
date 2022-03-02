package io.github.elements;

import com.codeborne.selenide.SelenideElement;
import io.github.enums.ElementEnum;
import org.openqa.selenium.By;

public class Heading extends BaseElements{
    /**
     * 通过名字寻找元素
     * @param name
     * @return
     */
    public static SelenideElement h1(String name){
        String element = String.format(ElementEnum.H1.getName(), name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过 class 和 文本 寻找元素
     * @param className
     * @param name
     * @return
     */
    public static SelenideElement h1(String className, String name){
        String element = String.format(ElementEnum.H1.getNameAndClass(), className, name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过名字寻找元素
     * @param name
     * @return
     */
    public static SelenideElement h2(String name){
        String element = String.format(ElementEnum.H2.getName(), name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过 class 和 文本 寻找元素
     * @param className
     * @param name
     * @return
     */
    public static SelenideElement h2(String className, String name){
        String element = String.format(ElementEnum.H2.getNameAndClass(), className, name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过名字寻找元素
     * @param name
     * @return
     */
    public static SelenideElement h3(String name){
        String element = String.format(ElementEnum.H3.getName(), name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过 class 和 文本 寻找元素
     * @param className
     * @param name
     * @return
     */
    public static SelenideElement h3(String className, String name){
        String element = String.format(ElementEnum.H3.getNameAndClass(), className, name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过名字寻找元素
     * @param name
     * @return
     */
    public static SelenideElement h4(String name){
        String element = String.format(ElementEnum.H4.getName(), name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过 class 和 文本 寻找元素
     * @param className
     * @param name
     * @return
     */
    public static SelenideElement h4(String className, String name){
        String element = String.format(ElementEnum.H4.getNameAndClass(), className, name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过名字寻找元素
     * @param name
     * @return
     */
    public static SelenideElement h5(String name){
        String element = String.format(ElementEnum.H5.getName(), name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

    /**
     * 通过 class 和 文本 寻找元素
     * @param className
     * @param name
     * @return
     */
    public static SelenideElement h5(String className, String name){
        String element = String.format(ElementEnum.H5.getNameAndClass(), className, name);
        By byElement = By.xpath(element);
        return by(byElement);
    }

}
