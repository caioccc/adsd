package br.com.leucotron.livre.repository;

import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.model.Organization;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

/**
 * Repository for entity: Organization.
 *
 * @author Virtus
 */
public interface OrganizationRepository extends CrudBaseRepository<Organization, Integer> {

}
