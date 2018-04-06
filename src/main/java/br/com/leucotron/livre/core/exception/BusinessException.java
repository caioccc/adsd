package br.com.leucotron.livre.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.leucotron.livre.util.MessageUtil;

/**
 * Exception for business errors or validations. <br>
 * 
 * Should contain a user friendly message. <br>
 * Ex: "There is an item with the same name".
 * 
 * @author Virtus
 */
public class BusinessException extends Exception {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger.
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Constructor.
	 * 
	 * @param code
	 * 		Message Code.
	 */
	public BusinessException(String code) {
		this(code, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param code
	 * 		Message code.
	 * @param e
	 * 		Exception.
	 */
	public BusinessException(String code, Exception e) {
		super(MessageUtil.findMessage(code), e);
		
		logger.error(this.getMessage());
	}
}
