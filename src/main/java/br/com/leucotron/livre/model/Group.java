package br.com.leucotron.livre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.leucotron.livre.core.model.Model;

/**
 * Model of the table: GRUPO.
 * 
 * @author Virtus
 */
@Entity(name = "GRUPO")
public class Group extends Model<Integer> {

	/**
	 * Group ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDGRUPO")
	private Integer id;
	
	/**
	 * Name of the group.
	 */
	@Column(name = "NOME")
	private String name;

	/**
	 * (non-Javadoc)
	 * @see br.com.leucotron.livre.core.model.Model#getId()
	 */
	@Override
	public Integer getId() {
		return id;
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.leucotron.livre.core.model.Model#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Gets the name of the group.
	 * 
	 * @return Name of the group.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the group.
	 * 
	 * @param name
	 * 		Name of the group.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
