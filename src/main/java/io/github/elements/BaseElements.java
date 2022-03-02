package io.github.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.github.enums.ElementEnum;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class BaseElements {
    /**
     * 寻找两遍， 第一遍判断是否存在多个元素， 如果是使用精确文本查询
     */
    protected static SelenideElement by(By by){
        ElementsCollection elements = $$(by);
        if(elements.size() == 1) {
            return elements.get(0);
        }else{
            return $(by);
        }
    }

    /**
     * 通过 ID 寻找元素
     * @param name
     * @return
     */
    public static SelenideElement id(String name) {
        return $(By.id(name));
    }

}
