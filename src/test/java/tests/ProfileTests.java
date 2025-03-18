package tests;

import api.LoginApi;
import data.TestData;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import models.AuthModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ProfileTests extends TestBase {

    @Test
    void successfulLoginTest() {
        String requestBody = "{ \"userName\": \"test123456\", \"password\": \"test123456\" }";

        Response authResponse = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post(baseURI + "/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        open("/books");

        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("UserId")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));
        open("/profile");
    }

    @Test
    void deleteBookFromProfileTest() {
        AuthModel authResponse = new LoginApi().login();
        bookApi.deleteAllBooksFromProfile(authResponse);
        bookApi.addBookToProfile(authResponse);

        step("Add cookies for authorization", () -> {
        open("/books");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        });

        step("Check added book in profile", () -> {
        open("/profile");
        $("[id='see-book-" + TestData.title + "']").shouldBe(visible);
        });

        bookApi.deleteOneBookFromProfile(authResponse);

        step("Check there are no books in profile", () -> {
            open("/profile");
            $("[id='see-book-" + TestData.title + "']").shouldNotBe(visible);
        });
    }

}
