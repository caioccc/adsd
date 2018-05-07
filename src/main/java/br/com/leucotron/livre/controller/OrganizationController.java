package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.dto.OrganizationDTO;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.service.OrganizationService;
import br.com.leucotron.livre.util.NullAwareBeanUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

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

}
