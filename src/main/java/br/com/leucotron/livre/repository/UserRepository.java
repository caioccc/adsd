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

	@Query(value = "SELECT user.iduser, user.name, user.login, user.role, \n" +
			"\n" +
			"  (SELECT\n" +
			"     COUNT(US_ORG.iduser)" +
			"   FROM livre.user_organization AS US_ORG WHERE US_ORG.iduser = user.iduser  AND US_ORG.idorganization = ?1) AS associated\n" +
			"FROM livre.user;\n" +
			"\n" +
			"\n" +
			"\n", nativeQuery = true)
	List<Object[]> getAllUsersWithChecked(Integer idOrganization);

}
