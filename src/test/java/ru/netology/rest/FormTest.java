package ru.netology.rest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.cssSelector;

public class FormTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Иванова Анна");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestIfOnlyName() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Анна");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestIfNameCapsLock() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("ИВАНОВА АННА");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestIfNameSmallLetters() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("иванова анна");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestIfNameOneLetters() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("и");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestIfDoubleName() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Анна-Мария Иванова-Петрова");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestIfNameTogether() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("АннаИванова");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestIfNameMore50Lettrs() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Аннааааааааааааааааааааа Иввввввввввввввввввввввввввв");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitRequestIfNameHaveLettersCyrillicSymbol() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Алёна Фёдорова");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestOnlySpaces() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("                    ");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestIfNameLattinLetters() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Anna Ivanova");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitRequestIfFieldNameEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestIfNameSpecialСharacters() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("!@#$%^^&*");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103546789");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    void shouldSubmitRequestIfAllFieldsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestIfPhoneEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Иваноа Мария");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestIfPhone5Figures() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Иванова Мария");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79106");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmitRequestIfPhone15Figures() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Иванова Мария");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+791063487612309");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmitRequestIfPhoneWithoutPlus() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Иванова Мария");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("79106890543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmitRequestIfPhoneIsLetters() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Иванова Мария");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("мипавыапр");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmitRequestIfPhoneSpecialСharacters() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Иванова Мария");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("!№;%:?*№№");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitIfAgreementIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Иванова Анна");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79103456789");
        form.$(cssSelector("[role=button]")).click();
        $(".input_invalid [role=presentation]").shouldHave(Condition.text("Я соглашаюсь"));
    }
}

