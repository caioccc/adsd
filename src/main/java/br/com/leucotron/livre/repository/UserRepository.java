package br.com.leucotron.livre.repository;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.dto.AssociatedSearchFilterDTO;
import br.com.leucotron.livre.dto.AssociatedUserDTO;
import br.com.leucotron.livre.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
     * @param username          Username.
     * @param encryptedPassword Encrypted password.
     * @return User's data.
     */
    @Query(value = "SELECT * FROM user U WHERE UPPER(U.login) = UPPER(?1) AND password = ?2", nativeQuery = true)
    User login(String username, String encryptedPassword);

    List<User> findByLogin(Serializable serializable);

    @Query(value = "SELECT * FROM " +
            "(SELECT user.iduser, user.name AS name, user.login, user.role, " +
            "(SELECT COUNT(user_organization.iduser) FROM livre.user_organization WHERE user_organization.iduser = user.iduser " +
            "AND user_organization.idorganization= ?1) AS associated FROM livre.user) AS FILTER " +
            "WHERE (FILTER.associated = ?2 OR FILTER.associated = ?3) AND FILTER.name LIKE ?4 \n#pageable\n",
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
        Boolean associated = searchFilter.getAssociated();
        if (associated != null) {
            if (!associated) {
                associated1 = 0;
                associated2 = 0;
            } else if (associated) {
                associated1 = 1;
                associated2 = 1;
            }
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


    default Specification<User> getSpecification(String text) {
        if (!text.contains("%")) {
            text = "%" + text + "%";
        }
        final String finalText = text;
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder builder) {
                return builder.or(root.getModel().getDeclaredSingularAttributes().stream().filter(a -> {
                            if (a.getJavaType().getSimpleName().equalsIgnoreCase("string")) {
                                return true;
                            } else {
                                return false;
                            }
                        }).map(a -> builder.like(root.get(a.getName()), finalText)
                        ).toArray(Predicate[]::new)
                );
            }
        };
    }
}
