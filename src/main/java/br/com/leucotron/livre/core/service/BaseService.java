package br.com.leucotron.livre.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The 'BaseService' class provides the common API for all services.
 * <p>
 * All services MUST extend this class.
 *
 * @author Virtus
 */
public abstract class BaseService {

    /**
     * Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Current User.
     */
    protected String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
