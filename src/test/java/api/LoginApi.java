package api;

import data.Credentials;
import io.qameta.allure.Step;
import models.AuthModel;
import models.LoginModel;

import static data.Constants.LOGIN_PATH;
import static io.restassured.RestAssured.given;
import static specs.TestSpec.requestSpec;
import static specs.TestSpec.statusCode200Spec;

public class LoginApi {

    @Step("API Authorization")
    public AuthModel login() {

        LoginModel login = new LoginModel();
        login.setUserName(Credentials.login);
        login.setPassword(Credentials.password);

        return
                given(requestSpec)
                        .body(login)
                        .when()
                        .post(LOGIN_PATH)
                        .then()
                        .spec(statusCode200Spec)
                        .extract().as(AuthModel.class);
    }

}
