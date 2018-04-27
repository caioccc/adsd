package br.com.leucotron.livre.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for Crypto password.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class RestMessageTest {

    public static final String NOT_NULL_ORGANIZATION_DTO_NAME = "NotNull.organizationDTO.name";

    /**
     * Test retrieves the value correctly and already converted.
     */
    @Test
    public void constructorTest() {
        RestMessage rm = new RestMessage(new ArrayList<>());
        RestMessage rmTwo = new RestMessage(NOT_NULL_ORGANIZATION_DTO_NAME);
        assertTrue(rm != null);
        assertTrue(rmTwo != null);
    }

}
