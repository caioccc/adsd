package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.ModelDTO;

/**
 * DTO for Model: User.
 *
 * @author Virtus
 */
public class UserDTO extends ModelDTO {

    /**
     * ID User.
     */
    private Integer id;

    /**
     * Name.
     */
    private String name;


    /**
     * Status.
     */
    private boolean login;

    /**
     * Tags.
     */
    private String tags;


    /**
     * Gets the ID.
     *
     * @return ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID.
     *
     * @param id ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the Tags.
     *
     * @return tags.
     */
    public String getTags() {
        return tags;
    }

    /**
     * Set the Status.
     *
     * @return tags Tags.
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * Gets the Login.
     *
     * @return login Login.
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * Set the Login.
     *
     * @return login Login.
     */
    public void setLogin(boolean login) {
        this.login = login;
    }
}
