package br.com.leucotron.livre.util;

import br.com.leucotron.livre.util.CryptoUtil;
import br.com.leucotron.livre.util.MessageUtil;
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
public class MessageUtilTest {

    /**
     * Testa se recupera o valor corretamente e já convertido.
     */
    @Test
    public void getGroupMessage() {
        String message = MessageUtil.findMessage("group.name");
        assertTrue(message.equals("Nome do grupo já existe."));
    }
}
