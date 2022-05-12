package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SendFormTest {

    @Test
    public void shouldTest() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Самара");
        // убираем автозаполнение поля ввода даты
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        // устанавливаем дату +5 дней от текущей
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        $("[data-test-id=name] input").setValue("Мери Поппинс-До-Свидания");
        $("[data-test-id=phone] input").setValue("+74569875656");
        $("[data-test-id=agreement]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $(byClassName("notification__title")).should(ownText("Успешно!"), Duration.ofSeconds(15));
    }
}