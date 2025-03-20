package api;

import io.qameta.allure.Step;
import models.*;

import java.util.ArrayList;
import java.util.List;

import static data.Constants.*;
import static io.restassured.RestAssured.given;
import static specs.TestSpec.*;

public class BookApi {

    @Step("API Add book to user profile")
    public void addBookToProfile(AuthModel auth) {

        List<IsbnModel> collectionOfIsbns = new ArrayList<>();
        collectionOfIsbns.add(new IsbnModel(BOOK_ISBN));
        AddListOfBooksModel addBooksRequest = new AddListOfBooksModel();
        addBooksRequest.setUserId(auth.getUserId());
        addBooksRequest.setCollectionOfIsbns(collectionOfIsbns);

        given(requestSpec)
                .header("Authorization", "Bearer " + auth.getToken())
                .body(addBooksRequest)
                .when()
                .post(BOOKS_PATH)
                .then()
                .spec(getBaseResponseSpec(201));
    }

    @Step("API Delete all books from user's profile")
    public void deleteAllBooksFromProfile(AuthModel auth) {

        given(requestSpec)
                .header("Authorization", "Bearer " + auth.getToken())
                .param("UserId", auth.getUserId())
                .when()
                .delete(BOOKS_PATH)
                .then()
                .spec(getBaseResponseSpec(204));
    }

    @Step("API Delete one book from user's profile")
    public void deleteOneBookFromProfile(AuthModel auth) {

        DeleteBookModel deleteBookRequest = new DeleteBookModel();
        deleteBookRequest.setUserId(auth.getUserId());
        deleteBookRequest.setIsbn(BOOK_ISBN);

        given(requestSpec)
                .header("Authorization", "Bearer " + auth.getToken())
                .body(deleteBookRequest)
                .when()
                .delete(BOOK_PATH)
                .then()
                .spec(getBaseResponseSpec(204));
    }

}
