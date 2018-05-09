package br.com.leucotron.livre.core.repository;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.model.Model;
import br.com.leucotron.livre.util.Condition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
     * @param searchFilter Filter.
     * @return Result.
     */
    default ResponseListDTO search(SearchFilterDTO searchFilter) {
        Page<M> result;

        if (searchFilter.getFilters().size() > 0) {
            result = searchFilter(searchFilter);
        } else if (!searchFilter.getColumn().isEmpty()) {
            result = this.findAll(
                    getSpecification(searchFilter.getSearch()),
                    new PageRequest(searchFilter.getCurrentPage() - 1, searchFilter.getPageSize(), Sort.Direction.fromString(searchFilter.getSort()), searchFilter.getColumn()));
        } else {
            result = this.findAll(
                    getSpecification(searchFilter.getSearch()),
                    new PageRequest(searchFilter.getCurrentPage() - 1, searchFilter.getPageSize()));
        }

        return new ResponseListDTO(result.getTotalPages(), result.getContent());
    }

    default Page<M> searchFilter(SearchFilterDTO searchFilter) {

        List<Condition> conditions = new ArrayList<>();

        searchFilter.getFilters().forEach((filter) -> {
            conditions.add(new Condition.Builder().
                    setComparison(filter.getComparison()).
                    setField(filter.getField()).
                    setValue(filter.getValue()).
                    build());
        });

        if (!searchFilter.getColumn().isEmpty()) {
            return this.findAll(
                    getSpecificationFilter(conditions),
                    new PageRequest(searchFilter.getCurrentPage() - 1, searchFilter.getPageSize(),
                            Sort.Direction.fromString(searchFilter.getSort()), searchFilter.getColumn())
            );
        }

        return this.findAll(
                getSpecificationFilter(conditions),
                new PageRequest(searchFilter.getCurrentPage() - 1, searchFilter.getPageSize())
        );
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

    default Specification<M> getSpecificationFilter(List<Condition> conditions) {
        return new Specification<M>() {

            @Override
            public Predicate toPredicate(Root<M> root, CriteriaQuery<?> cq, CriteriaBuilder builder) {
                List<Predicate> predicates = buildPredicates(root, cq, builder);
                return predicates.size() > 1
                        ? builder.and(predicates.toArray(new Predicate[predicates.size()]))
                        : predicates.get(0);
            }

            private List<Predicate> buildPredicates(Root<M> root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                conditions.forEach(condition -> predicates.add(buildPredicate(condition, root, criteriaQuery, criteriaBuilder)));
                return predicates;
            }

            private Predicate buildPredicate(Condition condition, Root<M> root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                try {
                    switch (condition.getComparison()) {
                        case EQ:
                            return buildEqualsPredicateToCriteria(condition, root, criteriaQuery, criteriaBuilder);
                        case LIKE:
                            return buildContainsPredicateToCriteria(condition, root, criteriaQuery, criteriaBuilder);
                        case IN:
                            return buildAndPredicateToCriteria(condition, root, criteriaQuery, criteriaBuilder);
                        default:
                            return buildEqualsPredicateToCriteria(condition, root, criteriaQuery, criteriaBuilder);
                    }
                } catch (NullPointerException ex) {
                    return null;
                }
            }

            private Predicate buildEqualsPredicateToCriteria(Condition condition, Root<M> root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(condition.getField()), condition.getValue());
            }

            private Predicate buildContainsPredicateToCriteria(Condition condition, Root<M> root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(condition.getField()), "%" + condition.getValue() + "%");
            }

            private Predicate buildAndPredicateToCriteria(Condition condition, Root<M> root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                String conditionValue = (String) condition.getValue();
                for (String value : conditionValue.split(",")) {
                    condition.setValue(","+value+",");
                    predicates.add(buildContainsPredicateToCriteria(condition, root, criteriaQuery, criteriaBuilder));
                }

                Predicate predicate = predicates.size() > 1
                        ? criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]))
                        : predicates.get(0);

                return criteriaBuilder.and(predicate);
            }

        };
    }

    Page<M> findAll(Specification<M> spec, Pageable pageable);

}
