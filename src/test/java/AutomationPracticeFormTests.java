import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }
    @AfterAll
    static  void afterAll() {
        closeWebDriver();
    }
    @Test
    void fillAutomationFormTest() {


        open("/automation-practice-form");
        $("#firstName").setValue("Den");
        $("#lastName").setValue("White");
        $("#userEmail").setValue("DedWhite@example.com");
        $("#userNumber").setValue("89104054060");
        $(byText("Male")).click(); //радиокнопка
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day.react-datepicker__day--026").click();
        $("#userEmail").setValue("DedWhite@example.com");
        $("#subjectsInput").setValue("English").pressEnter(); //выпадающий список
        $(byText("Reading")).click(); //чек-бокс

        //загрузка файла
        //предварительно добавляем файл в репозиторий
        $("#uploadPicture").uploadFile(new File("src/test/files/af75334fb974303ac203acd513435cc2.jpg"));
        $("#currentAddress").setValue("Russia, Moscow");

        //выпадающий список
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Rajasthan")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Jaipur")).click();
        $("#submit").click();

        //проверки:
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                Condition.text("Student Name Den White"),
                Condition.text("Student Email DedWhite@example.com"),
                Condition.text("Gender Male"),
                Condition.text("Mobile 8910405406"),
                Condition.text("Date of Birth 26 February,1990"),
                Condition.text("Subjects English"),
                Condition.text("Hobbies Reading"),
                Condition.text("Picture af75334fb974303ac203acd513435cc2.jpg"),
                Condition.text("Address Russia, Moscow"),
                Condition.text("State and City Rajasthan Jaipur"));




    }

}
