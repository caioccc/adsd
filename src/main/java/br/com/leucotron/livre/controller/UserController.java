package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.dto.OrganizationDTO;
import br.com.leucotron.livre.dto.UserDTO;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.service.OrganizationService;
import br.com.leucotron.livre.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CRUD Controller for the model: User.
 *
 * @author Virtus
 */
@RestController
@RequestMapping("users")
@Api(value = "user", description = "Endpoint for operations in Users")
public class UserController extends CrudBaseController<User, Integer, UserDTO> {

    /**
     * user service.
     */
    @Autowired
    private UserService service;

    /**
     * (non-Javadoc)
     *
     * @see CrudBaseController#getService()
     */
    @Override
    protected UserService getService() {
        return service;
    }

    @Override
    protected User toModel(UserDTO modelDTO) {
        User model = super.toModel(modelDTO);
        model.setPassword(modelDTO.getNewPassword());

        return model;
    }


}
