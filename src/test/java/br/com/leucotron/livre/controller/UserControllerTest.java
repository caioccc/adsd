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
public class UserControllerTest extends FunctionalTest {

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
    private static String URL = "/users/v1.0";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String PAGE_SIZE = "pageSize";
    public static final String SORT = "sort";
    public static final String COLUMN = "column";
    public static final String ONE = "1";
    public static final String TEN = "10";
    public static final String ASC = "asc";
    public static final String NAME_COLUMN = "name";
    public static final String DESC = "desc";

    private static RandomString GENERATOR = new RandomString(5, ThreadLocalRandom.current());

    private ExtractableResponse<Response> getCreatedUser() {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject()
                    .put(LOGIN, USER_LOGIN + GENERATOR.nextString() + MAIL)
                    .put(NAME, USER_NAME + GENERATOR.nextString())
                    .put(PASSWORD, USER_PASSWORD)
                    .put(TAGS, USER_TAGS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(jsonObj.toString())
                .when()
                .post(URL).then().contentType(ContentType.JSON).extract();
    }

    @Test
    public void statusVerification() {
        this.getAuthRestAssured().when().get(URL).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createUser() {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject()
                    .put(LOGIN, USER_LOGIN + GENERATOR.nextString() + MAIL)
                    .put(NAME, USER_NAME + GENERATOR.nextString())
                    .put(PASSWORD, USER_PASSWORD)
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
    public void patchLoginUser() {
        String id = getCreatedUser().path("id").toString();
        JSONObject patchObj = null;
        try {
            patchObj = new JSONObject()
                    .put(LOGIN, USER_LOGIN + GENERATOR.nextString() + MAIL);
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
    public void patchNameUser() {
        String id = getCreatedUser().path("id").toString();
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


    @Test
    public void patchTagsUser() {
        String id = getCreatedUser().path("id").toString();
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
    public void putUser() {
        String id = getCreatedUser().path("id").toString();
        JSONObject putObj = null;
        try {
            putObj = new JSONObject()
                    .put(LOGIN, USER_LOGIN + GENERATOR.nextString() + MAIL)
                    .put(NAME, USER_NAME + GENERATOR.nextString())
                    .put(PASSWORD, USER_PASSWORD)
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
    public void deleteUser() {
        String id = getCreatedUser().path("id").toString();
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL + "/" + id)
                .then()
                .statusCode(200);
    }

    public void filterUserAsc() {
        JSONObject jsonFilter = null;
        try {
            jsonFilter = new JSONObject()
                    .put(CURRENT_PAGE, ONE)
                    .put(PAGE_SIZE, TEN)
                    .put(SORT, ASC)
                    .put(COLUMN, NAME_COLUMN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jsonFilter.toString());
        this.getAuthRestAssured().when().param("filter", jsonFilter).get(URL).then().statusCode(200);
    }

    public void filterUserDesc() {
        JSONObject jsonFilter = null;
        try {
            jsonFilter = new JSONObject()
                    .put(CURRENT_PAGE, ONE)
                    .put(PAGE_SIZE, TEN)
                    .put(SORT, DESC)
                    .put(COLUMN, NAME_COLUMN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jsonFilter.toString());
        this.getAuthRestAssured().when().param("filter", jsonFilter).get(URL).then().statusCode(200);
    }

    public void filterUserAdmin() {
        JSONObject jsonFilter = null;
        try {
            jsonFilter = new JSONObject()
                    .put(CURRENT_PAGE, ONE)
                    .put(PAGE_SIZE, TEN)
                    .put(SORT, DESC)
                    .put(SEARCH, ADMIN_TAG_SEARCH)
                    .put(COLUMN, NAME_COLUMN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jsonFilter.toString());
        this.getAuthRestAssured().when().param("filter", jsonFilter).get(URL).then().statusCode(200);
    }

    @Test
    public void deleteUserError() {
        String id = getCreatedUser().path("id").toString();
        System.out.println(this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL + "/" + 9999999)
                .then().extract().body().toString());
    }


}
