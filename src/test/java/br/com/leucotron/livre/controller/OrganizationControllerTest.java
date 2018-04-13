package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.util.FunctionalTest;
import br.com.leucotron.livre.util.RandomString;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadLocalRandom;

//TODO: Blocked Tests
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = LivreApplication.class)
public class OrganizationControllerTest extends FunctionalTest {

    private static final String ACCESSKEY = "accesskey";
    private static final String NAME = "name";
    private static final String STATUS = "status";
    private static final String TAGS = "tags";
    private static final String ORG_NAME = "LeucotronOrg";
    private static final boolean ORG_STATUS_TRUE = true;
    private static final boolean ORG_STATUS_FALSE = false;
    private static final String ORG_TAGS = "a,ab,abc";
    private static String URL = "/organizations";
    private static RandomString GENERATOR = new RandomString(8, ThreadLocalRandom.current());

    private String getIdCreatedOrganization() {
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
                .post(URL).then().contentType(ContentType.JSON).extract().path("id");
    }

    //    TODO: The test fails because the GET request for "organizations" requires a filter parameter as required.
    @Ignore
    public void statusVerification() {
        this.getAuthRestAssured().when().get(URL).then().statusCode(HttpStatus.SC_OK);
    }

    //    TODO: The test fails because the POST request return 200 instead of 201.
    @Ignore
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

    //TODO: The test fails because the POST request return a wrong response in create Organization.
    @Ignore
    public void patchAccessKeyOrganization() {
        String id = getIdCreatedOrganization();
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

    //TODO: The test fails because the POST request return a wrong response in create Organization.
    @Ignore
    public void patchNameOrganization() {
        String id = getIdCreatedOrganization();
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

    //    TODO: The test fails because the POST request return a wrong response in create Organization.
    @Ignore
    public void patchStatusOrganization() {
        String id = getIdCreatedOrganization();
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

    //    TODO: The test fails because the POST request return a wrong response in create Organization.
    @Ignore
    public void patchTagsOrganization() {
        String id = getIdCreatedOrganization();
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

    //    TODO: The test fails because the POST request return a wrong response in create Organization.
    @Ignore
    public void putOrganization() {
        String id = getIdCreatedOrganization();
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

    //    TODO: The test fails because the POST request return a wrong response in create Organization.
    @Ignore
    public void deleteOrganization() {
        String id = getIdCreatedOrganization();
        JSONObject putObj = null;
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL + "/" + id)
                .then()
                .statusCode(200);
    }

}
