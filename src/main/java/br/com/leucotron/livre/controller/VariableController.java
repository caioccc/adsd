package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.core.dto.ModelDTO;
import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.exception.RestException;
import br.com.leucotron.livre.dto.VariableDTO;
import br.com.leucotron.livre.model.Variable;
import br.com.leucotron.livre.service.VariableService;
import br.com.leucotron.livre.util.JSonUtil;
import br.com.leucotron.livre.util.MessageUtil;
import br.com.leucotron.livre.util.RestMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/organizations/{idOrganization}/projects/{idProject}/variables")
@Api(value = "variable", description = "Endpoint for operations in Variables")
public class VariableController extends CrudBaseController<Variable, Integer, VariableDTO> {

    @Autowired
    private VariableService service;

    /**
     * (non-Javadoc)
     *
     * @see CrudBaseController#getService()
     */
    @Override
    protected VariableService getService() {
        return service;
    }

    @Override
    public ResponseEntity<VariableDTO> insert(@Valid @RequestBody VariableDTO modelDTO, @RequestHeader("Accept-Language") Locale locale) {
        try {
            getService().validateIdOrganizationAssocietedUser(modelDTO);
        } catch (BusinessException e) {
            return notAcceptable(locale, e);
        }
        return super.insert(modelDTO, locale);
    }


    @Override
    public ResponseEntity<VariableDTO> update(@Valid @PathVariable Integer id, @RequestBody VariableDTO modelDTO, @RequestHeader("Accept-Language") Locale locale) {
        try {
            getService().validateIdOrganizationAssocietedUser(modelDTO);
        } catch (BusinessException e) {
            return notAcceptable(locale, e);
        }
        return super.update(id, modelDTO, locale);
    }

    /**
     * Searchs the Variable by Project with the filter.
     *
     * @return DTO with list of model founded and filtered.
     */
    @ApiOperation(value = "View a list of available variable", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/v1.0", method = RequestMethod.GET, params = {"filter"})
    public ResponseListDTO search(@PathVariable Integer idOrganization, @PathVariable Integer idProject, @RequestParam("filter") String filterJSon) {
        SearchFilterDTO filter = JSonUtil.fromJSon(filterJSon, SearchFilterDTO.class);
        ResponseListDTO response = getService().searchByVariable(idProject, filter);
        response.setItems(toListDTO(response.getItems()));

        return response;
    }

    @ApiOperation(value = "Method not Acceptable. Use /organizations/{idOrganization}/projects/{idProject}/v1.0 with project´s id", response = Iterable.class)
    @RequestMapping(value = "/all/v1.0", method = RequestMethod.GET, params = {"filter"})
    @ApiResponses(value = {
            @ApiResponse(code = 303, message = "Use /organizations/{idOrganization}/projects/{idProject} with organization's id and project´s id")
    })
    public ResponseEntity<ModelDTO> search(@RequestParam("filter") String filterJSon) throws RestException {
        return new ResponseEntity<>(new RestMessage(MessageUtil.findMessage("Method not Acceptable")), HttpStatus.SEE_OTHER);
    }

}
