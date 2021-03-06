package br.com.leucotron.livre.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.dto.AssociatedSearchFilterDTO;
import br.com.leucotron.livre.dto.AssociatedUserDTO;
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
     * @param username          Username.
     * @param encryptedPassword Encrypted password.
     * @return User's data.
     */
    @Query(value = "SELECT * FROM user U WHERE UPPER(U.login) = UPPER(?1) AND password = ?2", nativeQuery = true)
    User login(String username, String encryptedPassword);

    List<User> findByLogin(String login);

    @Query(value = "SELECT * FROM " +
            "(SELECT user.iduser, user.name AS name, user.login, user.role, " +
            "(SELECT COUNT(user_organization.iduser) FROM livre.user_organization WHERE user_organization.iduser = user.iduser " +
            "AND user_organization.idorganization= ?1) AS associated FROM livre.user) AS FILTER " +
            "WHERE (FILTER.associated = ?2 OR FILTER.associated = ?3) AND FILTER.name LIKE ?4 \n-- #pageable\n",
            countQuery = "SELECT COUNT(*) FROM (SELECT * FROM (SELECT user.iduser, user.name, user.login, user.role, " +
                    "(SELECT COUNT(US_ORG.iduser) FROM livre.user_organization AS US_ORG " +
                    "WHERE US_ORG.iduser = user.iduser AND US_ORG.idorganization= ?1) AS associated FROM livre.user) " +
                    "AS FILTER WHERE (FILTER.associated = ?2 OR FILTER.associated = ?3) AND FILTER.name LIKE ?4) AS COUNT_ASSOCIATED",
            nativeQuery = true)
    Page<Object[]> getAllUsersWithChecked(Integer idOrganization, Integer associated1, Integer associated2, String search, Pageable pageable);

    default ResponseListDTO getAllUsersWithAssociated(Integer idOrganization, AssociatedSearchFilterDTO searchFilter) {
        Page<Object[]> result;
        List<AssociatedUserDTO> associatedUserDTOS = new ArrayList<>();

        Integer associated1 = 1;
        Integer associated2 = 0;
        Integer associated = searchFilter.getIntegerValue();
        if (associated != null) {
            associated1 = associated;
            associated2 = associated;
        }

        if (!searchFilter.getColumn().isEmpty()) {
            result = this.getAllUsersWithChecked(idOrganization, associated1, associated2, "%" + searchFilter.getSearch() + "%",
                    new PageRequest(searchFilter.getCurrentPage() - 1, searchFilter.getPageSize(), Sort.Direction.fromString(searchFilter.getSort()), searchFilter.getColumn()));
        } else {
            result = this.getAllUsersWithChecked(idOrganization, associated1, associated2, "%" + searchFilter.getSearch() + "%",
                    new PageRequest(searchFilter.getCurrentPage() - 1, searchFilter.getPageSize()));
        }

        result.forEach(o -> {
            BigInteger value = (BigInteger) o[4];
            associatedUserDTOS.add(new AssociatedUserDTO((Integer) o[0], (String) o[1], value.intValue() == 1));
        });

        return new ResponseListDTO(result.getTotalPages(), associatedUserDTOS);
    }
    
    public boolean existsByLoginAndOrganizationsIdOrganization(String login, Integer idOrganization);
    
}
