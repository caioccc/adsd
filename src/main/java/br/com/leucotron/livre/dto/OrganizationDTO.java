package br.com.leucotron.livre.dto;

import br.com.leucotron.livre.core.dto.ModelDTO;

/**
 * DTO for Model: Organization.
 *
 * @author Virtus
 */
public class OrganizationDTO extends ModelDTO {

    /**
     * ID Organization.
     */
    private Integer id;

    /**
     * Name.
     */
    private String name;


    /**
     * Status.
     */
    private boolean status;

    /**
     * Tags.
     */
    private String tags;

    /**
     * Access Key.
     */
    private String accesskey;

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
     * Gets the Status.
     *
     * @return Status.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Set the Status.
     *
     * @return status Status.
     */
    public void setStatus(boolean status) {
        this.status = status;
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
     * Gets the Access Key.
     *
     * @return Status.
     */
    public String getAccesskey() {
        return accesskey;
    }

    /**
     * Set the Access Key.
     *
     * @return accesskey New Access Key.
     */
    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }
}
