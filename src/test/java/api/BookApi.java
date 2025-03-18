package api;

import data.Credentials;
import data.TestData;
import io.qameta.allure.Step;
import models.*;

import java.util.ArrayList;
import java.util.List;

import static data.Constants.BOOKS_PATH;
import static data.Constants.BOOK_PATH;
import static data.Credentials.login;
import static io.restassured.RestAssured.given;
import static specs.TestSpec.*;

public class BookApi {

    @Step("API Add book to user profile")
    public void addBookToProfile(AuthModel auth) {

        List<IsbnModel> collectionOfIsbns = new ArrayList<>();
        collectionOfIsbns.add(new IsbnModel(TestData.book.getIsbn()));
        AddListOfBooksModel addBooksRequest = new AddListOfBooksModel();
        addBooksRequest.setUserId(auth.getUserId());
        addBooksRequest.setCollectionOfIsbns(collectionOfIsbns);

        given(requestSpec)
                .header("Authorization", "Bearer " + auth.getToken())
                .body(addBooksRequest)
                .when()
                .post(BOOKS_PATH)
                .then()
                .spec(statusCode201Spec);
    }

    @Step("API Delete all books from user's profile")
    public void deleteAllBooksFromProfile(AuthModel auth) {

        given(requestSpec)
                .header("Authorization", "Bearer " + auth.getToken())
                .param("UserId", auth.getUserId())
                .when()
                .delete(BOOKS_PATH)
                .then()
                .spec(statusCode204Spec);
    }

    @Step("API Delete one book from user's profile")
    public void deleteOneBookFromProfile(AuthModel auth) {

        DeleteBookModel deleteBookRequest = new DeleteBookModel();
        deleteBookRequest.setUserId(auth.getUserId());
        deleteBookRequest.setIsbn(TestData.book.getIsbn());

        given(requestSpec)
                .header("Authorization", "Bearer " + auth.getToken())
                .body(deleteBookRequest)
                .when()
                .delete(BOOK_PATH)
                .then()
                .spec(statusCode204Spec);
    }

}
