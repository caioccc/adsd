package br.com.leucotron.livre.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.core.dto.ModelDTO;
import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.exception.RestException;
import br.com.leucotron.livre.dto.DiagramDTO;
import br.com.leucotron.livre.model.Diagram;
import br.com.leucotron.livre.service.DiagramService;
import br.com.leucotron.livre.util.JSonUtil;
import br.com.leucotron.livre.util.MessageUtil;
import br.com.leucotron.livre.util.RestMessage;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/organizations/{idOrganization}/projects/{idProject}/diagrams")
@Api(value = "diagram", description = "Endpoint for operations in Diagrams")
public class DiagramController extends CrudBaseController<Diagram, Integer, DiagramDTO> {

    @Autowired
    private DiagramService service;

    /**
     * (non-Javadoc)
     *
     * @see CrudBaseController#getService()
     */
    @Override
    protected DiagramService getService() {
        return service;
    }

    @Override
    public ResponseEntity<DiagramDTO> insert(@Valid @RequestBody DiagramDTO modelDTO, @RequestHeader("Accept-Language") Locale locale) {
    	try {
            getService().validateIdOrganizationAssocietedUser(modelDTO);
        } catch (BusinessException e) {
            return notAcceptable(locale, e);
        }
        return super.insert(modelDTO, locale);
    }


    @Override
    public ResponseEntity<DiagramDTO> update(@Valid @PathVariable Integer id, @RequestBody DiagramDTO modelDTO, @RequestHeader("Accept-Language") Locale locale) {
        try {
            getService().validateIdOrganizationAssocietedUser(modelDTO);
        } catch (BusinessException e) {
            return notAcceptable(locale, e);
        }
        return super.update(id, modelDTO, locale);
    }

    @RequestMapping(value = "/v1.0", method = RequestMethod.GET, params = {"filter"})
    public ResponseListDTO search(@PathVariable Integer idOrganization, @PathVariable Integer idProject, @RequestParam("filter") String filterJSon) {
        SearchFilterDTO filter = JSonUtil.fromJSon(filterJSon, SearchFilterDTO.class);
        ResponseListDTO response = getService().searchByDiagram(idProject, filter);
        response.setItems(toListDTO(response.getItems()));

        return response;
    }

    @RequestMapping(value = "/all/v1.0", method = RequestMethod.GET, params = {"filter"})
    public ResponseEntity<ModelDTO> search(@RequestParam("filter") String filterJSon) throws RestException {
        return new ResponseEntity<>(new RestMessage(MessageUtil.findMessage("Method not Acceptable")), HttpStatus.SEE_OTHER);
    }

}
