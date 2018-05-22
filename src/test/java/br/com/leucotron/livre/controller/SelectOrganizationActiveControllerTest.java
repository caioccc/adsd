package br.com.leucotron.livre.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.repository.OrganizationRepository;
import br.com.leucotron.livre.repository.UserRepository;
import br.com.leucotron.livre.util.FunctionalTest;
import br.com.leucotron.livre.util.RandomString;
import io.restassured.http.ContentType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LivreApplication.class)
public class SelectOrganizationActiveControllerTest extends FunctionalTest {

	private static final String ORG_NAME = "LeucotronOrg";
	private static final String ORG_NAME_1 = "LeucotronOrg1";
	private static final boolean ORG_STATUS_TRUE = true;
	private static final boolean ORG_STATUS_FALSE = false;
	private static final String ORG_TAGS = "a,ab,abc";
	private static final String CURRENT_PAGE = "currentPage";
	private static final String PAGE_SIZE = "pageSize";
	private static final String FILTERS = "filters";
	private static final String FILTER = "filter";
	private static final String COLUMN = "column";
	private static final String SORT = "sort";
	private static String URL = "/organizations/v1.0";
	private static String URL_SELECT_ORGANIZATION_ACTIVE = URL+"/current/user";
	private static RandomString GENERATOR = new RandomString(8, ThreadLocalRandom.current());
	
	@Autowired
	private OrganizationRepository crudBaseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	

	@Test
	public void OrganizationsActiveAndAssociatedWithCurrentUser() {

		createOrganizationWithUserAssociated(ORG_NAME, ORG_STATUS_TRUE);
		
		createOrganizationWithUserAssociated(ORG_NAME_1, ORG_STATUS_FALSE);
	
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject().put(CURRENT_PAGE, 1).put(PAGE_SIZE, 10).put(FILTERS, new JSONArray());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		this.getAuthRestAssured().param(FILTER, jsonObj.toString()).contentType(ContentType.JSON).when().get(URL_SELECT_ORGANIZATION_ACTIVE).then()
				.statusCode(200).and().body("items.size()",is(1));
		
		
		
	}

	@Test
	public void filterOrganization() {
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject().put(CURRENT_PAGE, 1).put(PAGE_SIZE, 10).put(FILTERS, new JSONArray());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.getAuthRestAssured().param(FILTER, jsonObj.toString()).contentType(ContentType.JSON).when().get(URL_SELECT_ORGANIZATION_ACTIVE).then()
				.statusCode(200);
	}

	@Test
	public void filterOrganizationSort() {
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject().put(CURRENT_PAGE, 1).put(PAGE_SIZE, 10).put(COLUMN, "name").put(SORT, "asc")
					.put(FILTERS, new JSONArray());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.getAuthRestAssured().param(FILTER, jsonObj.toString()).contentType(ContentType.JSON).when().get(URL_SELECT_ORGANIZATION_ACTIVE).then()
				.statusCode(200);
	}

	@Test
	public void filterOrganizationByName() {
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject().put(CURRENT_PAGE, 1).put(PAGE_SIZE, 10).put(FILTERS,
					new JSONArray("[{\"field\":\"name\",\"value\":\"name\",\"comparison\":\"LIKE\"}]"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.getAuthRestAssured().param(FILTER, jsonObj.toString()).contentType(ContentType.JSON).when().get(URL_SELECT_ORGANIZATION_ACTIVE).then()
				.statusCode(200);
	}

	@Test
	public void filterOrganizationByAccesskey() {
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject().put(CURRENT_PAGE, 1).put(PAGE_SIZE, 10).put(FILTERS,
					new JSONArray("[{\"field\":\"accesskey\",\"value\":\"z\",\"comparison\":\"LIKE\"}]"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.getAuthRestAssured().param(FILTER, jsonObj.toString()).contentType(ContentType.JSON).when().get(URL_SELECT_ORGANIZATION_ACTIVE).then()
				.statusCode(200);
	}

	@Test
	public void filterOrganizationByMultiTags() {
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject().put(CURRENT_PAGE, 1).put(PAGE_SIZE, 10).put(FILTERS,
					new JSONArray("[{\"field\":\"tags\",\"value\":\"aa,bb\",\"comparison\":\"IN\"}]"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.getAuthRestAssured().param(FILTER, jsonObj.toString()).contentType(ContentType.JSON).when().get(URL_SELECT_ORGANIZATION_ACTIVE).then()
				.statusCode(200);
	}

	@Test
	public void filterOrganizationByTagsAndStatus() {
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject().put(CURRENT_PAGE, 1).put(PAGE_SIZE, 10).put(FILTERS, new JSONArray(
					"[{\"field\":\"status\",\"value\":false,\"comparison\":\"EQ\"},{\"field\":\"tags\",\"value\":\"bb\",\"comparison\":\"IN\"}]"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.getAuthRestAssured().param(FILTER, jsonObj.toString()).contentType(ContentType.JSON).when().get(URL_SELECT_ORGANIZATION_ACTIVE).then()
				.statusCode(200);
	}

	@Test
	public void filterOrganizationByNotImplementComparison() {
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject().put(CURRENT_PAGE, 1).put(PAGE_SIZE, 10).put(FILTERS,
					new JSONArray("[{\"field\":\"name\",\"value\":\"name\",\"comparison\":\"NE\"}]"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.getAuthRestAssured().param(FILTER, jsonObj.toString()).contentType(ContentType.JSON).when().get(URL_SELECT_ORGANIZATION_ACTIVE).then()
				.statusCode(200);
	}

	@Test
	public void filterOrganizationByNameAndSort() {
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject().put(CURRENT_PAGE, 1).put(PAGE_SIZE, 10).put(COLUMN, "name").put(SORT, "asc")
					.put(FILTERS, new JSONArray("[{\"field\":\"name\",\"value\":\"name\",\"comparison\":\"OR\"}]"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.getAuthRestAssured().param(FILTER, jsonObj.toString()).contentType(ContentType.JSON).when().get(URL_SELECT_ORGANIZATION_ACTIVE).then()
				.statusCode(200);
	}
	
	private void createOrganizationWithUserAssociated(String name, boolean isActive) {
		
		Organization org = new Organization();
		org.setName(name);
		org.setAccesskey(GENERATOR.nextString());
		org.setStatus(isActive);
		org.setTags(ORG_TAGS);
		org = crudBaseRepository.save(org);
		
		User user = userRepository.findOne(1);
		
		org.setUsers(new ArrayList<User>() {{
		    add(user);
		}});

		//Associating the user
		org = crudBaseRepository.save(org);
		
	}

}
