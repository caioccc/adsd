package br.com.leucotron.livre.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for Crypto password.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class MessageUtilTest {

    public static final String NOT_NULL_ORGANIZATION_DTO_NAME = "NotNull.organizationDTO.name";
    public static final int ZERO = 0;

    /**
     * Test retrieves the value correctly and already converted.
     */
    @Test
    public void findMessageTest() {
        assertTrue(MessageUtil.findMessage(NOT_NULL_ORGANIZATION_DTO_NAME).length() > ZERO);
    }

    @Test(expected = Exception.class)
    public void findMessageNullTest() {
        assertTrue(MessageUtil.findMessage(null).length() > ZERO);
    }

}
