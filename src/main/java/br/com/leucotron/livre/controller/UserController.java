package br.com.leucotron.livre.controller;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.dto.AssociatedUserDTO;
import br.com.leucotron.livre.dto.OrganizationDTO;
import br.com.leucotron.livre.dto.UserDTO;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.service.OrganizationService;
import br.com.leucotron.livre.service.UserService;
import br.com.leucotron.livre.util.JSonUtil;
import br.com.leucotron.livre.util.MessageUtil;
import br.com.leucotron.livre.util.NullAwareBeanUtils;
import br.com.leucotron.livre.util.RestMessage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private OrganizationService organizationService;



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

    @RequestMapping(value = "/v1.0/organizations/{id}", method = RequestMethod.GET, params = {"filter"})
    public ResponseListDTO associatedUsersToOrgs(@PathVariable Integer id, @RequestParam("filter") String filterJSon, @RequestHeader("Accept-Language") Locale locale) {
        SearchFilterDTO filter = JSonUtil.fromJSon(filterJSon, SearchFilterDTO.class);
        ResponseListDTO response = getService().getAllUsersWithAssociated(id, filter);
        response.setItems(toList(response.getItems(), AssociatedUserDTO.class));
        return response;
    }

    @RequestMapping(value = "/v1.0/organizations/{id}", method = RequestMethod.GET)
    public ResponseListDTO associatedUsersToOrgsFull(@PathVariable Integer id, @RequestHeader("Accept-Language") Locale locale) {
        SearchFilterDTO filter = new SearchFilterDTO();
        filter.setCurrentPage(1);
        filter.setPageSize(1000);
        ResponseListDTO response = getService().getAllUsersWithAssociated(id, filter);
        response.setItems(toList(response.getItems(), AssociatedUserDTO.class));
        return response;
    }

    @RequestMapping(value = "/v1.0/organizations/{id}", method = RequestMethod.PUT)
    public ResponseEntity<List<AssociatedUserDTO>> putAssociatedUsersToOrgs(@PathVariable Integer id, @RequestBody List<AssociatedUserDTO> list, @RequestHeader("Accept-Language") Locale locale) {
        Organization dbOrg = organizationService.getOne(id);
        List<AssociatedUserDTO> associatedUserDTOS = toList(list, AssociatedUserDTO.class);

        associatedUserDTOS.forEach(associatedUserDTO -> {
            if(containsById(dbOrg.getUsers(), associatedUserDTO.getId())){
                if(!associatedUserDTO.isAssociated()){
                    dbOrg.getUsers().removeIf(obj -> obj.getIdUser() == associatedUserDTO.getId());
                }
            }else if(associatedUserDTO.isAssociated()){
                User user = mapTo(associatedUserDTO, User.class);
                dbOrg.getUsers().add(user);
            }
        });
        try {
            organizationService.update(id, dbOrg);
        } catch (BusinessException e) {
            return new ResponseEntity<>((List<AssociatedUserDTO>) new RestMessage(MessageUtil.findMessage(e.getMessage(), locale)), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    public static boolean containsById(List<User> list, Integer id) {
        for (User user : list) {
            if (user.getIdUser() == id) {
                return true;
            }
        }
        return false;
    }
}
