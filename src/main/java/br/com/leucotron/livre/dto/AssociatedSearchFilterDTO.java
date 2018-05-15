package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.SearchFilterDTO;

/**
 * DTO for filter the search with associated param .
 *
 * @author Virtus
 */
public class AssociatedSearchFilterDTO extends SearchFilterDTO {

    /**
     * associated param.
     */
    private Boolean associated;

    public Boolean getAssociated() {
        return associated;
    }

    public void setAssociated(Boolean associated) {
        this.associated = associated;
    }

    public Integer getIntegerValue() {
        if (this.associated != null) {
            if (!associated) {
                return 0;
            } else if (this.associated) {
                return 1;
            }
        }
        return null;
    }
}
