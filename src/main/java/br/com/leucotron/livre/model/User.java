package br.com.leucotron.livre.model;

import javax.persistence.*;

import br.com.leucotron.livre.core.model.Model;

import java.util.Set;

/**
 * Model for table: User.
 *
 * @author Virtus
 */
@Entity(name = "user")
public class User extends Model<Integer> {

    /**
     * User ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iduser")
    private Integer idUser;

    /**
     * Name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Login.
     */
    @Column(name = "login")
    private String login;

    /**
     * Password.
     */
    @Column(name = "password")
    private String password;

    /**
     * Tags.
     */
    @Column(name = "tags")
    private String tags;


    /**
     * Role.
     */
    @Column(name = "role")
    private String role;

    /**
     * flag.
     */
    @Column(name = "master")
    private boolean master;


    @ManyToMany(mappedBy = "users")
    private Set<Organization> organizations;

    /**
     * Constructor.
     */
    public User() {
    }


    public User(String name, String login, String password, String tags, String role, boolean master) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.tags = tags;
        this.role = role;
        this.master = master;
    }

    /**
     * Gets the User ID.
     *
     * @return User ID.
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * Sets the User ID.
     *
     * @param idUser User ID.
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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
     * Get the login.
     *
     * @return Login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login.
     *
     * @param login Login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the password.
     *
     * @return Password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password Password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }

    /**
     * (non-Javadoc)
     *
     * @see br.com.leucotron.livre.core.model.Model#getId()
     */
    @Override
    public Integer getId() {
        return this.getIdUser();
    }

    /**
     * (non-Javadoc)
     *
     * @see br.com.leucotron.livre.core.model.Model#setId(java.io.Serializable)
     */
    @Override
    public void setId(Integer id) {
        this.setIdUser(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
