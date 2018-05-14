package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.SearchFilterDTO;

/**
 * DTO for filter the search with associated param .
 *
 * @author Virtus
 */
public class AssociatedSearchFilterDTO extends SearchFilterDTO {

    /**
     * Current page.
     */
    private Boolean associated;

    public Boolean getAssociated() {
        return associated;
    }

    public void setAssociated(Boolean associated) {
        this.associated = associated;
    }
}
