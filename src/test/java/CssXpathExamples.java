import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CssXpathExamples {


    void cssxPath() {
        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">
        $("[data-testid=email]").setValue("1");  // на css
        $(by("data-testid", "email")).setValue("1"); //аналог на css
        $x("//*[@data-testid='email']").setValue("1"); // на xpath

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email">

        $("[id=email]").setValue("1");  // на css
        $(by("id", "email")).setValue("1"); //аналог на css
        $(byId("email")).setValue("1");
        $("#email").setValue("1"); //топ вариант
        $x("//*[@id='email']").setValue("1"); // на xpath

        // <input type="email" class="inputtext login_form_input_box" name="email">

        $("[name=email]").setValue("1");  // на css
        $(by("name", "email")).setValue("1"); //аналог на css
        $(byName("email")).setValue("1");
        $("#email").setValue("1"); //топ вариант
        $x("//*[@name='email']").setValue("1"); // на xpath

        // <input type="email" class="inputtext login_form_input_box">

        $("[class=login_form_input_box][class=inputtext]").setValue("1");  // на css
        $(".login_form_input_box").setValue("1");  // топовый вариант
        $(".inputtext.login_form_input_box").setValue("1");  // на css
        $("input.inputtext.login_form_input_box").setValue("1");
        $x("//input[@class='inputtext'][@class='login_form_input_box']").setValue("1"); // на xpath


        // <div class="inputtext">
        //      <input type="email" class="login_form_input_box">
        // </div>
        $(".inputtext .login_form_input_box").setValue("1");  //добавляем пробел тк 2 элемента
        $("div.inputtext input.login_form_input_box").setValue("1");

        //<div>Hello, qa.guru!</div>
        $(byText("Hello, qa.guru!"));
        $(withText("llo, qa.g"));
        $x("//*[text()='Hello, qa.guru!']")); // моветон xpath
        $x("//*[contains(text(),'Hello, qa.guru!']")); // xpath
    }
}
