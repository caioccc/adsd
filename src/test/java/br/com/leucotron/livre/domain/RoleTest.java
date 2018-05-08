package br.com.leucotron.livre.domain;

import br.com.leucotron.livre.model.Role;
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
public class RoleTest {

    /**
     * Test retrieves the value correctly and already converted.
     */
    @Test
    public void getValue() {
        Role role = new Role("newrole");
        assertEquals("newrole", role.getRole());

        role.setRole("UserLeucotron");
        assertEquals("UserLeucotron", role.getRole());
    }

}
