package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class TestSpec {
    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplate())
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification getBaseResponseSpec(int code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .log(ALL)
                .build();
    }
}

