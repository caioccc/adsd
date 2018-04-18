package br.com.leucotron.livre.util;

import br.com.leucotron.livre.model.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 * Teste para classe de domínio de parâmetro.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class JsonUtilTest {
    public static final String LEUCOTRON_ORG = "LeucotronOrg";
    private String JSON_DATA =
            "{"
                    + "      \"id\": \"1\","
                    + "      \"name\": \"LeucotronOrg\","
                    + "      \"status\" : \"true\","
                    + "      \"accesskey\" : \"ash54544asd\","
                    + "      \"tags\" : \"ab,abc,abcd\""
                    + "    }";

    @Test
    public void changeStringToJson() {
        Organization organization = JSonUtil.fromJSon(JSON_DATA, Organization.class);
        assertTrue(organization.getName().equals(LEUCOTRON_ORG));
    }
}
