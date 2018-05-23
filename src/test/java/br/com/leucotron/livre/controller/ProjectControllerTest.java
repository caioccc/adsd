package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.dto.ProjectDTO;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.model.Project;
import br.com.leucotron.livre.repository.OrganizationRepository;
import br.com.leucotron.livre.repository.ProjectRepository;
import br.com.leucotron.livre.service.UserService;
import br.com.leucotron.livre.util.FunctionalTest;
import br.com.leucotron.livre.util.JsonUtil;
import br.com.leucotron.livre.util.MapperUtil;
import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LivreApplication.class)
public class ProjectControllerTest extends FunctionalTest {

    private static final String URL = "/organizations/%s/projects/v1.0%s";

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserService userService;

    @Test
    public void createProjectAndReturn201() throws JSONException {

        Organization organization = createOrganization();

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(createProjectJson(organization.getId()).toString())
                .when()
                .post(String.format(URL, organization.getId(), ""))
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .body(
                        JsonUtil.NAME, equalTo(JsonUtil.PROJECT_NAME),
                        JsonUtil.STATUS, equalTo(JsonUtil.PROJECT_STATUS),
                        JsonUtil.DESCRIPTION, equalTo(JsonUtil.PROJECT_DESCRIPTION),
                        JsonUtil.TAGS, equalTo(JsonUtil.PROJECT_TAGS),
                        JsonUtil.ID_ORGANIZATION, equalTo(organization.getId()),
                        JsonUtil.USER, equalTo("Administrador"),
                        JsonUtil.DATE_UPDATE, notNullValue()
                );
    }

    @Test
    public void updateProjectAndReturn200() {

        final String PROJECT_UPDATE_NAME = "Project X updated";
        final String PROJECT_UPDATE_DESCRIPTION = "Updated project test";
        final String PROJECT_UPDATE_TAGS = ",updated,";
        final boolean PROJECT_UPDATE_STATUS = false;

        Project project = createProject();

        project.setStatus(PROJECT_UPDATE_STATUS);
        project.setName(PROJECT_UPDATE_NAME);
        project.setDescription(PROJECT_UPDATE_DESCRIPTION);
        project.setTags(PROJECT_UPDATE_TAGS);

        ProjectDTO pd = MapperUtil.mapTo(project, ProjectDTO.class);

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(pd)
                .when()
                .put(String.format(URL, project.getOrganization().getId(), "/" + project.getId()))
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .body(
                        JsonUtil.NAME, equalTo(PROJECT_UPDATE_NAME),
                        JsonUtil.STATUS, equalTo(PROJECT_UPDATE_STATUS),
                        JsonUtil.DESCRIPTION, equalTo(PROJECT_UPDATE_DESCRIPTION),
                        JsonUtil.TAGS, equalTo(PROJECT_UPDATE_TAGS),
                        JsonUtil.USER, equalTo("Administrador"),
                        JsonUtil.ID_ORGANIZATION, equalTo(project.getOrganization().getId()),
                        JsonUtil.DATE_UPDATE, notNullValue()
                );
    }

    @Test
    public void updateProjectWithoutUpdateNameAndReturn200() {

        final String PROJECT_UPDATE_DESCRIPTION = "Updated project test";
        final String PROJECT_UPDATE_TAGS = ",updated,";
        final boolean PROJECT_UPDATE_STATUS = false;

        Project project = createProject();

        project.setStatus(PROJECT_UPDATE_STATUS);
        project.setDescription(PROJECT_UPDATE_DESCRIPTION);
        project.setTags(PROJECT_UPDATE_TAGS);

        ProjectDTO pd = MapperUtil.mapTo(project, ProjectDTO.class);

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(pd)
                .when()
                .put(String.format(URL, project.getOrganization().getId(), "/" + project.getId()))
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .body(
                        JsonUtil.NAME, equalTo(JsonUtil.PROJECT_NAME),
                        JsonUtil.STATUS, equalTo(PROJECT_UPDATE_STATUS),
                        JsonUtil.DESCRIPTION, equalTo(PROJECT_UPDATE_DESCRIPTION),
                        JsonUtil.TAGS, equalTo(PROJECT_UPDATE_TAGS),
                        JsonUtil.USER, equalTo("Administrador"),
                        JsonUtil.ID_ORGANIZATION, equalTo(project.getOrganization().getId()),
                        JsonUtil.DATE_UPDATE, notNullValue()
                );
    }

    @Test
    public void getProjectByIdAndReturn200() {

        Project project = createProject();

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format(URL, project.getOrganization().getId(), "/" + project.getId()))
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .body(
                        JsonUtil.NAME, equalTo(JsonUtil.PROJECT_NAME),
                        JsonUtil.STATUS, equalTo(JsonUtil.PROJECT_STATUS),
                        JsonUtil.DESCRIPTION, equalTo(JsonUtil.PROJECT_DESCRIPTION),
                        JsonUtil.TAGS, equalTo(JsonUtil.PROJECT_TAGS),
                        JsonUtil.ID_ORGANIZATION, equalTo(project.getOrganization().getId()),
                        JsonUtil.USER, equalTo("Administrador"),
                        JsonUtil.DATE_UPDATE, notNullValue()
                );
    }

    @Test
    public void getProjectAllByIdOrganizationAndReturn200() throws JSONException {

        Project project = createProject();

        this.getAuthRestAssured()
                .param(JsonUtil.FILTER, createJsonForList().toString())
                .contentType(ContentType.JSON)
                .when()
                .get(String.format(URL, project.getOrganization().getId(), ""))
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .body(
                        JsonUtil.TOTAL_PAGES, equalTo(1),
                        JsonUtil.ITEMS + "[0]." + JsonUtil.NAME, equalTo(JsonUtil.PROJECT_NAME)
                );
    }

    @Test
    public void deleteProjectAndReturn200() {

        Project project = createProject();

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format(URL, project.getOrganization().getId(),  "/" + project.getId()))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void getAllProjectsAndReturn303() throws JSONException {

        this.getAuthRestAssured()
                .param(JsonUtil.FILTER, createJsonForList().toString())
                .contentType(ContentType.JSON)
                .redirects().follow(false)
                .when()
                .get("organizations/10/projects/all/v1.0")
                .then()
                .statusCode(HttpStatus.SEE_OTHER.value());
    }

    @Test
    public void createProjectSameNameAndSameOrganization() throws JSONException {

        Project project = createProject();

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(createProjectJson(project.getOrganization().getId()).toString())
                .when()
                .post(String.format(URL, project.getOrganization().getId(), ""))
                .then()
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value());
    }

    @Test
    public void updateProjectSameNameAndSameOrganization() throws JSONException {

        Project project = createProject();

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(createProjectJson(project.getOrganization().getId()).toString())
                .when()
                .put(String.format(URL, project.getOrganization().getId(), "/1000"))
                .then()
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value());
    }

    private JSONObject createJsonForList() throws JSONException {
        return JsonUtil.createJsonObject()
                .put(JsonUtil.CURRENT_PAGE, 1)
                .put(JsonUtil.PAGE_SIZE, 10)
                .put(JsonUtil.COLUMN, "name")
                .put(JsonUtil.SORT, "asc")
                .put(JsonUtil.FILTERS, new JSONArray("[]"));
    }

    private JSONObject createProjectJson(Integer IdOrganization) throws JSONException {
        return JsonUtil.createJsonObject()
                .put(JsonUtil.NAME, JsonUtil.PROJECT_NAME)
                .put(JsonUtil.STATUS, JsonUtil.PROJECT_STATUS)
                .put(JsonUtil.TAGS, JsonUtil.PROJECT_TAGS)
                .put(JsonUtil.DESCRIPTION, JsonUtil.PROJECT_DESCRIPTION)
                .put(JsonUtil.ID_ORGANIZATION, IdOrganization);
    }

    private Organization createOrganization() {
        return organizationRepository.save(new Organization("Organization", true, null, "key1234"));
    }

    private Project createProject() {
        return projectRepository.save(
                new Project(
                        JsonUtil.PROJECT_NAME,
                        JsonUtil.PROJECT_DESCRIPTION,
                        JsonUtil.PROJECT_TAGS,
                        JsonUtil.PROJECT_STATUS,
                        new Date(),
                        userService.findByLogin("admin@leucotron.com.br").get(0), createOrganization()
                )
        );
    }
}
