package br.com.leucotron.livre.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class for Messages using i18n.
 * 
 * @author Virtus
 *
 */
public final class MessageUtil {

	/**
	 * Private constructor.
	 */
	private MessageUtil() { }
	
	/**
	 * 
	 * 
	 * @param code
	 * 		Message code.
	 * @return 
	 */
	public static String findMessage(String code) {
		try{
			return ResourceBundle.getBundle("i18n/messages", Locale.getDefault()).getString(code);
		} catch (Exception e) {
		}
		return code;
	}
}
