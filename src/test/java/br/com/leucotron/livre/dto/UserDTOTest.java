package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.UserDTO;
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
public class UserDTOTest {

    /**
     * Testa se recupera o valor corretamente e já convertido.
     */
    @Test
    public void getUserDtoValues() {
        UserDTO user = new UserDTO();
        user.setName("UserLeucotron");
        user.setRefreshToken("user12345");
        user.setSector("MANAGER");
        user.setToken("sasda5s6a5s96a");

        assertEquals("UserLeucotron", user.getName());
        assertEquals("user12345", user.getRefreshToken());
        assertEquals("MANAGER", user.getSector());
        assertEquals("sasda5s6a5s96a", user.getToken());
    }

}
