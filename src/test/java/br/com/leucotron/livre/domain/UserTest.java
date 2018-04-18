package br.com.leucotron.livre.domain;

import br.com.leucotron.livre.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Teste para classe de domínio de parâmetro.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class UserTest {

    /**
     * Testa se recupera o valor corretamente e já convertido.
     */
    @Test
    public void getValue() {
        User user = new User(50, "UserLeucotron", "commonUser", "user12345", "MANAGER");

        assertEquals("UserLeucotron", user.getName());
        assertEquals("commonUser", user.getLogin());
        assertEquals("user12345", user.getPassword());
        assertEquals("MANAGER", user.getSector());
        assertEquals("50", user.getIdUser().toString());
        assertEquals("50", user.getId().toString());
    }

    @Test
    public void setValue() {
        User user = new User(50, "UserLeucotron", "commonUser", "user12345", "MANAGER");
        user.setIdUser(3);
        user.setPassword("Admin123!");
        user.setLogin("adminUser");
        user.setSector("ADMIN");
        user.setName("Admin");
        user.setIdUser(35);
        assertEquals("Admin", user.getName());
        assertEquals("adminUser", user.getLogin());
        assertEquals("Admin123!", user.getPassword());
        assertEquals("ADMIN", user.getSector());
        assertEquals("35", user.getIdUser().toString());
        user.setId(30);
        assertEquals("30", user.getId().toString());
    }
}
