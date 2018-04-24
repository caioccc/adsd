package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Test for parameter domain class.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class ResponseListDTOTest {

    /**
     * Test retrieves the value correctly and already converted.
     */
    @Test
    public void getResponseListDtoValues() {
        ResponseListDTO response1 = new ResponseListDTO();
        ResponseListDTO response2 = new ResponseListDTO(10, new ArrayList<>());
        response1.setItems(new ArrayList<>());

        assertEquals(response1.getItems().size(), 0);
        assertEquals(response2.getTotalPages().intValue(), 10);
        assertEquals(response2.getItems().size(), 0);
    }

}
