package br.com.leucotron.livre.util;

import java.lang.reflect.Field;

/**
 * Utility class for Class operations. <br>
 * 
 * Provides helpers for reflection such as instantiate, setters, getters.
 * 
 * @author Virtus
 */
public final class ClassUtil {

	/**
	 * Private constructor.
	 */
	private ClassUtil() { }
	
	/**
	 * Creates an instance of the type.
	 * 
	 * @param type
	 * 		Type.
	 * @return New instance.
	 */
	public static <T> T newInstance(Class<T> type) {
		try {
			return type.newInstance();
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * Sets the value into object's field.
	 * 
	 * @param object
	 * 		Object.
	 * @param fieldName
	 * 		Field name.
	 * @param value
	 * 		Value.
	 */
	public static void setField(Object object, String fieldName, Object value) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
	        field.set(object, value);
		} catch (Exception e) {
		}
	}

	/**
	 * Gets the value from object's field.
	 * 
	 * @param object
	 * 		Object.
	 * @param fieldName
	 * 		Field name.
	 * @return Value.
	 */
	public static <T> Object getValue(T object, String fieldName) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
	        return field.get(object);
		} catch (Exception e) {
		}
		return null;
	}
}
