package br.com.leucotron.livre.repository;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.dto.AssociatedUserDTO;
import br.com.leucotron.livre.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
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
			"FROM livre.user \n-- #pageable\n",
			nativeQuery = true)
    Page<Object[]> getAllUsersWithChecked(Integer idOrganization, Pageable pageable);


    default ResponseListDTO getAllUsersWithAssociated(Integer idOrganization, SearchFilterDTO searchFilter) {
        Page<Object[]> result;
        List<AssociatedUserDTO> associatedUserDTOS = new ArrayList<>();

        if (!searchFilter.getColumn().isEmpty()) {
            result = this.getAllUsersWithChecked(idOrganization,
                    new PageRequest(searchFilter.getCurrentPage() - 1, searchFilter.getPageSize(), Sort.Direction.fromString(searchFilter.getSort()), searchFilter.getColumn()));
        } else {
            result = this.getAllUsersWithChecked(idOrganization,
                    new PageRequest(searchFilter.getCurrentPage() - 1, searchFilter.getPageSize()));
        }

        result.forEach(o -> {
            BigInteger value = (BigInteger) o[4];
            associatedUserDTOS.add(new AssociatedUserDTO((Integer) o[0], (String) o[1], value.intValue() == 1));
        });

        return new ResponseListDTO(result.getTotalPages(), associatedUserDTOS);
    }
}
