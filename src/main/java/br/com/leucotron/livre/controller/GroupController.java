package br.com.leucotron.livre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leucotron.livre.core.controller.CrudBaseController;
import br.com.leucotron.livre.dto.GroupDTO;
import br.com.leucotron.livre.model.Group;
import br.com.leucotron.livre.service.GroupService;

/**
 * CRUD Controller for the model: Group.
 * 
 * @author Virtus
 */
@RestController
@RequestMapping("groups")
public class GroupController extends CrudBaseController<Group, Integer, GroupDTO> {

	/**
	 * Group service.
	 */
	@Autowired
	private GroupService service;
	
	/**
	 * (non-Javadoc)
	 * @see br.com.leucotron.livre.core.controller.CrudBaseController#getService()
	 */
	@Override
	protected GroupService getService() {
		return service;
	}
}
