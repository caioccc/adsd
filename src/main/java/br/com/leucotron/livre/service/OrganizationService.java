package br.com.leucotron.livre.service;

import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CRUD service of the model: Organization.
 *
 * @author Virtus
 */
@Service
public class OrganizationService extends CrudService<Organization, Integer> {

    /**
     * Organization repository.
     */
    @Autowired
    private OrganizationRepository repository;

    /**
     * (non-Javadoc)
     *
     * @see CrudService#getRepository()
     */
    @Override
    protected OrganizationRepository getRepository() {
        return repository;
    }
}