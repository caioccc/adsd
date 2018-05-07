package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.util.FunctionalTest;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import org.apache.commons.httpclient.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LivreApplication.class)
public class RoleControllerTest extends FunctionalTest {

    private static String URL = "/roles/v1.0";
    public static final String ROLE = "role";

    @Test
    public void statusVerification() {
        this.getAuthRestAssured().when().get(URL).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createRole() {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject()
                    .put(ROLE, "newrole");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(jsonObj.toString())
                .when()
                .post(URL)
                .then()
                .statusCode(201);
    }

    @Test
    public void deleteRole() {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject()
                    .put(ROLE, "newrole");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ExtractableResponse jsonRole = this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(jsonObj.toString())
                .when()
                .post(URL)
                .then().extract();
        String id = jsonRole.path("id").toString();
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL + "/" + id)
                .then()
                .statusCode(200);
    }
}
