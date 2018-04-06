package br.com.leucotron.livre.core.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.model.Model;

/**
 * Base Repository for Search operations.
 * 
 * @author Virtus
 *
 * @param <M> Model type.
 * @param <T> ID type.
 */
@NoRepositoryBean
public interface SearchBaseRepository<M extends Model<T>, T extends Serializable> extends PagingAndSortingRepository<M, Serializable> {

	/**
	 * Searches entities with the filter and paginated.
	 * 
	 * @param filter
	 * 		Filter.
	 * @return Result.
	 */
	default ResponseListDTO search(SearchFilterDTO filter) {
		Page<M> result = this.findAll(new PageRequest(filter.getCurrentPage() - 1, filter.getPageSize()));
		return new ResponseListDTO(result.getTotalPages(), result.getContent());
	}
}
