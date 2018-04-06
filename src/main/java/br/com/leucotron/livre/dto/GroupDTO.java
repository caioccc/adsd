package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.ModelDTO;

/**
 * DTO for Model: Group.
 * 
 * @author Virtus
 *
 */
public class GroupDTO extends ModelDTO {

	/**
	 * ID Group.
	 */
	private Integer id;
	
	/**
	 * Name.
	 */
	private String name;

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
}
