import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class SelenideRepositorySearch {

    @Test
    void ShouldFindSelenideRepositoryAtTheTop(){

        // открыть главную страницу
        open("https://github.com/");

        // ввести в поле поиска selenide и нажать enter
        $("[placeholder='Search Github']").setValue("selenide").pressEnter();
        // кликнуть на первый репозиторий из списка найденных
        // проверка: заголовок selenide/selenide


}
}
