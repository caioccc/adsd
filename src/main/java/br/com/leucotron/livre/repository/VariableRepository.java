package br.com.leucotron.livre.repository;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.repository.CrudBaseRepository;
import br.com.leucotron.livre.model.Variable;
import br.com.leucotron.livre.util.Comparison;
import br.com.leucotron.livre.util.Condition;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface VariableRepository extends CrudBaseRepository<Variable, Integer> {

    public List<Variable> findByProjectIdProjectAndName(Integer idProject, String name);

    default ResponseListDTO searchByVariable(SearchFilterDTO searchFilter, Integer idProject) {
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition.Builder().
                setComparison(Comparison.EQ).
                setField("project").
                setValue(idProject).
                build());
        Page<Variable> result = searchFilter(searchFilter, conditions);
        return new ResponseListDTO(result.getTotalPages(), result.getContent());
    }

}
