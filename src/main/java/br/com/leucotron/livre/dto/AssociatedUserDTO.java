package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.ModelDTO;

/**
 * DTO for Model: User.
 *
 * @author Virtus
 */
public class AssociatedUserDTO extends UserDTO {


    private boolean associated;


    public AssociatedUserDTO(Integer id, String name, boolean associated) {
        super.setId(id);
        this.associated = associated;
        super.setName(name);
    }

    public boolean isAssociated() {
        return associated;
    }

    public void setAssociated(boolean associated) {
        this.associated = associated;
    }
}
