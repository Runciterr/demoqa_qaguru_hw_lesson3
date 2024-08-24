
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsJUnit5Test {

    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void SoftAssertionsSearchTest() {

        // открыть страницу в github
        open("https://github.com/");

        // ввести selenide в строке поиска
        $("div.search-input-container").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("div.kXssRI div").first().$("a").click();

        // перейти на страницу с wiki
        $("#wiki-tab").click();

        // найти страницу SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions");

        // открыть страницу SoftAssertions, проверить что внутри есть пример кода для JUnit5
        $(byText("SoftAssertions")).click();

        //$(byText("JUnit5 extension "));

        $("#wiki-body").shouldHave(text(
                "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));

    }
}