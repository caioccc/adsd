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
     * Tags.
     */
    private String tags;

    private String role;

    /**
     * Login.
     */
    private String login;

    /**
     * Token.
     */
    private String token;

    /**
     * Refresh token.
     */
    private String refreshToken;

    private boolean master;

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
     * Gets the sector.
     *
     * @return Sector.
     */
    public String getTags() {
        return tags;
    }

    /**
     * Sets the tags.
     *
     * @param tags Tags.
     */
    public void setTags(String tags) {
        this.tags = tags;
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
     * @param token Token.
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
     * @param refreshToken Refresh token.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Gets the login.
     *
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the Login.
     *
     * @param login New Login.
     */
    public void setLogin(String login) {
        this.login = login;
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
}
