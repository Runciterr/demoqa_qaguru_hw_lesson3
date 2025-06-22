import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class EnterprizeGitHubPageTest {
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void enterprisePageHaveHeadTest() {
        open("");
        $(byTagAndText("button", "Solutions")).hover();
        $(byTagAndText("a", "Enterprise")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered\n" + "developer platform"));
    }
    @Test
    void dragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        SelenideElement source = $("#column-a");
        SelenideElement target = $("#column-b");
        //проверка headers элементов до action
        source.shouldHave(text("A"));
        target.shouldHave(text("B"));

        //перенесос прямоугольник А на место В
        actions().clickAndHold(source).moveToElement(target).release().build().perform();
        //проверка того, что headers поменялись
        source.shouldHave(text("B"));
        target.shouldHave(text("A"));

        //проверка работает ли тест, с командой dragAndDrop
        source.dragAndDrop(to("#column-b"));
        //проверка того, что headers элементов в первоначальном состоянии
        source.shouldHave(text("A"));
        target.shouldHave(text("B"));
    }
}