package br.com.leucotron.livre.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.leucotron.livre.dto.OrganizationDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leucotron.livre.core.controller.BaseController;

/**
 * Controller for flow operations.
 * 
 * @author Virtus
 */
@RestController
@RequestMapping("flow")
public class FlowController extends BaseController {
	
	/**
	 * Get all groups with its operations for flow use.
	 * 
	 * @return All groups with its operations.
	 */
	@GetMapping("elements")
	public List<OrganizationDTO> getFlowGroups() {
		return new ArrayList<>();
	}
}
