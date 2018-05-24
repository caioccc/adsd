package br.com.leucotron.livre.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.dto.VariableDTO;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.model.Variable;
import br.com.leucotron.livre.repository.VariableRepository;

@Service
public class VariableService extends CrudService<Variable, Integer> {

    private static final String NOT_VALID_ID_PROJECT = "NotNull.variableDTO.idProject";
    private static final String NOT_VALID_USER_LOGIN = "NotValid.userDTO.login";
    private static final String NOT_VALID_VARIABLE_NAME = "NotValid.variable.name";
    private static final String USER_NOT_PERMISSION_CREATE_VARIABLE = "UserNotPermission.create.variable";

    
    @Autowired
    private VariableRepository repository;

    @Autowired
    private UserService userService;

    /**
     * (non-Javadoc)
     *
     * @see CrudService#getRepository()
     */
    @Override
    protected VariableRepository getRepository() {
        return repository;
    }
    
    public ResponseListDTO searchByVariable(Integer idProject, SearchFilterDTO filter) {
        return getRepository().searchByVariable(filter, idProject);
    }

    @Override
    public Variable processModelUpdate(Variable variable) throws BusinessException {
        return processProjectBeforeSaving(variable);
    }

    @Override
    public Variable processModelInsert(Variable variable) throws BusinessException {
        return processProjectBeforeSaving(variable);
    }
  
    @Override
    public void validateInsert(Variable variable) throws BusinessException {
        if (variable.getProject() == null) {
            throw new BusinessException(NOT_VALID_ID_PROJECT);
        }else if (repository.findByProjectIdProjectAndName(variable.getProject().getId(), variable.getName()).size() > 0) {
            throw new BusinessException(NOT_VALID_VARIABLE_NAME);
        }
    }
    
    public void validateIdOrganizationAssocietedUser(VariableDTO variableDTO) throws BusinessException {
       if(userService.getRepository().existsByLoginAndOrganizationsIdOrganization(getCurrentUser(),variableDTO.getIdOrganization())) {
        	throw new BusinessException(USER_NOT_PERMISSION_CREATE_VARIABLE);
        }
    }
    

    @Override
    public void validateUpdate(Variable variable) throws BusinessException {
        if (variable.getProject() == null) {
            throw new BusinessException(NOT_VALID_ID_PROJECT);
        }else if(repository.findByProjectIdProjectAndName(variable.getProject().getId(), variable.getName()).size() > 0) {
        	throw new BusinessException(NOT_VALID_VARIABLE_NAME);
        }

        List<Variable> variables = repository.findByProjectIdProjectAndName(variable.getProject().getId(), variable.getName());
        if (variables.size() > 0) {
            if (variables.get(0).getId() != variable.getId()) {
                throw new BusinessException(NOT_VALID_VARIABLE_NAME);
            }
        }
    }

    private Variable processProjectBeforeSaving(Variable variable) throws BusinessException {
        User user;
        try {
            user = userService.findByLogin(getCurrentUser()).get(0);
        } catch (NullPointerException ex) {
            throw new BusinessException(NOT_VALID_USER_LOGIN);
        }
        variable.setDateUpdate(new Date());
        variable.setUser(user);
        return variable;
    }

}
