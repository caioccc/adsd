package br.com.leucotron.livre.core.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.leucotron.livre.util.MapperUtil;

/**
 * The 'BaseController' class provides the common API for all controllers.
 * 
 * All controllers MUST extend this class.
 * 
 * @author Virtus
 */
public abstract class BaseController {

	/**
	 * Logger.
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Returns a success response.
	 * 
	 * @return Success response.
	 */
	protected ResponseEntity<?> success() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Maps the source object the a new destination type instance.
	 * 
	 * @param source
	 * 		Source object.
	 * @param destClass
	 * 		Destination class.
	 * @return Instance of destination type.
	 */
    protected <S, D> D mapTo(S source, Class<D> destClass) {
        return MapperUtil.mapTo(source, destClass);
    }

    /**
     * Converts the source list to a destination list mapping the source items
	 * to destination type items.
     * 
     * @param items
     * 		Source items.
     * @param destClass
     * 		Destination class.
     * @return List of instances of destination type.
     */
    protected <D, S> List<D> toList(List<S> items, Class<D> destClass) {
        return MapperUtil.toList(items, destClass);
    }
}
