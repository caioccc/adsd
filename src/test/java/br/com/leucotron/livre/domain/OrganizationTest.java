package br.com.leucotron.livre.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import br.com.leucotron.livre.model.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Teste para classe de domínio de parâmetro.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class OrganizationTest {

    /**
     * Testa se recupera o valor corretamente e já convertido.
     */
    @Test
    public void getValue() {

        Organization orgFalse = new Organization("LeucontronFalse", false, "a,ab,abc", "a45Sya5A552");
        Organization orgTrue = new Organization("LeucotronTrue", true, "bc,abc,af", "a45Sya5A111");

        assertEquals("LeucontronFalse", orgFalse.getName());
        assertFalse((Boolean) orgFalse.isStatus());

        assertEquals("LeucotronTrue", orgTrue.getName());
        assertTrue((Boolean) orgTrue.isStatus());

        assertEquals("a,ab,abc", orgFalse.getTags());
        assertEquals("bc,abc,af", orgTrue.getTags());

        assertEquals("a45Sya5A552", orgFalse.getAccesskey());
        assertEquals("a45Sya5A111", orgTrue.getAccesskey());
    }
}
