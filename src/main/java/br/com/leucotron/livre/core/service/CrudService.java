package br.com.leucotron.livre.core.service;

import java.io.Serializable;

import javax.transaction.Transactional;

import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.model.Model;
import br.com.leucotron.livre.core.repository.CrudBaseRepository;

/**
 * The 'CrudService' class provides the common API for all CRUD services.
 * 
 * All CRUD services MUST extend this class.
 * 
 * Provides validations, insertion, update and deletion.
 * 
 * @author Virtus
 *
 * @param <M> Model type.
 * @param <T> ID type.
 */
public abstract class CrudService<M extends Model<T>, T extends Serializable> extends SearchService<M, T> {

	/**
	 * Gets the model CRUD Repository.
	 * 
	 * @return CRUD Repository.
	 */
	protected abstract CrudBaseRepository<M, T> getRepository();
	
	/**
	 * Validates the insertion before perform.
	 * 
	 * @param model 
	 * 		Model.
	 * @throws BusinessException
	 * 		If some rule is not acceptable.
	 */
	public void validateInsert(M model) throws BusinessException {
		
	}

	/**
	 * Inserts the model.
	 * 
	 * @param model
	 * 		Model.
	 * @throws BusinessException
	 * 		If some rule is not acceptable.
	 */
	@Transactional
	public M insert(M model) throws BusinessException {
		validateInsert(model);

		return getRepository().save(model);
	}

	/**
	 * Validates the update before perform.
	 * 
	 * @param model 
	 * 		Model.
	 * @throws BusinessException
	 * 		If some rule is not acceptable.
	 */
	public void validateUpdate(M model) throws BusinessException {
		
	}
	
	/**
	 * Updates the model.
	 * 
	 * @param id
	 * 		ID.
	 * @param model
	 * 		Model.
	 * @throws BusinessException
	 * 		If some rule is not acceptable.
	 */
	@Transactional
	public void update(T id, M model) throws BusinessException {
		validateUpdate(model);
		
		getRepository().save(model);
	}
	
	/**
	 * Validates the deletion before perform.
	 * 
	 * @param id 
	 * 		ID.
	 * @throws BusinessException
	 * 		If some rule is not acceptable.
	 */
	public void validateDelete(Serializable id) throws BusinessException {
		
	}
	
	/**
	 * Deletes the model.
	 * 
	 * @param id
	 * 		ID.
	 * @throws BusinessException
	 * 		If some rule is not acceptable.
	 */
	@Transactional
	public void delete(T id) throws BusinessException {
		validateDelete(id);
		
		getRepository().delete(id);
	}
}
