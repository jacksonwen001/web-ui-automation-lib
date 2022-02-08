package io.github.core;

import com.codeborne.selenide.Selenide;
import io.github.components.Pagination;
import io.github.components.Select;
import io.github.components.enums.Elements;
import org.testng.annotations.Test;

import static io.github.components.Element.get;

/**
 * @author: Jackson.Wen
 */
public class DriverTest {
    @Test
    public void testStartDriver() throws Exception {
        DriverBase driverBase = new DriverBase();
        driverBase.startServerDriver();
        Selenide.open("https://portal.foodtruck-qa.com/");

//        Pagination p = new Pagination();
//        p.size("10");
////        //open("http://www.baidu.com");
//        //driverBase.saveCookie("baidu");
//        driverBase.openAndSetCookie("https://www.baidu.com", "baidu");

    }
}
