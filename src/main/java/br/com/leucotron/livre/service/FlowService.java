package br.com.leucotron.livre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leucotron.livre.core.service.BaseService;
import br.com.leucotron.livre.dto.FlowGroupDTO;
import br.com.leucotron.livre.repository.FlowRepository;

/**
 * Service for flow operations.
 * 
 * @author Virtus
 */
@Service
public class FlowService extends BaseService {

	/**
	 * Flow repository.
	 */
	@Autowired
	private FlowRepository repository;
	
	/**
	 * Gets all groups with its operations to be used in flow design.
	 * 
	 * @return All groups with its operations.
	 */
	public List<FlowGroupDTO> getFlowGroups() {
		return repository.getFlowGroups();
	}
}
