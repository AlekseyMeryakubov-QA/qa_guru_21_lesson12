package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class TextBoxTests {

    static {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    void googleTest() {
        Selenide.open("https://google.com");
    }
}

//public class TextBoxTests {
//
//    static {
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        Configuration.browserCapabilities = capabilities;
//    }
//
//
//    @BeforeAll
//    static void beforeAll() {
//        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.browserSize = "1920x1080";
//        Configuration.pageLoadStrategy = "eager";
//
//        static {
//            ChromeOptions options = new ChromeOptions();
//            options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//            Configuration.browserCapabilities = capabilities;
//        }
//    }
//
//    @Test
//    void fillFormTest() {
//        open("/text-box");
//
//        $("#userName").setValue("Alex Egorov");
//        $("#userEmail").setValue("alex@egorov.com");
////        $("#currentAddress").setValue("Some address 1");
////        $("#permanentAddress").setValue("Another address 1");
////        $("#submit").click();
////
//////        $("#output").$("#name").shouldHave(text("Alex Egorov"));
////        $("#output #name").shouldHave(text("Alex Egorov"));
////        $("#output #email").shouldHave(text("alex@egorov.com"));
////        $("#output #currentAddress").shouldHave(text("Some address 1"));
////        $("#output #permanentAddress").shouldHave(text("Another address 1"));
//    }
//}
