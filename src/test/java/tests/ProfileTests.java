package tests;

import api.LoginApi;
import data.TestData;
import models.AuthModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static data.Constants.*;
import static io.qameta.allure.Allure.step;

@Tag("simple")
public class ProfileTests extends TestBase {

    @Test
    void deleteBookFromProfileTest() {
        AuthModel authResponse = new LoginApi().login();
        bookApi.deleteAllBooksFromProfile(authResponse);
        bookApi.addBookToProfile(authResponse);

        step("Add cookies for authorization", () -> {
        open(BOOKS_URL);
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        });

        step("Check added book in profile", () -> {
        open(PROFILE_URL);
        $("[id='see-book-" + BOOK_TITLE + "']").shouldBe(visible);
        });

        bookApi.deleteOneBookFromProfile(authResponse);

        step("Check there are no books in profile", () -> {
            open(PROFILE_URL);
            $("[id='see-book-" + BOOK_TITLE + "']").shouldNotBe(visible);
        });
    }

}
