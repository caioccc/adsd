package br.com.leucotron.livre.domain;

import br.com.leucotron.livre.LivreApplication;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AppContextTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void appComponent() {
        Assert.assertTrue(context.getBean(LivreApplication.class) != null);
    }

    @Test
    public void applicationContextLoaded() {
    }

    @Ignore
    public void applicationContextTest() {
        LivreApplication.main(new String[] {});
    }
}