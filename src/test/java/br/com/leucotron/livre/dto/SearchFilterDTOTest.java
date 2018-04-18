package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Teste para classe de domínio de parâmetro.
 *
 * @author Virtus
 */
@RunWith(JUnit4.class)
public class SearchFilterDTOTest {

    /**
     * Testa se recupera o valor corretamente e já convertido.
     */
    @Test
    public void setSearchFilterDtoValues() {
        SearchFilterDTO searchFilter = new SearchFilterDTO();
        searchFilter.setPageSize(5);
        searchFilter.setCurrentPage(1);
        searchFilter.setFilters(new HashMap<>());
        assertEquals(searchFilter.getCurrentPage().intValue(), 1);
        assertEquals(searchFilter.getPageSize().intValue(), 5);
        assertEquals(searchFilter.getFilters().size(), 0);
    }

}
