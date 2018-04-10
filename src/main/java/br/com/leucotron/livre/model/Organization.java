package br.com.leucotron.livre.model;

import br.com.leucotron.livre.core.model.Model;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Model for table: ORGANIZATION.
 *
 * @author Virtus
 */
@Entity(name = "organization")
public class Organization extends Model<Integer> {

    /**
     * Organization ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated product ID.")
    @Column(name = "idorganization")
    private Integer idOrganization;

    /**
     * Name.
     */
    @ApiModelProperty(notes = "An organization's fancy name.")
    @Column(name = "name")
    private String name;


    /**
     * Status.
     */
    @ApiModelProperty(notes = "Indicates the current status of the company in the system.")
    @Column(name = "status")
    private boolean status;

    /**
     * Tags.
     */
    @ApiModelProperty(notes = "Indicates the tags that define the organization in the system.")
    @Column(name = "tags")
    private String tags;

    /**
     * Access Key.
     */
    @ApiModelProperty(notes = "Indicates the organization's access key. The access key is a hash function of size 16 (0 to 15), which can be generated again after the registration.")
    @Column(name = "accesskey")
    private String accesskey;


    public Organization() {
    }

    public Organization(String name, boolean status, String tags, String accesskey) {
        this.name = name;
        this.status = status;
        this.tags = tags;
        this.accesskey = accesskey;
    }

    public Integer getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Integer idOrganization) {
        this.idOrganization = idOrganization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    /**
     * (non-Javadoc)
     *
     * @see Model#getId()
     */
    @Override
    public Integer getId() {
        return this.getIdOrganization();
    }

    /**
     * (non-Javadoc)
     *
     * @see Model#setId(java.io.Serializable)
     */
    @Override
    public void setId(Integer id) {
        this.setIdOrganization(id);
    }

}
