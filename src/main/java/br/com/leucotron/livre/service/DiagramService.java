package br.com.leucotron.livre.service;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.dto.DiagramDTO;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.model.Diagram;
import br.com.leucotron.livre.repository.DiagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiagramService extends CrudService<Diagram, Integer> {

    private static final String NOT_VALID_ID_PROJECT = "NotNull.diagramDTO.idProject";
    private static final String NOT_VALID_USER_LOGIN = "NotValid.userDTO.login";
    private static final String NOT_VALID_DIAGRAM_NAME = "NotValid.diagram.name";
    private static final String USER_NOT_PERMISSION_CREATE_DIAGRAM = "UserNotPermission.create.diagram";


    @Autowired
    private DiagramRepository repository;

    @Autowired
    private UserService userService;

    /**
     * (non-Javadoc)
     *
     * @see CrudService#getRepository()
     */
    @Override
    protected DiagramRepository getRepository() {
        return repository;
    }

    public ResponseListDTO searchByDiagram(Integer idProject, SearchFilterDTO filter) {
        return getRepository().searchByDiagram(filter, idProject);
    }

    @Override
    public Diagram processModelUpdate(Diagram diagram) throws BusinessException {
        return processProjectBeforeSaving(diagram);
    }

    @Override
    public Diagram processModelInsert(Diagram diagram) throws BusinessException {
        return processProjectBeforeSaving(diagram);
    }

    @Override
    public void validateInsert(Diagram diagram) throws BusinessException {
        if (diagram.getProject() == null) {
            throw new BusinessException(NOT_VALID_ID_PROJECT);
        } else if (repository.findByProjectIdProjectAndName(diagram.getProject().getId(), diagram.getName()).size() > 0) {
            throw new BusinessException(NOT_VALID_DIAGRAM_NAME);
        }
    }

    public void validateIdOrganizationAssocietedUser(DiagramDTO diagramDTO) throws BusinessException {
        if (!userService.getRepository().existsByLoginAndOrganizationsIdOrganization(getCurrentUser(), diagramDTO.getIdOrganization())) {
            throw new BusinessException(USER_NOT_PERMISSION_CREATE_DIAGRAM);
        }
    }


    @Override
    public void validateUpdate(Diagram diagram) throws BusinessException {
        if (diagram.getProject() == null) {
            throw new BusinessException(NOT_VALID_ID_PROJECT);
        } else if (repository.findByProjectIdProjectAndName(diagram.getProject().getId(), diagram.getName()).size() > 1) {
            throw new BusinessException(NOT_VALID_DIAGRAM_NAME);
        }

        List<Diagram> diagrams = repository.findByProjectIdProjectAndName(diagram.getProject().getId(), diagram.getName());
        if (diagrams.size() > 0) {
            if (diagrams.get(0).getId() != diagram.getId()) {
                throw new BusinessException(NOT_VALID_DIAGRAM_NAME);
            }
        }
    }

    private Diagram processProjectBeforeSaving(Diagram diagram) throws BusinessException {
        User user;
        try {
            user = userService.findByLogin(getCurrentUser()).get(0);
        } catch (NullPointerException ex) {
            throw new BusinessException(NOT_VALID_USER_LOGIN);
        }
        diagram.setDateUpdate(new Date());
        diagram.setUser(user);
        return diagram;
    }

}
