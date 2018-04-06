package br.com.leucotron.livre.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Util class for cryptography.
 * 
 * @author Virtus
 */
public final class CryptoUtil {
	
	static final String ALGORITHM = "AES";

	/**
	 * Encrypt the value.
	 * 
	 * @param value
	 * 		Value.
	 * @return Encrypted value.
	 */
	public static String encrypt(String value) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec("L3uC0tr0n###L1vr".getBytes(), ALGORITHM);
			
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			
			return new String(cipher.doFinal(value.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
