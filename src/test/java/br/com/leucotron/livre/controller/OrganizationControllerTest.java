package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.core.exception.BusinessException;
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
public class OrganizationControllerTest extends FunctionalTest {

    private static final String ACCESSKEY = "accesskey";
    private static final String NAME = "name";
    private static final String STATUS = "status";
    private static final String TAGS = "tags";
    private static final String ORG_NAME = "LeucotronOrg";
    private static final boolean ORG_STATUS_TRUE = true;
    private static final boolean ORG_STATUS_FALSE = false;
    private static final String ORG_TAGS = "a,ab,abc";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String PAGE_SIZE = "pageSize";
    public static final String FILTERS = "filters";
    public static final String FILTER = "filter";
    private static String URL = "/organizations";
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
                .post(URL).then().contentType(ContentType.JSON).extract();
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
                    .put(ACCESSKEY, GENERATOR.nextString())
                    .put(NAME, ORG_NAME + GENERATOR.nextString())
                    .put(STATUS, ORG_STATUS_TRUE)
                    .put(TAGS, ORG_TAGS);
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
        String id = getObjectCreatedOrganization().path("id").toString();
        JSONObject patchObj = null;
        try {
            patchObj = new JSONObject()
                    .put(ACCESSKEY, GENERATOR.nextString());
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
        String id = getObjectCreatedOrganization().path("id").toString();
        JSONObject patchObj = null;
        try {
            patchObj = new JSONObject()
                    .put(NAME, ORG_NAME + GENERATOR.nextString());
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
    public void patchStatusOrganization() {
        String id = getObjectCreatedOrganization().path("id").toString();
        JSONObject patchObj = null;
        try {
            patchObj = new JSONObject()
                    .put(STATUS, ORG_STATUS_FALSE);
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
    public void patchTagsOrganization() {
        String id = getObjectCreatedOrganization().path("id").toString();
        JSONObject patchObj = null;
        try {
            patchObj = new JSONObject()
                    .put(TAGS, ORG_TAGS);
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
        String id = getObjectCreatedOrganization().path("id").toString();
        JSONObject putObj = null;
        try {
            putObj = new JSONObject()
                    .put(ACCESSKEY, GENERATOR.nextString())
                    .put(NAME, ORG_NAME + GENERATOR.nextString())
                    .put(STATUS, ORG_STATUS_TRUE)
                    .put(TAGS, ORG_TAGS);
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
        String id = getObjectCreatedOrganization().path("id").toString();
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL + "/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    public void getOneOrganization() {
        String id = getObjectCreatedOrganization().path("id").toString();
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .get(URL + "/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    public void filterOrganization() {
        ExtractableResponse<Response> response = getObjectCreatedOrganization();
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject()
                    .put(CURRENT_PAGE, 1)
                    .put(PAGE_SIZE, 10)
                    .put(FILTERS, new JSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.getAuthRestAssured().param(FILTER, jsonObj.toString())
                .contentType(ContentType.JSON)
                .when()
                .get(URL)
                .then()
                .statusCode(200);
    }

}
