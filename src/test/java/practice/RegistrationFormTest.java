package practice;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selectors.byText;

public class RegistrationFormTest {

        @BeforeAll
        static void beforeAll() {

            Configuration.baseUrl = "https://demoqa.com";
            Configuration.browserSize = "1920x1080";
            Configuration.pageLoadStrategy = "eager";
            Configuration.timeout = (10);
        }

        @Test
        void fillRegistrationFormTest() {
            open("/automation-practice-form");

            $("input#firstName").setValue("Alex");
            $("input#lastName").setValue("Egorov");
            $("input#userEmail").setValue("alex@egorov.com");
            $("#genterWrapper").$(byText("Male")).click();
            $("input#userNumber").setValue("1234567890");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").$(byText("2005")).click();
            $(".react-datepicker__month-select").$(byText("July")).click();
            $("div.react-datepicker__week div.react-datepicker__day--001").click();
            $("input#subjectsInput").setValue("History");
    //        $("#hobbiesWrapper").$(byText("Sports")).click();
    //        $("#uploadPicture").uploadFromClasspath("фото.jpeg");
            $("#currentAddress").setValue("New York");
            $("input#subjectsInput").setValue("Maths").pressEnter();

            $("#state").click();
            $("#stateCity-wrapper div#react-select-3-option-3").click();
            $("#city").click();
            $("#city div#react-select-4-option-1").click();
            $("#submit").click();


            $(".table-responsive").shouldHave(text("Alex Egorov"));
            $(".table-responsive").shouldHave(text("alex@egorov.com"));
            $(".table-responsive").shouldHave(text("Male"));
            $(".table-responsive").shouldHave(text("1234567890"));
            $(".table-responsive").shouldHave(text("01 July,2005"));
            $(".table-responsive").shouldHave(text("History"));
            $(".table-responsive").shouldHave(text("фото.jpeg"));
            $(".table-responsive").shouldHave(text("New York"));
            $(".table-responsive").shouldHave(text("Haryana"));

            $("#closeLargeModal").click();


    }
}
