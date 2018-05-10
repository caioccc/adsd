package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.ModelDTO;

/**
 * DTO for Model: User.
 *
 * @author Virtus
 */
public class AssociatedUserDTO extends UserDTO {


    private boolean associated;


    public AssociatedUserDTO(String name, boolean associated) {
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
