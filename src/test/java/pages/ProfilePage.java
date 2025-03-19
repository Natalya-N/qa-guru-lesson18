package pages;

import io.qameta.allure.Step;
import models.AuthModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static data.Constants.*;

public class ProfilePage {

    @Step("Add cookies for authorization")
    public ProfilePage addCookies(AuthModel authResponse) {
        open(BOOKS_URL);
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        return this;
    }

    @Step("Open profile page")
    public ProfilePage openProfilePage() {
        open(PROFILE_URL);
        return this;
    }

    @Step("Check added book in profile")
    public ProfilePage checkAddedBook() {
        $("[id='see-book-" + BOOK_TITLE + "']").shouldBe(visible);
        return this;
    }

    @Step("Check book was deleted")
    public ProfilePage checkBookDeleted() {
        $("[id='see-book-" + BOOK_TITLE + "']").shouldNotBe(visible);
        return this;
    }
}
