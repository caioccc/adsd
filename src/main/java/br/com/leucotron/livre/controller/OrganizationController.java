package br.com.leucotron.livre.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.dto.CompleteOrganizationDTO;
import br.com.leucotron.livre.dto.OrganizationDTO;
import br.com.leucotron.livre.dto.UserDTO;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.service.OrganizationService;
import br.com.leucotron.livre.util.JSonUtil;
import br.com.leucotron.livre.util.NullAwareBeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CRUD Controller for the model: Organization.
 *
 * @author Virtus
 */
@RestController
@RequestMapping("organizations")
@Api(value="organization", description="Endpoint for operations in Organizations")
public class OrganizationController extends CrudBaseController<Organization, Integer, OrganizationDTO> {

    public static final String NOT_VALID_ORGANIZATION_DTO_NAME = "NotValid.organizationDTO.name";
    /**
     * Organization service.
     */
    @Autowired
    private OrganizationService service;

    /**
     * (non-Javadoc)
     *
     * @see CrudBaseController#getService()
     */
    @Override
    protected OrganizationService getService() {
        return service;
    }
    
    @ApiOperation(value = "View a list of organization active in user current")
    @RequestMapping(value = "/v1.0/current/user", method = RequestMethod.GET, params = {"filter"})
    public ResponseListDTO searchOrganizations(@RequestParam("filter") String filterJSon) {

    	SearchFilterDTO filter = JSonUtil.fromJSon(filterJSon, SearchFilterDTO.class);
        ResponseListDTO response = getService().getActiveOrganizationsByUser(filter);
        response.setItems(toListDTO(response.getItems()));

        return response;
    }


    @Override
    public ResponseEntity<OrganizationDTO> insert(@Valid @RequestBody OrganizationDTO modelDTO, @RequestHeader("Accept-Language") Locale locale) {
        try {
            Organization model = getService().insert(toModel(modelDTO));
            return created(toDTO(model));
        } catch (DataIntegrityViolationException e) {
            return notAcceptable(locale, NOT_VALID_ORGANIZATION_DTO_NAME);
        } catch (Exception e) {
            return notAcceptable(locale, e);
        }
    }

    @Override
    public ResponseEntity<OrganizationDTO> update(@PathVariable Integer id, @RequestBody OrganizationDTO modelDTO, @RequestHeader("Accept-Language") Locale locale) {
        try {
            getService().update(id, toModel(modelDTO));
            return ok(modelDTO);
        } catch (DataIntegrityViolationException e) {
            return notAcceptable(locale, NOT_VALID_ORGANIZATION_DTO_NAME);
        } catch (Exception e) {
            return notAcceptable(locale, e);
        }
    }

    @Override
    public ResponseEntity<OrganizationDTO> updatePartial(@PathVariable Integer id,@RequestBody OrganizationDTO modelDTO,@RequestHeader("Accept-Language") Locale locale) {
        try {
            Organization model = toModel(modelDTO);
            Organization dbModel = getService().getOne(id);
            NullAwareBeanUtils.getInstance().copyProperties(dbModel, model);
            getService().update(id, dbModel);
            return ok(modelDTO);
        } catch (DataIntegrityViolationException e) {
            return notAcceptable(locale, NOT_VALID_ORGANIZATION_DTO_NAME);
        } catch (IllegalAccessException e) {
            return notAcceptable(locale, e);
        } catch (BusinessException e) {
            return notAcceptable(locale, e);
        } catch (InvocationTargetException e) {
            return notAcceptable(locale, e);
        }
    }

    @GetMapping("/v1.0/{id}/users")
    public OrganizationDTO getOrganizationComplete(@PathVariable Integer id) {
        Organization model = super.getOneModel(id);

        CompleteOrganizationDTO dto = mapTo(model, CompleteOrganizationDTO.class);
        dto.setUsers(toList(model.getUsers(), UserDTO.class));

        return dto;
    }

}
