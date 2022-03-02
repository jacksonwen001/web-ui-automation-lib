package io.github.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.github.enums.ElementEnum;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class Table{
    private SelenideElement tableElement;
    private ElementsCollection th;
    private ElementsCollection td;

    /**
     * 通过表格中含有的字段来定位元素
     * @param name
     */
    public Table(String name) {
        String element = String.format(ElementEnum.TD.getName(), name);
        tableElement = $(By.xpath(element));
    }

    /**
     * 通过 class 和 含有的字段来定位元素
     * @param className
     * @param name
     */
    public Table(String className, String name) {
        String element = String.format(ElementEnum.TD.getName(), className, name);
        tableElement = $(By.xpath(element));
        this.th = tableElement.findAll(By.xpath(".//th"));
        this.td = tableElement.findAll(By.xpath(".//td"));
    }

    /**
     * 如果 th 和 td 不在同一个 table 内， 直接通过拼装找到各自的 th 和 td 组成 table
     * @param th
     * @param td
     */
    public Table(ElementsCollection th,ElementsCollection td){
        this.th = th;
        this.td = td;
    }

    /**
     * 如果无法通过第一种方式， 需要先定位好 table 元素再传进来
     * @param element
     */
    public Table(SelenideElement element) {
        this.tableElement = element;
        this.th = tableElement.findAll(By.xpath(".//th"));
        this.td = tableElement.findAll(By.xpath(".//td"));
    }

    /**
     *
     * @param th
     * @param td
     * @param num 第几行
     * @param row 第几列
     * @return
     */
    public Map<String, String> col(ElementsCollection th, ElementsCollection td, int num, int row) {
        Map<String, String> colMaps = new HashMap<>(16);
        int base = num * row;
        for (int j = base; j < (base + row); j++) {
            String title = th.get(j % row).text();
            String val = td.get(j).text();
            colMaps.put(title, val);
        }
        return colMaps;
    }

    /**
     * 第一行数据
     * @return
     */
    public Map<String, String> firstCol(){
        return col(th, td, 0, th.size());
    }

    /**
     * 最后一行数据
     * @return
     */
    public Map<String, String> lastCol(){
        return col(th, td, (td.size()-1), th.size());
    }

    /**
     * 获取全部的行的数据
     * @return
     */
    public List<Map<String, String>> cols(){
        List<Map<String, String>> result = new ArrayList<>();
        int colSize = size();
        for(int i=0; i< colSize ;i++){
            result.add(col(th, td, i, th.size()));
        }
        return result;
    }

    /**
     * 一共有几行数据
     * @return
     */
    public int size(){
        return td.size() / th.size();
    }

}
