package br.com.leucotron.livre.util;

import br.com.leucotron.livre.core.config.AppContext;
import br.com.leucotron.livre.core.dto.UserDTO;
import br.com.leucotron.livre.core.security.TokenAuthenticationService;
import br.com.leucotron.livre.model.User;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FunctionalTest {

    public static final String JDBC_URL = "jdbc:h2:mem:LIVRE";

    @Autowired
    private ApplicationContext context;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(JDBC_URL, "root", "livradmin");
        flyway.setLocations("classpath:db/migration");
        flyway.migrate();
        AppContext.loadApplicationContext(this.context);
        RestAssured.port = this.port;
    }

    protected RequestSpecification getAuthRestAssured() {
        return RestAssured.given().header(
                new Header(TokenAuthenticationService.HEADER, TokenAuthenticationService.generateToken("admin@leucotron.com")));
    }
}