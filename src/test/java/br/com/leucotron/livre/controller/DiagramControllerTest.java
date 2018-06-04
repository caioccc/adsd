package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.dto.DiagramDTO;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.model.Project;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.model.Diagram;
import br.com.leucotron.livre.repository.OrganizationRepository;
import br.com.leucotron.livre.repository.ProjectRepository;
import br.com.leucotron.livre.repository.DiagramRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LivreApplication.class)
public class DiagramControllerTest extends FunctionalTest {

    private static String URL = "/organizations/%s/projects/%s/diagrams/v1.0%s";
    private static RandomString GENERATOR = new RandomString(8, ThreadLocalRandom.current());

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    DiagramRepository diagramRepository;

    @Autowired
    UserService userService;

    @Test
    public void createDiagramAndReturn201() throws JSONException {

        Project project = createProject();
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(createDiagramJson(project.getIdProject(), project.getOrganization().getIdOrganization()).toString())
                .when()
                .post(String.format(URL, project.getOrganization().getIdOrganization(),
                        project.getIdProject(), ""))
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void updateDiagramAndReturn200() throws JSONException {

        final String DIAGRAM_UPDATE_NAME = "Diagram" + GENERATOR.nextString();
        final String DIAGRAM_UPDATE_VERSION = "Updated diagram test";
        final String DIAGRAM_UPDATE_TAGS = ",updated,";
        Diagram diagram = createDiagram();
        diagram.setVersion(DIAGRAM_UPDATE_VERSION);
        diagram.setName(DIAGRAM_UPDATE_NAME);
        diagram.setTags(DIAGRAM_UPDATE_TAGS);

        DiagramDTO pd = MapperUtil.mapTo(diagram, DiagramDTO.class);
        pd.setIdOrganization(diagram.getProject().getOrganization().getIdOrganization());
        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .body(pd)
                .when()
                .put(String.format(URL, diagram.getProject().getOrganization().getId(),
                        diagram.getProject().getId(), "/" + diagram.getIdDiagram()))
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .body(
                        JsonUtil.NAME, equalTo(DIAGRAM_UPDATE_NAME),
                        JsonUtil.VERSION, equalTo(DIAGRAM_UPDATE_VERSION),
                        JsonUtil.TAGS, equalTo(DIAGRAM_UPDATE_TAGS)
                );
    }

    @Test
    public void getDiagramByIdAndReturn200() {

        Diagram diagram = createDiagram();

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format(URL, diagram.getProject().getOrganization().getId(),
                        diagram.getProject().getIdProject(), "/" + diagram.getIdDiagram()))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void getAllDiagramsByIdProjectAndReturn200() throws JSONException {

        Diagram diagram = createDiagram();

        this.getAuthRestAssured()
                .param(JsonUtil.FILTER, createJsonForList().toString())
                .contentType(ContentType.JSON)
                .when()
                .get(String.format(URL, diagram.getProject().getOrganization().getId(),
                        diagram.getProject().getIdProject(), ""))
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .body(
                        JsonUtil.TOTAL_PAGES, equalTo(1)
                );
    }

    @Test
    public void deleteDiagramAndReturn200() {

        Diagram diagram = createDiagram();

        this.getAuthRestAssured()
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format(URL, diagram.getProject().getOrganization().getId(),
                        diagram.getProject().getIdProject(), "/" + diagram.getIdDiagram()))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    private JSONObject createJsonForList() throws JSONException {
        return JsonUtil.createJsonObject()
                .put(JsonUtil.CURRENT_PAGE, 1)
                .put(JsonUtil.PAGE_SIZE, 10)
                .put(JsonUtil.COLUMN, "name")
                .put(JsonUtil.SORT, "asc")
                .put(JsonUtil.FILTERS, new JSONArray("[]"));
    }

    private JSONObject createDiagramJson(Integer idProject, Integer idOrganization) throws JSONException {
        return JsonUtil.createJsonObject()
                .put(JsonUtil.NAME, JsonUtil.DIAGRAM_NAME + GENERATOR.nextString())
                .put(JsonUtil.DESCRIPTION, JsonUtil.DESCRIPTION)
                .put(JsonUtil.TAGS, JsonUtil.TAGS)
                .put(JsonUtil.START_DATE, JsonUtil.DIAGRAM_START_DATE)
                .put(JsonUtil.END_DATE, JsonUtil.DIAGRAM_END_DATE)
                .put(JsonUtil.VERSION, JsonUtil.DIAGRAM_VERSION)
                .put(JsonUtil.ID_ORGANIZATION, idOrganization)
                .put(JsonUtil.ID_PROJECT, idProject);
    }

    private Organization createOrganization() {
        User user = userService.findByLogin("admin@leucotron.com.br").get(0);
        Organization organization = new Organization("Organization" + GENERATOR.nextString(),
                false, null, "key1234");
        organization = organizationRepository.save(organization);
        List<User> listUsers = new ArrayList<>();
        listUsers.add(user);
        organization.setUsers(listUsers);
        return organizationRepository.save(organization);

    }

    private Project createProject() {
        Organization organization = createOrganization();
        User user = userService.findByLogin("admin@leucotron.com.br").get(0);
        Project project = projectRepository.save(
                new Project(
                        JsonUtil.PROJECT_NAME + GENERATOR.nextString(),
                        JsonUtil.PROJECT_DESCRIPTION,
                        JsonUtil.PROJECT_TAGS,
                        JsonUtil.PROJECT_STATUS,
                        new Date(),
                        user,
                        organization
                )
        );
        return project;
    }

    private Diagram createDiagram() {
        Project project = createProject();
        return diagramRepository.save(
                new Diagram(
                        JsonUtil.DIAGRAM_NAME + GENERATOR.nextString(),
                        JsonUtil.DESCRIPTION,
                        JsonUtil.TAGS,
                        new Date(),
                        new Date(),
                        new Date(),
                        userService.findByLogin("admin@leucotron.com.br").get(0),
                        project
                )
        );
    }

}
