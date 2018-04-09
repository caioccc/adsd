package br.com.leucotron.livre;

import br.com.leucotron.livre.util.FunctionalTest;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.json.JSONException;
import org.json.JSONObject;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

@RunWith(SpringRunner.class)
public class LivrApplicationTests extends FunctionalTest {

    @Test
    public void loginReturn200() throws JSONException {

        final String USERNAME = "username";
        final String PASSWORD = "password";

        JSONObject jsonObj = new JSONObject().put(USERNAME, "admin@leucotron.com").put(PASSWORD, "1234");
        given()
            .contentType(ContentType.JSON)
            .body(jsonObj.toString())
            .when()
            .post("/login")
            .then()
            .statusCode(200);
    }

}
