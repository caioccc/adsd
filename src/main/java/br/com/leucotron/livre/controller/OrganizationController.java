package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.dto.OrganizationDTO;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CRUD Controller for the model: Organization.
 *
 * @author Virtus
 */
@RestController
@RequestMapping("organizations")
public class OrganizationController extends CrudBaseController<Organization, Integer, OrganizationDTO> {

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
}
