package br.com.leucotron.livre.repository;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.model.Project;
import br.com.leucotron.livre.util.Comparison;
import br.com.leucotron.livre.util.Condition;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for entity: Project.
 *
 * @author Virtus
 */
public interface ProjectRepository extends CrudBaseRepository<Project, Integer> {

    default ResponseListDTO searchByOrganization(SearchFilterDTO searchFilter, Integer idOrganization) {
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition.Builder().
                setComparison(Comparison.EQ).
                setField("organization").
                setValue(idOrganization).
                build());
        Page<Project> result = searchFilter(searchFilter, conditions);
        return new ResponseListDTO(result.getTotalPages(), result.getContent());
    }
}
