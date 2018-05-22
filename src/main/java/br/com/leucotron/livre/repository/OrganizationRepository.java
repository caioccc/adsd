package br.com.leucotron.livre.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.model.Organization;

/**
 * Repository for entity: Organization.
 *
 * @author Virtus
 */
public interface OrganizationRepository extends CrudBaseRepository<Organization, Integer> {

	public Page<Organization> findByStatusTrueAndUsersLogin(String login, Pageable pageable);

	default ResponseListDTO getOrganizationActiveByUserCurrent(String login, SearchFilterDTO searchFilter) {
		Page<Organization> result;

		if (!searchFilter.getColumn().isEmpty()) {
			result = this.findByStatusTrueAndUsersLogin(login,
					new PageRequest(searchFilter.getCurrentPage() - 1, searchFilter.getPageSize(),
							Sort.Direction.fromString(searchFilter.getSort()), searchFilter.getColumn()));
		} else {
			result = this.findByStatusTrueAndUsersLogin(login,
					new PageRequest(searchFilter.getCurrentPage() - 1, searchFilter.getPageSize()));
		}

		return new ResponseListDTO(result.getTotalPages(), result.getContent());
	}

}
