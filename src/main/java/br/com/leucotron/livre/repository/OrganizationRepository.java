package br.com.leucotron.livre.repository;

import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.model.Organization;

import java.io.Serializable;

/**
 * Repository for entity: Organization.
 *
 * @author Virtus
 */
public interface OrganizationRepository extends CrudBaseRepository<Organization, Integer> {

    Organization findByName(Serializable serializable);
}
