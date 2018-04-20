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
    private String login;

    /**
     * Tags.
     */
    private String tags;

    /**
     * Password
     */
    private String newPassword;

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
     * Set the Login.
     *
     * @return login Login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the Login.
     *
     * @return login Login.
     */
    public String getLogin() {
        return login;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
