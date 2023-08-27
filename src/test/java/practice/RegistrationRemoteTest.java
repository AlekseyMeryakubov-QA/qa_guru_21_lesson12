package practice;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import practice.helpers.Attach;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.asynchttpclient.util.HttpConstants.Methods.OPTIONS;

public class RegistrationRemoteTest {

    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = (10);
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(OPTIONS);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        capabilities.setCapability("selenoid:options", selenoidOptions);
        Configuration.browserCapabilities = capabilities;
    }
    @BeforeEach
    void addListener (){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    @Tag("remote")
    @DisplayName("Successuful registration")
    void fillRegistrationRemoteFormTest() {
        step("Open form",() -> {
            open("/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });
        step("Fill form",() -> {
            $("#firstName").setValue("Alex");
            $("#lastName").setValue("Egorov");
            $("#userEmail").setValue("alex@egorov.com");
            $("#genterWrapper").$(byText("Male")).click();
            $("#userNumber").setValue("1234567890");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").$(byText("2005")).click();
            $(".react-datepicker__month-select").$(byText("July")).click();
            $("div.react-datepicker__week div.react-datepicker__day--001").click();
            $("#subjectsInput").setValue("History").pressEnter();
            $("#hobbiesWrapper").$(byText("Sports")).click();
            $("#uploadPicture").uploadFromClasspath("image.png");
            $("#currentAddress").setValue("New York");
            $("#state").click();
            $("#stateCity-wrapper").$(byText("Haryana")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Karnal")).click();
            $("#submit").click();
        });
        step("Verify results", () -> {
            $(".table-responsive").shouldHave(text("Alex Egorov"));
            $(".table-responsive").shouldHave(text("alex@egorov.com"));
            $(".table-responsive").shouldHave(text("Male"));
            $(".table-responsive").shouldHave(text("1234567890"));
            $(".table-responsive").shouldHave(text("01 July,2005"));
            $(".table-responsive").shouldHave(text("Sports"));
            $(".table-responsive").shouldHave(text("image.png"));
            $(".table-responsive").shouldHave(text("New York"));
            $(".table-responsive").shouldHave(text("Haryana Karnal"));
        });
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
    }
}
