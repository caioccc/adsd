package br.com.leucotron.livre.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 * Test for Crypto password.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class CryptoUtilTest {

    /**
     * Test retrieves the value correctly and already converted.
     */
    @Test
    public void CryptoPasswordValue() {
        String passCrypto = CryptoUtil.encrypt("password");
        assertTrue(passCrypto != null);
    }
}
