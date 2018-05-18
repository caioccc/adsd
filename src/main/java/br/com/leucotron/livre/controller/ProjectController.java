package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.dto.ProjectDTO;
import br.com.leucotron.livre.model.Project;
import br.com.leucotron.livre.service.ProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projects")
@Api(value = "project", description = "Endpoint for operations in Projects")
public class ProjectController extends CrudBaseController<Project, Integer, ProjectDTO> {


    @Autowired
    private ProjectService service;
    /**
     * (non-Javadoc)
     *
     * @see CrudBaseController#getService()
     */
    @Override
    protected ProjectService getService() {
        return service;
    }

}
