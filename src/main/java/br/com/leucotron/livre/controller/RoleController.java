package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.dto.RoleDTO;
import br.com.leucotron.livre.model.Role;
import br.com.leucotron.livre.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CRUD Controller for the model: Role.
 *
 * @author Virtus
 */
@RestController
@RequestMapping("roles")
@Api(value="role", description="Endpoint for operations in Roles")
public class RoleController extends CrudBaseController<Role, Integer, RoleDTO> {

    /**
     * Role service.
     */
    @Autowired
    private RoleService service;

    /**
     * (non-Javadoc)
     *
     * @see CrudBaseController#getService()
     */
    @Override
    protected RoleService getService() {
        return service;
    }
}
