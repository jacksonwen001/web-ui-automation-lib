//package io.github.core;
//
//import com.codeborne.selenide.ClickOptions;
//import com.codeborne.selenide.Condition;
//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.SelenideElement;
//import com.codeborne.selenide.testng.TextReport;
//import io.github.components.Pagination;
//import io.github.components.Select;
//import io.github.components.enums.Elements;
//import io.github.core.browsers.Drivers;
//import io.qameta.allure.Description;
//import io.qameta.allure.Step;
//import lombok.Getter;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import static com.codeborne.selenide.Condition.*;
//import static com.codeborne.selenide.Condition.visible;
//import static com.codeborne.selenide.Selenide.open;
//import static io.github.components.Element.*;
//import static io.github.components.Element.span;
//
///**
// * @author: Jackson.Wen
// */
//@Getter
//@Listeners({TextReport.class })
//public class DriverTest {
//    Drivers drivers;
//    // username and password
//    private final SelenideElement enterButton = button("ENTER");
//    private final SelenideElement userInput = xpath("//input[@name=\"loginfmt\"]");
//    private final SelenideElement userNextStepButton = xpath("//input[@type=\"submit\"]");
//    private final SelenideElement passwordInput = xpath("//input[@name=\"passwd\"]");
//    private final SelenideElement loginButton = xpath("//input[@type=\"submit\"]");
//    private final SelenideElement keepLoginButton = xpath("//input[@type=\"submit\"]");
//    private final SelenideElement customerPortalButton = span("Customer Portal");
//    private final SelenideElement customerTitle = span("Manage Customer Service Dashboard");
//
//    @BeforeClass
//    public void beforeClass(){
//        drivers = new Drivers();
//        drivers.startDriver();
//    }
//
//
//    @Test(priority = 0)
//    @Step("Test")
//    @Description("This is the way")
//    public void testOpenWithCookie() throws Exception {
////        open("")
////        getEnterButton().shouldBe(enabled).click(ClickOptions.usingJavaScript());
////        getUserInput().shouldBe(Condition.visible).sendKeys(getUsername());
////        getUserNextStepButton().shouldBe(Condition.visible).click();
////        getPasswordInput().shouldBe(Condition.visible).sendKeys(getPassword());
////        getLoginButton().shouldBe(Condition.visible).click();
////        getKeepLoginButton().shouldBe(Condition.visible).click();
////        getCustomerPortalButton().shouldBe(visible).click();
////        getEnterButton().shouldBe(visible).shouldBe(enabled).click(ClickOptions.usingJavaScript());
////        getEnterButton().shouldBe(disappear);
////        getCustomerTitle().shouldBe(visible);
////        drivers.saveCookies("customer");
////        open("https://cs.foodtruck-uat.com");
//    }
//
//
//    @AfterClass
//    public void afterClass(){
//        drivers.closeDriver();
//    }
//}
