package br.com.leucotron.livre.service;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.model.Project;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService extends CrudService<Project, Integer> {

    private static final String NOT_VALID_ID_ORGANIZATION = "NotNull.projectDTO.idOrganization";
    private static final String NOT_VALID_USER_LOGIN = "NotValid.userDTO.login";
    private static final String NOT_VALID_PROJECT_NAME = "NotValid.project.name";

    /**
     * Role repository.
     */
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserService userService;

    /**
     * (non-Javadoc)
     *
     * @see CrudService#getRepository()
     */
    @Override
    protected ProjectRepository getRepository() {
        return repository;
    }

    public ResponseListDTO searchByOrganization(Integer idOrganization, SearchFilterDTO filter) {
        return getRepository().searchByOrganization(filter, idOrganization);
    }

    @Override
    public Project processModelUpdate(Project project) throws BusinessException {
        return processProjectBeforeSaving(project);
    }

    @Override
    public Project processModelInsert(Project project) throws BusinessException {
        return processProjectBeforeSaving(project);
    }

    @Override
    public void validateInsert(Project project) throws BusinessException {
        if (project.getOrganization() == null) {
            throw new BusinessException(NOT_VALID_ID_ORGANIZATION);
        } else if (repository.findByOrganizationIdOrganizationAndName(project.getOrganization().getId(), project.getName()).size() > 0) {
            throw new BusinessException(NOT_VALID_PROJECT_NAME);
        }
    }

    @Override
    public void validateUpdate(Project project) throws BusinessException {
        if (project.getOrganization() == null) {
            throw new BusinessException(NOT_VALID_ID_ORGANIZATION);
        }
        List<Project> projects = repository.findByOrganizationIdOrganizationAndName(project.getOrganization().getId(), project.getName());
        if (projects.size() > 0) {
            if (projects.get(0).getId() != project.getId()) {
                throw new BusinessException(NOT_VALID_PROJECT_NAME);
            }
        }
    }

    private Project processProjectBeforeSaving(Project project) throws BusinessException {
        User user;
        try {
            user = userService.findByLogin(getCurrentUser()).get(0);
        } catch (NullPointerException ex) {
            throw new BusinessException(NOT_VALID_USER_LOGIN);
        }
        project.setDateUpdate(new Date());
        project.setUser(user);
        return project;
    }

}
