package br.com.leucotron.livre.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO representing a flow group.
 * 
 * @author Virtus
 */
public class FlowGroupDTO {

	/**
	 * ID.
	 */
	private Integer id;
	
	/**
	 * Name.
	 */
	private String name;
	
	/**
	 * Operations.
	 */
	private List<FlowOperationDTO> operations;

	/**
	 * Constructor.
	 */
	public FlowGroupDTO() {
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * 		ID.
	 * @param name
	 * 		Name.
	 */
	public FlowGroupDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.operations = new ArrayList<>();
	}
	
	/**
	 * Gets the ID.
	 * 
	 * @return ID.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the ID.
	 * 
	 * @param id
	 * 		ID.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 * 
	 * @return Name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 * 		Name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the list of operations.
	 * 
	 * @return List of operations.
	 */
	public List<FlowOperationDTO> getOperations() {
		return operations;
	}

	/**
	 * Sets the list of operations.
	 * 
	 * @param operations
	 * 		List of operations.
	 */
	public void setOperations(List<FlowOperationDTO> operations) {
		this.operations = operations;
	}
}
