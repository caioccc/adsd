package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.util.FunctionalTest;
import br.com.leucotron.livre.util.RandomString;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
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
public class RoleControllerTest extends FunctionalTest {

    private static final String MAIL = "@gmail.com";
    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String PASSWORD = "newPassword";
    private static final String TAGS = "tags";
    private static final String USER_NAME = "UserTest";
    private static final String USER_LOGIN = "user";
    private static final String USER_TAGS = "manager; adm";
    private static final String USER_PASSWORD = "admin123";
    public static final String SEARCH = "search";
    public static final String ADMIN_TAG_SEARCH = "ad";
    private static String URL = "/roles/v1.0";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String PAGE_SIZE = "pageSize";
    public static final String SORT = "sort";
    public static final String COLUMN = "column";
    public static final String ONE = "1";
    public static final String TEN = "10";
    public static final String ASC = "asc";
    public static final String NAME_COLUMN = "name";
    public static final String DESC = "desc";
    public static final String ROLE = "role";
    public static final String USER_ROLE = "usuario";

    private static RandomString GENERATOR = new RandomString(5, ThreadLocalRandom.current());

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
