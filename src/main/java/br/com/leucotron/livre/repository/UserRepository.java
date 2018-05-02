package br.com.leucotron.livre.repository;

import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.model.User;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

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
	@Query(value = "SELECT * FROM user U WHERE UPPER(U.login) = UPPER(?1) AND password = ?2", nativeQuery = true)
	User login(String username, String encryptedPassword);


	List<User> findByLogin(Serializable serializable);
}
