package io.github.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

/**
 * @author: Jackson.Wen
 */
@Getter
public class Pagination extends Element {
    /**
     * 一页展示多少条数据。 必须是页面上的数据，比如 10， 15， 20
     *
     * @param number
     */
    public void size(String number) {
        SelenideElement parent = xpath("//span[string()=\"Page Size:\"]/following-sibling::div");
        Select select = new Select(parent);
        select.selectOption(number);
    }

    /**
     * 选择第几页
     *
     * @param number
     */
    public void page(String number) {
        SelenideElement parent = xpath("//span[string()=\"Page Size:\"]/preceding-sibling::div");
        Select select = new Select(parent);
        select.selectOption(number);
    }
}
