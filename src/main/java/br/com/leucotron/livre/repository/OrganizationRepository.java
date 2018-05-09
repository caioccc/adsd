package br.com.leucotron.livre.repository;

import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.model.Organization;

import java.io.Serializable;
import java.util.List;

/**
 * Repository for entity: Organization.
 *
 * @author Virtus
 */
public interface OrganizationRepository extends CrudBaseRepository<Organization, Integer> {

    List<Organization> findByName(Serializable serializable);

}
