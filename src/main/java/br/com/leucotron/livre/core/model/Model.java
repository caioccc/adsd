package br.com.leucotron.livre.core.model;

import java.io.Serializable;

/**
 * Model representing a table in database.
 * 
 * @author Virtus
 *
 * @param <T> ID's type.
 */
public abstract class Model<T extends Serializable> {

	/**
	 * Gets the ID of the model.
	 * 
	 * @return ID of the model.
	 */
	public abstract T getId(); 
	
	/**
	 * Sets the ID of the model.
	 * 
	 * @param id
	 * 		ID of the model.
	 */
	public abstract void setId(T id);
}
