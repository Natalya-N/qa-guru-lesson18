package tests;

import api.LoginApi;
import jdk.jfr.Description;
import models.AuthModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("simple")
public class ProfileTests extends TestBase {

    @Test
    @Description("Проверка удаления одной книги из профайла пользователя")
    void deleteBookFromProfileTest() {
        AuthModel authResponse = new LoginApi().login();
        bookApi.deleteAllBooksFromProfile(authResponse);
        bookApi.addBookToProfile(authResponse);

        profilePage.addCookies(authResponse)
                .openProfilePage()
                .checkAddedBook();

        bookApi.deleteOneBookFromProfile(authResponse);

        profilePage.openProfilePage()
                .checkBookDeleted();
    }

}
