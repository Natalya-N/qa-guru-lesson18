package tests;

import api.LoginApi;
import models.AuthModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("simple")
public class ProfileTests extends TestBase {

    @Test
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
