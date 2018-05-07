package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.dto.OrganizationDTO;
import br.com.leucotron.livre.dto.UserDTO;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.service.OrganizationService;
import br.com.leucotron.livre.service.UserService;
import br.com.leucotron.livre.util.NullAwareBeanUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/**
 * CRUD Controller for the model: User.
 *
 * @author Virtus
 */
@RestController
@RequestMapping("users")
@Api(value = "user", description = "Endpoint for operations in Users")
public class UserController extends CrudBaseController<User, Integer, UserDTO> {

    public static final String NOT_VALID_USER_DTO_LOGIN = "NotValid.userDTO.login";
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
        model.setMaster(false);
        return model;
    }

    @Override
    public ResponseEntity<UserDTO> updatePartial(@PathVariable Integer id, @RequestBody UserDTO modelDTO, @RequestHeader("Accept-Language") Locale locale) {
        try {
            User model = toModel(modelDTO);
            User dbModel = getService().getOne(id);
            NullAwareBeanUtils.getInstance().copyProperties(dbModel, model);
            getService().update(id, dbModel);
            return ok(modelDTO);
        } catch (DataIntegrityViolationException e) {
            return notAcceptable(locale, NOT_VALID_USER_DTO_LOGIN);
        } catch (IllegalAccessException e) {
            return notAcceptable(locale, e);
        } catch (BusinessException e) {
            return notAcceptable(locale, e);
        } catch (InvocationTargetException e) {
            return notAcceptable(locale, e);
        }
    }
}
