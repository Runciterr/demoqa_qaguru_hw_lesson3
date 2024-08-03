import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }

    @Test
    void fillFormTest() {


        open("/text-box");
        $("#userName").setValue("Den");
        $("#userEmail").setValue("Den@cool.com");
        $("#currentAddress").setValue("Elm Street 1");
        $("#permanentAddress").setValue("Other Street 1");
        $("#submit").click();


        $("#output #name").shouldHave(text("Den")); //можно написать так
        $("#output #email").shouldHave(text("Den@cool.com"));
        $("#output").$("#currentAddress").shouldHave(text("Elm Street 1")); //а можно так
        $("#output").$("#permanentAddress").shouldHave(text("Other Street 1"));

    }
}