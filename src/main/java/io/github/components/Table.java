package io.github.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author: Jackson.Wen
 */
@Getter
public class Table extends Element {
    static Logger logger = LoggerFactory.getLogger(Table.class);

    private final SelenideElement table;

    /**
     * 定义一个表格组件
     */
    public Table(SelenideElement table) {
        this.table = table;
    }

    public Table(String xpathStr) {
        this.table = xpath(xpathStr);
    }

    /**
     * 寻找距离这个字符串最近的 table head
     *
     * @return
     */
    public ElementsCollection th() {
        return table.shouldBe(Condition.visible).findAll(By.xpath(".//th"));
    }

    /**
     * 寻找距离这个字符串最近的 table td
     *
     * @return
     */
    public ElementsCollection td() {
        return table.shouldBe(Condition.visible).findAll(By.xpath(".//td"));
    }


    /**
     * 获取行数据
     *
     * @param num
     * @return
     */
    public Map<String, String> col(int num) {
        ElementsCollection th = th();
        ElementsCollection td = td();
        return col(th, td, num, th.size());
    }

    /**
     * 传入自定义的 th 和 td
     *
     * @param th
     * @param td
     * @param num
     * @param row
     * @return
     */
    public Map<String, String> col(ElementsCollection th, ElementsCollection td, int num, int row) {
        Map<String, String> colMaps = new HashMap<>(16);
        logger.info("row :" + row);

        int base = num * row;
        for (int j = base; j < (base + row); j++) {
            int h = j % row;
            SelenideElement title = th.get(h).find(By.xpath(".//span[@class=\"ant-table-column-title\"]"));
            String head = "null";
            if(title.exists()){
                 head = title.text();
            }

            String val = td.get(j).text();
            logger.info(">>>>>>>>>>>>>" + head + ": " + val);
            colMaps.put(th.get(j % row).text(), td.get(j).text());
        }
        return colMaps;
    }

    /**
     * 获取默认的所有表格行元素
     *
     * @return
     */
    public List<Map<String, String>> cols() {
        ElementsCollection th = th();
        ElementsCollection td = td();
        return cols(th, td);
    }

    /**
     * 获取所有的自定义的表格行元素
     *
     * @param th
     * @param td
     * @return
     */
    public List<Map<String, String>> cols(ElementsCollection th, ElementsCollection td) {
        assertThat(th).isNotNull();
        int group = td.size() / th.size();
        List<Map<String, String>> result = new ArrayList<>();

        for (int i = 0; i < group; i++) {
            result.add(col(th, td, i, th.size()));
        }
        return result;
    }

    /**
     * 获取表格有几行元素
     *
     * @param th
     * @param td
     * @return
     */
    public int capacity(ElementsCollection th, ElementsCollection td) {
        int group = td.size() / th.size();
        return group;
    }

    public int capacity() {
        ElementsCollection th = th();
        ElementsCollection td = td();
        return capacity(th, td);
    }

}
