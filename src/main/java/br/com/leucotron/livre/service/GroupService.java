package br.com.leucotron.livre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.model.Group;
import br.com.leucotron.livre.repository.GroupRepository;

/**
 * CRUD service of the model: Group.
 * 
 * @author Virtus
 */
@Service
public class GroupService extends CrudService<Group, Integer> {

	/**
	 * Group repository.
	 */
	@Autowired
	private GroupRepository repository; 

	/**
	 * (non-Javadoc)
	 * @see br.com.leucotron.livre.core.service.CrudService#getRepository()
	 */
	@Override
	protected GroupRepository getRepository() {
		return repository;
	}
}
