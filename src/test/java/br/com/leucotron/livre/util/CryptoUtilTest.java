package br.com.leucotron.livre.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 * Teste para classe de domínio de parâmetro.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class CryptoUtilTest {

    /**
     * Testa se recupera o valor corretamente e já convertido.
     */
    @Test
    public void CryptoPasswordValue() {
        String passCrypto = CryptoUtil.encrypt("password");
        assertTrue(passCrypto != null);
    }
}
