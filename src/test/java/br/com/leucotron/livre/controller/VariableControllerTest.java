package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.dto.VariableDTO;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.model.Project;
import br.com.leucotron.livre.model.Variable;
import br.com.leucotron.livre.repository.OrganizationRepository;
import br.com.leucotron.livre.repository.ProjectRepository;
import br.com.leucotron.livre.repository.VariableRepository;
import br.com.leucotron.livre.service.UserService;
import br.com.leucotron.livre.util.FunctionalTest;
import br.com.leucotron.livre.util.JsonUtil;
import br.com.leucotron.livre.util.MapperUtil;
import br.com.leucotron.livre.util.RandomString;
import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LivreApplication.class)
public class VariableControllerTest extends FunctionalTest {

    private static String URL = "/organizations/%s/projects/%s/variables/v1.0%s";
    private static RandomString GENERATOR = new RandomString(8, ThreadLocalRandom.current());

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    VariableRepository variableRepository;

    @Autowired
    UserService userService;

    @Test
    public void createVariableAndReturn201() throws JSONException {

        Project project = createProject();

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(createVariableJson(project.getIdProject(), project.getOrganization().getIdOrganization()).toString())
                .when()
                .post(String.format(URL, project.getOrganization().getIdOrganization(),
                        project.getIdProject(), ""))
                .then()
                .statusCode(org.springframework.http.HttpStatus.CREATED.value());
    }

    @Test
    public void updateVariableAndReturn200() {

        final String VARIABLE_UPDATE_NAME = "Variable " + GENERATOR.nextString();
        final String VARIABLE_UPDATE_DESCRIPTION = "Updated variable test";
        final String VARIABLE_UPDATE_TAGS = ",updated,";

        Variable variable = createVariable();

        variable.setDescription(VARIABLE_UPDATE_DESCRIPTION);
        variable.setName(VARIABLE_UPDATE_NAME);
        variable.setTags(VARIABLE_UPDATE_TAGS);

        VariableDTO pd = MapperUtil.mapTo(variable, VariableDTO.class);

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(pd)
                .when()
                .put(String.format(URL, variable.getProject().getOrganization().getIdOrganization(),
                        variable.getProject().getIdProject(), "/" + variable.getIdVariable()))
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value())
                .and()
                .body(
                        JsonUtil.NAME, equalTo(VARIABLE_UPDATE_NAME),
                        JsonUtil.DESCRIPTION, equalTo(VARIABLE_UPDATE_DESCRIPTION),
                        JsonUtil.TAGS, equalTo(VARIABLE_UPDATE_TAGS)
                );
    }

    @Test
    public void getVariableByIdAndReturn200() {

        Variable variable = createVariable();

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format(URL, variable.getProject().getOrganization().getId(),
                        variable.getProject().getIdProject(), "/" + variable.getIdVariable()))
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
    }

    @Test
    public void getAllVariablesByIdProjectAndReturn200() throws JSONException {

        Variable variable = createVariable();

        this.getAuthRestAssured()
                .param(JsonUtil.FILTER, createJsonForList().toString())
                .contentType(ContentType.JSON)
                .when()
                .get(String.format(URL, variable.getProject().getOrganization().getId(),
                        variable.getProject().getIdProject(), ""))
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value())
                .and()
                .body(
                        JsonUtil.TOTAL_PAGES, equalTo(1)
                );
    }

    @Test
    public void deleteVariableAndReturn200() {

        Variable variable = createVariable();

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format(URL, variable.getProject().getOrganization().getId(),
                        variable.getProject().getIdProject(), "/" + variable.getIdVariable()))
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
    }

    private JSONObject createJsonForList() throws JSONException {
        return JsonUtil.createJsonObject()
                .put(JsonUtil.CURRENT_PAGE, 1)
                .put(JsonUtil.PAGE_SIZE, 10)
                .put(JsonUtil.COLUMN, "name")
                .put(JsonUtil.SORT, "asc")
                .put(JsonUtil.FILTERS, new JSONArray("[]"));
    }

    private JSONObject createVariableJson(Integer idProject, Integer idOrganization) throws JSONException {
        return JsonUtil.createJsonObject()
                .put(JsonUtil.NAME, JsonUtil.VARIABLE_NAME + GENERATOR.nextString())
                .put(JsonUtil.DESCRIPTION, JsonUtil.DESCRIPTION)
                .put(JsonUtil.TAGS, JsonUtil.TAGS)
                .put(JsonUtil.TYPE, JsonUtil.VARIABLE_TYPE)
                .put(JsonUtil.ID_ORGANIZATION, idOrganization)
                .put(JsonUtil.ID_PROJECT, idProject);
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

    private Variable createVariable() {
        return variableRepository.save(
                new Variable(
                        JsonUtil.VARIABLE_NAME + GENERATOR.nextString(),
                        JsonUtil.DESCRIPTION,
                        JsonUtil.TAGS,
                        JsonUtil.VARIABLE_TYPE,
                        new Date(),
                        userService.findByLogin("admin@leucotron.com.br").get(0),
                        createProject()
                )
        );
    }

}
