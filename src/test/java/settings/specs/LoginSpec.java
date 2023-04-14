package settings.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static settings.helpers.CustomAllureListener.withCustomTemplates;

public class LoginSpec {

    public static RequestSpecification requestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(ContentType.JSON)
//            .baseUri("https://web.rbsuat.com/ab/")
//            .basePath("rest/");
            .baseUri("https://alfabank.ru/");
    //            .basePath("rest/");
    public static RequestSpecification requestSpecificationLogin = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(ContentType.JSON)
            .baseUri("https://private.auth.alfabank.ru/passport/cerberus-mini-green/dashboard-green/")
            .basePath("api/oid/authorize");


    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification responseSpecification400 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(400)
            .build();

    public static ResponseSpecification responseSpecification500 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(500)
            .build();

    public static RequestSpecification requestSpecificationWeb = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(ContentType.JSON)
            .baseUri("https://web.rbsuat.com/ab/")
            .basePath("rest/");
}
