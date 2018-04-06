package br.com.leucotron.livre.dto;

/**
 * DTO representing a flow operation.
 * 
 * @author Virtus
 */
public class FlowOperationDTO {

	/**
	 * ID.
	 */
	private Integer id;
	
	/**
	 * Name.
	 */
	private String name;
	
	/**
	 * Icon as Base64.
	 */
	private String icon;
	
	/**
	 * Constructor.
	 */
	public FlowOperationDTO() { }
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * 		ID.
	 * @param name
	 * 		Name.
	 * @param icon
	 * 		Icon.
	 */
	public FlowOperationDTO(Integer id, String name, String icon) {
		super();
		
		this.id = id;
		this.name = name;
		this.icon = icon;
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
	 * Gets the icon.
	 * 
	 * @return Icon.
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the icon.
	 * 
	 * @param icon
	 * 		Icon.
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
