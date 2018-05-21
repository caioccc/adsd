package br.com.leucotron.livre.service;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.model.Organization;
import br.com.leucotron.livre.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    /**
	 * Search the template by entering in the filter 
	 * if the organization is active and belongs to the logged in user.

	 * @return Instances founded.
	 */
	public ResponseListDTO getActiveOrganizationsByUser(SearchFilterDTO filter) {
		
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in username
       
		return getRepository().getOrganizationActiveByUserCurrent(login, filter);
	}


    @Override
    public void validateInsert(Organization model) throws BusinessException {
        if (model.getName().equals("") || model.getName().isEmpty()) {
            throw new BusinessException("NotNull.organizationDTO.name");
        }
        super.validateUpdate(model);
    }

    @Override
    public void validateUpdate(Organization model) throws BusinessException {
        if (model.getName().equals("") || model.getName().isEmpty()) {
            throw new BusinessException("NotNull.organizationDTO.name");
        }
        super.validateUpdate(model);
    }
}