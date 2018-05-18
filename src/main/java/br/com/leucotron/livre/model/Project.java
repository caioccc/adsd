package br.com.leucotron.livre.model;

import br.com.leucotron.livre.core.model.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Model for table: User.
 *
 * @author Virtus
 */
@Entity(name = "project")
public class Project extends Model<Integer> {

    /**
     * User ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idproject")
    private Integer idProject;

    /**
     * Name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Description.
     */
    @Column(name = "description")
    private String description;

    /**
     * Tags.
     */
    @Column(name = "tags")
    private String tags;

    @Column(name = "status")
    private boolean status;

    /**
     * Date for Created and Update.
     */
    @Column(name = "dateupdate")
    private Date dateUpdate;

    /**
     * User.
     */
    @OneToOne()
    @JoinColumn(name = "iduser")
    private User user;

    /**
     * Constructor.
     */
    public Project() {
    }

    public Project(String name, String description, String tags, boolean status, Date dateUpdate, User user) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.status = status;
        this.dateUpdate = dateUpdate;
        this.user = user;
    }

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * (non-Javadoc)
     *
     * @see Model#getId()
     */
    @Override
    public Integer getId() {
        return this.getIdProject();
    }

    /**
     * (non-Javadoc)
     *
     * @see Model#setId(java.io.Serializable)
     */
    @Override
    public void setId(Integer id) {
        this.setIdProject(id);
    }

}
