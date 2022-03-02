package io.github.elements;

import com.codeborne.selenide.SelenideElement;
import io.github.components.Element;
import io.github.components.Select;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author: Jackson.Wen
 */
@Getter
public class Pagination extends BaseElements {
    private final String sizeElement = "//span[string()=\"Page Size:\"]/following-sibling::div";
    private final String pageElement = "//span[string()=\"Page Size:\"]/preceding-sibling::div";

    /**
     * 一页展示多少条数据。 必须是页面上的数据，比如 10， 15， 20
     *
     * @param number
     */
    public void size(String number) {
        SelenideElement parent = $(By.xpath(sizeElement));
        io.github.components.Select select = new io.github.components.Select(parent);
        select.select(number);
    }

    /**
     * 选择第几页
     *
     * @param number
     */
    public void page(String number) {
        SelenideElement parent = $(By.xpath(pageElement));
        io.github.components.Select select = new Select(parent);
        select.select(number);
    }
}
