package br.com.leucotron.livre.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.model.Project;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.model.Variable;
import br.com.leucotron.livre.repository.VariableRepository;

public class VariableService extends CrudService<Variable, Integer> {

    private static final String NOT_VALID_ID_PROJECT = "NotNull.variableDTO.idProject";
    private static final String NOT_VALID_USER_LOGIN = "NotValid.userDTO.login";

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

    public ResponseListDTO searchByOrganization(Integer idOrganization, SearchFilterDTO filter) {
        return getRepository().searchByOrganization(filter, idOrganization);
    }

    @Override
    public Variable processModelUpdate(Variable variable) throws BusinessException {
        return processProjectBeforeSaving(variable);
    }

    @Override
    public Variable processModelInsert(Variable variable) throws BusinessException {
        return processVariableBeforeSaving(variable);
    }

    @Override
    public void validateInsert(Variable variable) throws BusinessException {
        if (variable.getProject() == null) {
            throw new BusinessException(NOT_VALID_ID_PROJECT);
        }
    }

    @Override
    public void validateUpdate(Variable variable) throws BusinessException {
        if (variable.getProject() == null) {
            throw new BusinessException(NOT_VALID_ID_PROJECT);
        }
    }

    private Variable processVariableBeforeSaving(Variable variable) throws BusinessException {
        User user = userService.findByLogin(getCurrentUser()).get(0);
        if (user == null) {
            throw new BusinessException(NOT_VALID_USER_LOGIN);
        }
        variable.setDateUpdate(new Date());
        variable.setUser(user);
        return variable;
    }

}
