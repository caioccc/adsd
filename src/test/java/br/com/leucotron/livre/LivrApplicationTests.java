package br.com.leucotron.livre;

import br.com.leucotron.livre.util.FunctionalTest;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

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

    @Test
    public void loginReturn401() throws JSONException {

        final String USERNAME = "username";
        final String PASSWORD = "password";

        JSONObject jsonObj = new JSONObject().put(USERNAME, "adm@leucotron.com").put(PASSWORD, "12345");
        given()
                .contentType(ContentType.JSON)
                .body(jsonObj.toString())
                .when()
                .post("/login")
                .then()
                .statusCode(401);
    }

    @Test
    public void contextLoads() {
    }


}
