package br.com.leucotron.livre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.leucotron.livre.core.model.Model;

/**
 * Model for table: USUARIO.
 * 
 * @author Virtus
 */
@Entity(name = "USUARIO")
public class User extends Model<Integer> {

	/**
	 * User ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDUSUARIO")
	private Integer idUser;
	
	/**
	 * Name.
	 */
	@Column(name = "NOME")
	private String name;
	
	/**
	 * Login.
	 */
	@Column(name = "LOGIN")
	private String login;
	
	/**
	 * Password.
	 */
	@Column(name = "SENHA")
	private String password;
	
	/**
	 * Sector.
	 */
	@Column(name = "SETOR")
	private String sector;
	
	/**
	 * Constructor.
	 */
	public User() { }

	/**
	 * Constructor.
	 * 
	 * @param idUser
	 * 		User ID.
	 * @param name
	 * 		Name.
	 * @param login
	 * 		Login.
	 * @param password
	 * 		Password.
	 * @param sector
	 * 		Sector.
	 */
	public User(Integer idUser, String name, String login, String password, String sector) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.login = login;
		this.password = password;
		this.sector = sector;
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

	/**
	 * Gets the sector.
	 * 
	 * @return Sector.
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * Sets the sector.
	 * 
	 * @param sector
	 * 		Sector.
	 */
	public void setSector(String sector) {
		this.sector = sector;
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
