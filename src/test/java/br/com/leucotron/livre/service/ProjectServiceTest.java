package br.com.leucotron.livre.service;

import br.com.leucotron.livre.LivreApplication;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.model.Project;
import br.com.leucotron.livre.util.FunctionalTest;
import br.com.leucotron.livre.util.JsonUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LivreApplication.class)
public class ProjectServiceTest extends FunctionalTest {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void insertProjectWithoutUser() throws BusinessException {

        thrown.expect(BusinessException.class);
        thrown.expectMessage("NotValid.userDTO.login");

        projectService.update(1, new Project(
                JsonUtil.PROJECT_NAME,
                JsonUtil.PROJECT_DESCRIPTION,
                JsonUtil.PROJECT_TAGS,
                JsonUtil.PROJECT_STATUS,
                new Date(),
                null, null
        ));

    }

    @Test
    public void updateProjectWithoutUser() throws BusinessException {

        thrown.expect(BusinessException.class);
        thrown.expectMessage("NotValid.userDTO.login");

        projectService.update(1, new Project(
                JsonUtil.PROJECT_NAME,
                JsonUtil.PROJECT_DESCRIPTION,
                JsonUtil.PROJECT_TAGS,
                JsonUtil.PROJECT_STATUS,
                new Date(),
                null, null
        ));

    }
}
