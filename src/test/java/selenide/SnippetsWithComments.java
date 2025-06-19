package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// this is not a full list, just the most common
public class SnippetsWithComments {

    void browser_command_examples() { // команды браузера
        open("https://google.com"); //открывать абсолютный URL
        open("/customer/orders");  //относительный URL   // -Dselenide.baseUrl=http://123.23.23.1 (пример как потом в Jenkins можно управлять средами)
        open("/", AuthenticationType.BASIC,
                new BasicAuthCredentials("", "user", "password"));

        Selenide.back(); //кнопка "Назад" в браузере
        Selenide.refresh(); //кнопка "Перезагрузить(обновить) страницу"  в браузере

        Selenide.clearBrowserCookies(); //очисть куки
        Selenide.clearBrowserLocalStorage(); /
        executeJavaScript("sessionStorage.clear();"); // no Selenide command for this yet

        //JS алерты (варианты аллертов https://the-internet.herokuapp.com/javascript_alerts)
        Selenide.confirm(); // OK in alert dialogs
        Selenide.dismiss(); // Cancel in alert dialogs

        Selenide.closeWindow(); // close active tab
        Selenide.closeWebDriver(); // close browser completely

        //фреймы (фрейм - отдельное независимое DOM дерево)
        Selenide.switchTo().frame("new"); //перейти внутрь фрейма
        Selenide.switchTo().defaultContent(); // return from frame back to the main DOM - выйти из фрайма обратно в основной DOM

        //перемещение между окнами
        Selenide.switchTo().window("The Internet"); // можно переходить по номерам: 1,2,3,etc.

        var cookie = new Cookie("foo", "bar"); //определить куки
        WebDriverRunner.getWebDriver().manage().addCookie(cookie); //добавить куки

        //не забывать обновлять страничку через Selenide.refresh();

    }

    void selectors_examples() { //селекторы
        $("div").click();
        element("div").click(); //то же самое, что и $, синоним

        $("div", 2).click(); // найти третий div, считает от нуля 0

        $x("//h1/div").click();
        $(byXpath("//h1/div")).click();

        $(byText("full text")).click(); // ищет по тексту
        $(withText("ull tex")).click(); // ищет по частичному вхождению

        $(byTagAndText("div", "full text")); //поиск по тегу и тексту
        $(withTagAndText("div", "ull text"));


        //навигация по DOM дереву
        $("").parent();
        $("").sibling(1); //движение по родственнику вниз
        $("").preceding(1); //движение по родственнику вверх
        $("").closest("div");
        $("").ancestor("div"); // the same as closest
        $("div:last-child"); //редкая команда

        $("div").$("h1").find(byText("abc")).click();
        // very optional
        $(byAttribute("abc", "x")).click();
        $("[abc=x]").click(); //более привычный аналог

        $(byId("mytext")).click();
        $("#mytext").click(); //более привычный аналог

        $(byClassName("red")).click();
        $(".red").click(); //более привычный аналог
    }

    void actions_examples() { //действия
        $("").click();
        $("").doubleClick();
        $("").contextClick(); //клик правой кнопкой мыши

        $("").hover();

        $("").setValue("text"); //записываем текст в поле для ввода
        $("").append("text"); //добавляет текст к уже написанному
        $("").clear();
        $("").setValue(""); // clear

        $("div").sendKeys("c"); // hotkey c on element (эмуляция нажатия клавиш)
        actions().sendKeys("c").perform(); //hotkey c on whole application
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F (комбинация клавиш)
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));

        $("").pressEnter();
        $("").pressEscape();
        $("").pressTab();


        // complex actions with keybord and mouse, example
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

        // old html actions don't work with many modern frameworks
        $("").selectOption("dropdown_option");
        $("").selectRadio("radio_options");

    }

    void assertions_examples() {
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);


        //longer timeouts
        $("").shouldBe(visible, Duration.ofSeconds(30));

    }

    void conditions_examples() {
        $("").shouldBe(visible);
        $("").shouldBe(hidden);

        $("").shouldHave(text("abc")); //
        $("").shouldHave(exactText("abc"));
        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactTextCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$"));

        $("").shouldHave(cssClass("red"));
        $("").shouldHave(cssValue("font-size", "12"));

        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);

        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name", "example"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

        $("").shouldBe(checked); // for checkboxes

        // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
        $("").should(exist);

        // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);
    }

    void collections_examples() {

        $$("div"); // does nothing!

        $$x("//div"); // by XPath

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1));
        $$("div").excludeWith(text("123")).shouldHave(size(1));

        $$("div").first().click();
        elements("div").first().click();
        // $("div").click();
        $$("div").last().click();
        $$("div").get(1).click(); // the second! (start with 0)
        $("div", 1).click(); // same as previous
        $$("div").findBy(text("123")).click(); //  finds first

        // assertions
        $$("").shouldHave(size(0));
        $$("").shouldBe(CollectionCondition.empty); // the same

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

        $$("").shouldHave(itemWithText("Gamma")); // only one text

        $$("").shouldHave(sizeGreaterThan(0));
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(2));


    }

    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); // only for <a href=".."> links
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // don't forget to submit!
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')");
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

    }
}

