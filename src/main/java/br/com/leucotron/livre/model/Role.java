package br.com.leucotron.livre.model;

import br.com.leucotron.livre.core.model.Model;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Model for table: ROLE.
 *
 * @author Virtus
 */
@Entity(name = "role")
public class Role extends Model<Integer> {

    /**
     * Organization ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated product ID.")
    @Column(name = "id")
    private Integer idRole;

    /**
     * Role.
     */
    @ApiModelProperty(notes = "The Role name.")
    @Column(name = "role")
    @NotNull
    private String role;


    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * (non-Javadoc)
     *
     * @see Model#getId()
     */
    @Override
    public Integer getId() {
        return this.getIdRole();
    }

    /**
     * (non-Javadoc)
     *
     * @see Model#setId(java.io.Serializable)
     */
    @Override
    public void setId(Integer id) {
        this.setIdRole(id);
    }

}
