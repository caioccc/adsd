package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.exception.RestException;
import br.com.leucotron.livre.dto.ProjectDTO;
import br.com.leucotron.livre.model.Project;
import br.com.leucotron.livre.service.ProjectService;
import br.com.leucotron.livre.util.JSonUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizations/{idOrganization}/projects")
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

    @RequestMapping(value = "/v1.0", method = RequestMethod.GET, params = {"filter"})
    public ResponseListDTO search(@PathVariable Integer idOrganization, @RequestParam("filter") String filterJSon) {
        SearchFilterDTO filter = JSonUtil.fromJSon(filterJSon, SearchFilterDTO.class);
        ResponseListDTO response = getService().searchByOrganization(idOrganization, filter);
        response.setItems(toListDTO(response.getItems()));

        return response;
    }

    @RequestMapping(value = "/all/v1.0", method = RequestMethod.GET, params = {"filter"})
    public ResponseListDTO search(@RequestParam("filter") String filterJSon) throws RestException {
        throw new RestException("Method not Acceptable");
    }

}
