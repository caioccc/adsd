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
			return findMessage(code, Locale.getDefault());
		} catch (Exception e) {
		}
		return code;
	}

	/**
	 *
	 *
	 * @param code
	 * 		Message code.
	 * @param locale
	 * 		Current locale
	 * @return
	 */
	public static String findMessage(String code, Locale locale) {
		try{
			return ResourceBundle.getBundle("messages", locale).getString(code);
		} catch (Exception e) {
		}
		return code;
	}
}
