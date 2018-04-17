package br.com.leucotron.livre.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
}
