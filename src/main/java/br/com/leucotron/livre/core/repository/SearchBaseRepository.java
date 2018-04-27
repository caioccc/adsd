package br.com.leucotron.livre.core.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.model.Model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Base Repository for Search operations.
 *
 * @param <M> Model type.
 * @param <T> ID type.
 * @author Virtus
 */
@NoRepositoryBean
public interface SearchBaseRepository<M extends Model<T>, T extends Serializable> extends PagingAndSortingRepository<M, Serializable> {

    /**
     * Searches entities with the filter and paginated.
     *
     * @param filter Filter.
     * @return Result.
     */
    default ResponseListDTO search(SearchFilterDTO filter) {
        Page<M> result;
        result = !filter.getColumn().isEmpty() ? this.findAll(getSpecification(filter.getSearch()), new PageRequest(filter.getCurrentPage() - 1, filter.getPageSize(), Sort.Direction.fromString(filter.getSort()), filter.getColumn())) : this.findAll(getSpecification(filter.getSearch()), new PageRequest(filter.getCurrentPage() - 1, filter.getPageSize()));
        return new ResponseListDTO(result.getTotalPages(), result.getContent());
    }

    default Specification<M> getSpecification(String text) {
        if (!text.contains("%")) {
            text = "%" + text + "%";
        }
        final String finalText = text;
        return new Specification<M>() {
            @Override
            public Predicate toPredicate(Root<M> root, CriteriaQuery<?> cq, CriteriaBuilder builder) {
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

    Page<M> findAll(Specification<M> spec, Pageable pageable);

}
