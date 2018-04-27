package br.com.leucotron.livre.util;

import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for Crypto password.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class BusinessExceptionTest {

    /**
     * Test retrieves the value correctly and already converted.
     */
    @Test
    public void constructorTest() {
        BusinessException be = new BusinessException("Error");
        assertNotEquals(null, be);
    }

}
