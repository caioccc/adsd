package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.util.FunctionalTest;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LivreApplication.class)
public class FlowControllerTest extends FunctionalTest {

    public static final String CURRENT_PAGE = "currentPage";
    public static final String PAGE_SIZE = "pageSize";
    public static final String FILTERS = "filters";
    public static final String FILTER = "filter";
    private static String URL = "/flow/elements";

    @Test
    public void statusVerification() {
        this.getAuthRestAssured().when().get(URL).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void filterOrganization() {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject()
                    .put(CURRENT_PAGE, 1)
                    .put(PAGE_SIZE, 10)
                    .put(FILTERS, new JSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObj.toString());
        this.getAuthRestAssured().param(FILTER, jsonObj.toString())
                .contentType(ContentType.JSON)
                .when()
                .get(URL)
                .then()
                .statusCode(200);
    }


}
