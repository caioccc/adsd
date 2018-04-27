package br.com.leucotron.livre.util;

import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.exception.RestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.omg.CORBA.Object;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test for Crypto password.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class RestExceptionTest {


    /**
     * Test retrieves the value correctly and already converted.
     */
    @Test
    public void constructorTest() {
        RestException re = new RestException();
        Object[] args = new Object[5];
        re.setArgs(args);
        assertNotEquals(null, re);
        assertTrue(re.getArgs().length != 0);
    }

}
