package br.com.leucotron.livre.core.dto;

/**
 * DTO for User.
 * 
 * @author Virtus
 */
public class UserDTO {

	/**
	 * Name.
	 */
	private String name;
	
	/**
	 * Sector.
	 */
	private String sector;

	/**
	 * Token.
	 */
	private String token;

	/**
	 * Refresh token.
	 */
	private String refreshToken;
	
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
	 * Gets the token.
	 * 
	 * @return Token.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 * 
	 * @param token
	 * 		Token.
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the refresh token.
	 * 
	 * @return Refresh token.
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Sets the refresh token.
	 * 
	 * @param refreshToken
	 * 		Refresh token.
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
