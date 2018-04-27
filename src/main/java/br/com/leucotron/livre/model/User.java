package br.com.leucotron.livre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.leucotron.livre.core.model.Model;

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
	 * Sector.
	 */
	@Column(name = "tags")
	private String tags;

	/**
	 * Constructor.
	 */
	public User() { }


	public User(String name, String login, String password, String tags) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.tags = tags;
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
	 * @param idUser
	 * 		User ID.
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
	 * @param name
	 * 		Name.
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
	 * @param login
	 * 		Login.
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
	 * @param password
	 * 		Password.
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

	/**
	 * (non-Javadoc)
	 * @see br.com.leucotron.livre.core.model.Model#getId()
	 */
	@Override
	public Integer getId() {
		return this.getIdUser();
	}

	/**
	 * (non-Javadoc)
	 * @see br.com.leucotron.livre.core.model.Model#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Integer id) {
		this.setIdUser(id);
	}

}
