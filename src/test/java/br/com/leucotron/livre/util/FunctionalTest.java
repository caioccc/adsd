package br.com.leucotron.livre.util;

import br.com.leucotron.livre.core.config.AppContext;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FunctionalTest {

    @Autowired
    private ApplicationContext context;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {

        AppContext.loadApplicationContext(this.context);
        RestAssured.port = this.port;
    }
}