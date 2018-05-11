package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.ModelDTO;

/**
 * DTO for Model: User.
 *
 * @author Virtus
 */
public class AssociatedUserDTO extends ModelDTO{

    private Integer id;

    private String name;

    private boolean associated;

    public AssociatedUserDTO() {
    }

    public AssociatedUserDTO(Integer id, String name, boolean associated) {
        this.id = id;
        this.name = name;
        this.associated = associated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAssociated() {
        return associated;
    }

    public void setAssociated(boolean associated) {
        this.associated = associated;
    }
}
