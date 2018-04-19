package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.util.FunctionalTest;
import br.com.leucotron.livre.util.RandomString;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LivreApplication.class)
public class UserControllerTest extends FunctionalTest {

    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String TAGS = "tags";
    private static final String USER_NAME = "UserTest";
    private static final String USER_LOGIN = "user";
    private static final String USER_TAGS = "manager, adm";
    private static final String USER_PASSWORD = "admin123";
    private static String URL = "/users";
    private static RandomString GENERATOR = new RandomString(5, ThreadLocalRandom.current());

    private String getIdCreatedUser() {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject()
                    .put(LOGIN, USER_LOGIN + GENERATOR.nextString())
                    .put(NAME, USER_NAME + GENERATOR.nextString())
                    .put(PASSWORD, USER_PASSWORD)
                    .put(TAGS, USER_TAGS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return String.valueOf(this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(jsonObj.toString())
                .when()
                .post(URL).then().contentType(ContentType.JSON).extract().path("id").toString());
    }

    @Test
    public void statusVerification() {
        this.getAuthRestAssured().when().get(URL).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createOrganization() {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject()
                    .put(LOGIN, GENERATOR.nextString())
                    .put(NAME, USER_NAME + GENERATOR.nextString())
                    .put(PASSWORD, USER_LOGIN)
                    .put(TAGS, USER_TAGS);
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
    public void patchAccessKeyOrganization() {
        String id = getIdCreatedUser();
        JSONObject patchObj = null;
        try {
            patchObj = new JSONObject()
                    .put(LOGIN, GENERATOR.nextString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(patchObj.toString())
                .when()
                .patch(URL + "/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    public void patchNameOrganization() {
        String id = getIdCreatedUser();
        JSONObject patchObj = null;
        try {
            patchObj = new JSONObject()
                    .put(NAME, USER_NAME + GENERATOR.nextString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(patchObj.toString())
                .when()
                .patch(URL + "/" + id)
                .then()
                .statusCode(200);
    }

    //    TODO: The test fails because the POST request return a wrong response in create Organization.
    @Test
    public void patchStatusOrganization() {
        String id = getIdCreatedUser();
        JSONObject patchObj = null;
        try {
            patchObj = new JSONObject()
                    .put(PASSWORD, USER_TAGS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(patchObj.toString())
                .when()
                .patch(URL + "/" + id)
                .then()
                .statusCode(200);
    }

    //    TODO: The test fails because the POST request return a wrong response in create Organization.
    @Test
    public void patchTagsOrganization() {
        String id = getIdCreatedUser();
        JSONObject patchObj = null;
        try {
            patchObj = new JSONObject()
                    .put(TAGS, USER_TAGS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(patchObj.toString())
                .when()
                .patch(URL + "/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    public void putOrganization() {
        String id = getIdCreatedUser();
        JSONObject putObj = null;
        try {
            putObj = new JSONObject()
                    .put(LOGIN, GENERATOR.nextString())
                    .put(NAME, USER_NAME + GENERATOR.nextString())
                    .put(PASSWORD, USER_LOGIN)
                    .put(TAGS, USER_TAGS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(putObj.toString())
                .when()
                .put(URL + "/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteOrganization() {
        String id = getIdCreatedUser();
        JSONObject putObj = null;
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL + "/" + id)
                .then()
                .statusCode(200);
    }

}
