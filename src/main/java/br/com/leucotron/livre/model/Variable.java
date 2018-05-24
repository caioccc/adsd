package br.com.leucotron.livre.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.leucotron.livre.core.model.Model;

/**
 * Model for table: Variable.
 *
 * @author Virtus
 */
@Entity(name = "variable")
public class Variable extends Model<Integer> {

    /**
     * User ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idvariable")
    private Integer idVariable;

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

    /**
     * type.
     */
    @Column(name = "type")
    private String type;


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
     * Project.
     */
    @OneToOne()
    @JoinColumn(name = "idproject")
    private Project project;

    /**
     * Constructor.
     */
    public Variable() {
    }

    public Variable(String name, String description, String tags, String type, Date dateUpdate, User user,
                    Project project) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.type = type;
        this.dateUpdate = dateUpdate;
        this.user = user;
        this.project = project;
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

    public Integer getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public Integer getId() {
        return this.idVariable;
    }

    @Override
    public void setId(Integer id) {
        this.idVariable = id;

    }


}
