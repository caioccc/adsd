package br.com.leucotron.livre.repository;

import org.springframework.data.jpa.repository.Query;

import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.model.User;

/**
 * CRUD Repository for entity: User.
 * 
 * @author Virtus
 */
public interface UserRepository extends CrudBaseRepository<User, Integer> {

	/**
	 * Login the user.
	 * 
	 * @param username
	 * 		Username.
	 * @param encryptedPassword
	 * 		Encrypted password.
	 * @return User's data.
	 */
	@Query(value = "SELECT * FROM USUARIO U WHERE UPPER(U.LOGIN) = UPPER(?1) AND SENHA = ?2", nativeQuery = true)
	User login(String username, String encryptedPassword);
}
