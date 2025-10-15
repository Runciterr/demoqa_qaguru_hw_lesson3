package simple;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Tag("demoqa")
public class AutomationPracticeFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        Configuration.browserCapabilities = capabilities;
//

    }

    @AfterAll
    static void tearDown() {
        closeWebDriver();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }


    @Test
    void fillAutomationFormTest() {

        step("Open form", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");

        });

        step("fill form", () -> {
            $("#firstName").setValue("Den");
            $("#lastName").setValue("White");
            $("#userEmail").setValue("DedWhite@example.com");
            $("#userNumber").setValue("89104054060");
            $("#genterWrapper").$(byText("Male")).click();
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("March");
            $(".react-datepicker__year-select").selectOption("1990");
            $(".react-datepicker__day.react-datepicker__day--026").click();
            $("#subjectsInput").setValue("English").pressEnter();
            $("#hobbiesWrapper").$(byText("Reading")).click();
            $("#uploadPicture").uploadFromClasspath("af75334fb974303ac203acd513435cc2.jpg");
            $("#currentAddress").setValue("Russia, Moscow");
            $("#state").click();
            $("#stateCity-wrapper").$(byText("Rajasthan")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Jaipur")).click();
            $("#submit").click();
        });

        step("check results", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(Condition.text("Student Name Den White"));
            $(".table-responsive").shouldHave(Condition.text("Student Email DedWhite@example.com"));
            $(".table-responsive").shouldHave(Condition.text("Gender Male"));
            $(".table-responsive").shouldHave(Condition.text("Mobile 8910405406"));
            $(".table-responsive").shouldHave(Condition.text("Date of Birth 26 February,1990"));
            $(".table-responsive").shouldHave(Condition.text("Subjects English"));
            $(".table-responsive").shouldHave(Condition.text("Hobbies Reading"));
            $(".table-responsive").shouldHave(Condition.text("Picture af75334fb974303ac203acd513435cc2.jpg"));
            $(".table-responsive").shouldHave(Condition.text("Address Russia, Moscow"));
            $(".table-responsive").shouldHave(Condition.text("State and City Rajasthan Jaipur"));
        });

    }

}
