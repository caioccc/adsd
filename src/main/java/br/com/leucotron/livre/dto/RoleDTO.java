package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.ModelDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO for Model: Role.
 *
 * @author Virtus
 */
@Getter
@Setter
public class RoleDTO extends ModelDTO {

    /**
     * ID Role.
     */
    private Integer id;

    /**
     * Role.
     */
    @NotNull
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
