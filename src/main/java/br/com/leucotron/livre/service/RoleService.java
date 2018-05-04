package br.com.leucotron.livre.service;

import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.model.Role;
import br.com.leucotron.livre.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CRUD service of the model: Role.
 *
 * @author Virtus
 */
@Service
public class RoleService extends CrudService<Role, Integer> {

    /**
     * Role repository.
     */
    @Autowired
    private RoleRepository repository;

    /**
     * (non-Javadoc)
     *
     * @see CrudService#getRepository()
     */
    @Override
    protected RoleRepository getRepository() {
        return repository;
    }

}