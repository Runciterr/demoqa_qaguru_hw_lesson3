import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class BestContributorToSelenide {

@Test
    void AndreySoltsevShouldBeTheFirstContributor (){
    // открыть страницу репозитория селенида
    open ("https://github.com/selenide/selenide");
    // подвести мышку (ховер) к первому автору из блока contributors
    // проверка: во всплывающем окне есть текст Andrei Solntsev

    }
}
