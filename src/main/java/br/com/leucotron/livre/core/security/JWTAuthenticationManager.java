package br.com.leucotron.livre.core.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.com.leucotron.livre.core.config.AppContext;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.service.UserService;

/**
 * Authentication Manager.
 *
 * @author Virtus
 *
 */
public class JWTAuthenticationManager implements AuthenticationManager {

    /**
     * User service.
     */
    private UserService userService;

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
     */
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        User loggedUser = this.getUserService().login(auth.getName(), (String) auth.getCredentials());

        if (loggedUser != null) {
            return new UsernamePasswordAuthenticationToken(loggedUser, auth.getCredentials());
        }

        throw new BadCredentialsException("Usuário e/ou senha inválidos.");
    }

    /**
     * Gets the User service.
     *
     * @return User service.
     */
    protected UserService getUserService() {

        if (this.userService == null) {
            this.userService = AppContext.getBean(UserService.class);
        }

        return this.userService;
    }
}
