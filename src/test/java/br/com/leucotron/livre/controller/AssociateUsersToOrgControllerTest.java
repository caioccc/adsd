package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.util.FunctionalTest;
import br.com.leucotron.livre.util.RandomString;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.commons.httpclient.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LivreApplication.class)
public class AssociateUsersToOrgControllerTest extends FunctionalTest {

    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String PASSWORD = "newPassword";
    private static final String TAGS = "tags";
    private static final String USER_NAME = "UserTest";
    private static final String USER_LOGIN = "user";
    private static final String USER_TAGS = "manager; adm";
    private static final String USER_PASSWORD = "admin123";
    private static final String MAIL = "@gmail.com";
    private static final String ACCESSKEY = "accesskey";
    private static final String STATUS = "status";
    private static final String ORG_NAME = "LeucotronOrg";
    private static final boolean ORG_STATUS_TRUE = true;
    private static final String ORG_TAGS = "a,ab,abc";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String PAGE_SIZE = "pageSize";
    private static final String FILTERS = "filters";
    private static final String FILTER = "filter";
    private static final String COLUMN = "column";
    private static final String SORT = "sort";
    public static final String ID = "id";
    public static final String ASSOCIATED = "associated";
    public static final String SEARCH = "search";
    public static final String NAME_SEARCH_COLUMN = "name";
    public static final String SORT_ASC = "asc";
    public static final String ASC = "asc";
    public static final int FIRST_PAGE = 1;
    public static final int LENGTH_PAGE = 10;
    public static final String COLUMN_NAME = "name";
    private static String URL = "/users/v1.0/organizations";
    private static String URL_ORGANIZATIONS = "/organizations/v1.0";
    private static String URL_USERS = "/users/v1.0";
    private static RandomString GENERATOR = new RandomString(8, ThreadLocalRandom.current());

    private ExtractableResponse<Response> getObjectCreatedOrganization() {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject()
                    .put(ACCESSKEY, GENERATOR.nextString())
                    .put(NAME, ORG_NAME + GENERATOR.nextString())
                    .put(STATUS, ORG_STATUS_TRUE)
                    .put(TAGS, ORG_TAGS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(jsonObj.toString())
                .when()
                .post(URL_ORGANIZATIONS).then().contentType(ContentType.JSON).extract();
    }

    private ExtractableResponse<Response> getObjectCreatedUser() {
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
                .post(URL_USERS).then().contentType(ContentType.JSON).extract();
    }

    @Test
    public void statusVerification() {
        ExtractableResponse<Response> obj = getObjectCreatedOrganization();
        String idOrganization = obj.path("id").toString();

        JSONObject filterJson = null;
        try {
            filterJson = new JSONObject()
                    .put(CURRENT_PAGE, FIRST_PAGE)
                    .put(PAGE_SIZE, LENGTH_PAGE)
                    .put(COLUMN, COLUMN_NAME)
                    .put(SORT, SORT_ASC)
                    .put(ASSOCIATED, true)
                    .put(FILTERS, new JSONArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured().param(FILTER, filterJson.toString())
                .when().get(URL + "/" + idOrganization).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void linkUsersToOrganization() {
        String idOrganization = getObjectCreatedOrganization().path("id").toString();
        ExtractableResponse<Response> userObj = getObjectCreatedUser();
        String idUser = userObj.path("id").toString();
        String nameUser = userObj.path("name").toString();

        JSONArray jsonArray = null;
        JSONObject jsonUser;
        try {
            jsonUser = new JSONObject()
                    .put(ID, idUser)
                    .put(NAME, nameUser)
                    .put(ASSOCIATED, false);
            jsonArray = new JSONArray().put(jsonUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(jsonArray.toString())
                .when()
                .put(URL + "/" + idOrganization)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void filterOrganization() {
        ExtractableResponse<Response> orgObj = getObjectCreatedOrganization();
        String nameOrg = orgObj.path("name").toString();
        String idOrganization = orgObj.path("id").toString();
        ExtractableResponse<Response> userObj = getObjectCreatedUser();
        String idUser = userObj.path("id").toString();
        String nameUser = userObj.path("name").toString();

        JSONArray jsonArray = null;
        JSONObject jsonUser;
        try {
            jsonUser = new JSONObject()
                    .put(ID, idUser)
                    .put(NAME, nameUser)
                    .put(ASSOCIATED, false);
            jsonArray = new JSONArray().put(jsonUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(jsonArray.toString())
                .when()
                .put(URL + "/" + idOrganization)
                .then()
                .statusCode(HttpStatus.SC_OK);

        JSONObject filterJson = null;
        try {
            filterJson = new JSONObject()
                    .put(CURRENT_PAGE, FIRST_PAGE)
                    .put(PAGE_SIZE, LENGTH_PAGE)
                    .put(SEARCH, nameOrg)
                    .put(COLUMN, NAME_SEARCH_COLUMN)
                    .put(SORT, ASC)
                    .put(ASSOCIATED, true)
                    .put(FILTERS, new JSONArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured().param(FILTER, filterJson.toString())
                .when().get(URL + "/" + idOrganization).then().statusCode(HttpStatus.SC_OK);
    }
}
