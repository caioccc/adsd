package br.com.leucotron.livre.domain;

import br.com.leucotron.livre.core.security.AccountCredentials;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Test for parameter domain class.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class AccountCredentialsTest {

    /**
     * Test retrieves the value correctly and already converted.
     */
    @Test
    public void getValue() {
        AccountCredentials ac = new AccountCredentials();
        ac.setPassword("abcde");
        ac.setUsername("leucotron@leucotron.com");
        assertEquals("abcde", ac.getPassword());
        assertEquals("leucotron@leucotron.com", ac.getUsername());
    }

}
