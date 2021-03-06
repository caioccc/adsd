package br.com.leucotron.livre.domain;

import br.com.leucotron.livre.model.User;
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
public class UserTest {

    /**
     * Test retrieves the value correctly and already converted.
     */
    @Test
    public void getValue() {
        User user = new User("UserLeucotron", "commonUser", "user12345", "MANAGER", "usuario", false);
        assertEquals("UserLeucotron", user.getName());
        assertEquals("commonUser", user.getLogin());
        assertEquals("user12345", user.getPassword());
        assertEquals("MANAGER", user.getTags());
        assertEquals("usuario", user.getRole());
        assertEquals(false, user.isMaster());
    }

    @Test
    public void setValue() {
        User user = new User("UserLeucotron", "commonUser", "user12345", "MANAGER", "usuario", false);
        user.setPassword("Admin123!");
        user.setLogin("adminUser");
        user.setTags("ADMIN");
        user.setName("Admin");
        user.setMaster(true);
        assertEquals("Admin", user.getName());
        assertEquals("adminUser", user.getLogin());
        assertEquals("Admin123!", user.getPassword());
        assertEquals("ADMIN", user.getTags());
        assertEquals("usuario", user.getRole());
        assertEquals(true, user.isMaster());
    }



}
