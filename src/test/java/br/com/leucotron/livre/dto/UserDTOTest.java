package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.UserDTO;
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
public class UserDTOTest {

    /**
     * Test retrieves the value correctly and already converted.
     */
    @Test
    public void getUserDtoValues() {
        UserDTO user = new UserDTO();
        user.setName("UserLeucotron");
        user.setRefreshToken("user12345");
        user.setTags("MANAGER");
        user.setToken("sasda5s6a5s96a");
        user.setLogin("newLogin@gmail.com");
        user.setRole("usuario");
        user.setMaster(false);

        assertEquals("UserLeucotron", user.getName());
        assertEquals("user12345", user.getRefreshToken());
        assertEquals("MANAGER", user.getTags());
        assertEquals("sasda5s6a5s96a", user.getToken());
        assertEquals("newLogin@gmail.com", user.getLogin());
        assertEquals("usuario", user.getRole());
        assertEquals(false, user.isMaster());
    }

}
