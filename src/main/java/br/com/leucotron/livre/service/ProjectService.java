package br.com.leucotron.livre.service;

import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.model.Project;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ProjectService extends CrudService<Project, Integer> {

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

    /**
     * Inserts the model.
     *
     * @param model
     * 		Model.
     * @throws BusinessException
     * 		If some rule is not acceptable.
     */
    @Transactional
    public Project insert(Project model) throws BusinessException {
        validateInsert(model);

        User user = userService.findByLogin(getCurrentUser()).get(0);
        if (user == null) {
            throw new BusinessException("NotValid.userDTO.login");
        }
        model.setDateUpdate(new Date());
        model.setUser(user);

        return getRepository().save(model);
    }

    /**
     * Updates the model.
     *
     * @param id
     * 		ID.
     * @param model
     * 		Model.
     * @throws BusinessException
     * 		If some rule is not acceptable.
     */
    @Transactional
    public void update(Integer id, Project model) throws BusinessException {
        validateUpdate(model);

        User user = userService.findByLogin(getCurrentUser()).get(0);
        if (user == null) {
           throw new BusinessException("NotValid.userDTO.login");
        }
        model.setDateUpdate(new Date());
        model.setUser(user);

        getRepository().save(model);
    }

}
