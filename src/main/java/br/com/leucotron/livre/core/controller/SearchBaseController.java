package br.com.leucotron.livre.core.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.leucotron.livre.core.dto.ModelDTO;
import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.model.Model;
import br.com.leucotron.livre.core.service.SearchService;
import br.com.leucotron.livre.util.JSonUtil;
import net.jodah.typetools.TypeResolver;

/**
 * The 'SearchBaseController' class provides the common API for controllers
 * to search models.
 * 
 * If a controller needs to search using a model, this controller MUST extend this class.
 * 
 * Provides search operations.
 * 
 * @author Virtus
 *
 * @param <M> Model type.
 * @param <T> Model ID type.
 * @param <D> Model DTO type.
 */
public abstract class SearchBaseController<M extends Model<T>, T extends Serializable, D extends ModelDTO> extends BaseController {

	/**
	 * Model mapper.
	 */
	protected static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	/**
	 * The model search service.
	 * 
	 * @return Model search service.
	 */
	protected abstract SearchService<M, T> getService();

	/**
	 * Searchs the model with the filter.
	 *
	 * @return DTO with list of model founded and filtered.
	 */
    @RequestMapping(method = RequestMethod.GET)
	public ResponseListDTO search() {
		SearchFilterDTO filter = new SearchFilterDTO();
		filter.setCurrentPage(1);
		filter.setPageSize(10);
		ResponseListDTO response = getService().search(filter);
		response.setItems(toListDTO(response.getItems()));

		return response;
	}

	/**
	 * Searchs the model with the filter.
	 * 
	 * @param filterJSon
	 * 		Filter as JSon text.
	 * @return DTO with list of model founded and filtered.
	 */
    @RequestMapping(method = RequestMethod.GET, params = {"filter"})
	public ResponseListDTO search(@RequestParam("filter") String filterJSon) {
		SearchFilterDTO filter = JSonUtil.fromJSon(filterJSon, SearchFilterDTO.class);
		
		ResponseListDTO response = getService().search(filter);
		response.setItems(toListDTO(response.getItems()));
		
		return response;
	}
	
	/**
	 * Gets one model by its specific ID.
	 * 
	 * @param id
	 * 		ID of instance.
	 * @return Model instance founded.
	 */
	@GetMapping("/{id}")
	public D getOne(@PathVariable T id) {
		return toDTO(getService().getOne(id));
	}
	
	/**
	 * Converts the Model DTO into Model.
	 * 
	 * @param modelDTO
	 * 		Model DTO.
	 * @return Model.
	 */
	@SuppressWarnings("unchecked")
	protected M toModel(D modelDTO) {
		Class<?>[] typeArg = TypeResolver.resolveRawArguments(SearchBaseController.class, getClass());
		
		return MODEL_MAPPER.map(modelDTO, (Class<M>) typeArg[0]);
	}
	
	/**
	 * Converts the Model into Model DTO.
	 * 
	 * @param model
	 * 		Model.
	 * @return Model DTO.
	 */
	@SuppressWarnings("unchecked")
	protected D toDTO(M model) {
		Class<?>[] typeArg = TypeResolver.resolveRawArguments(SearchBaseController.class, getClass());
		
		return MODEL_MAPPER.map(model, (Class<D>) typeArg[2]);
	}
	
	/**
	 * Converts the list of models into list of DTOs.
	 * 
	 * @param items
	 * 		Model items. 
	 * @return DTOs.
	 */
	@SuppressWarnings("unchecked")
	protected List<D> toListDTO(List<?> items) {
		List<D> dtos = new ArrayList<>();
		List<M> models = (List<M>) items;
		
		if(items != null) {
			models.forEach(model -> {
				dtos.add(toDTO(model));
			});
		}
		return dtos;
	}
	
	/**
	 * Response for the REST requests.
	 * 
	 * @param dto
	 * 		DTO.
	 * @param status
	 * 		Http Status.
	 * @return Response.
	 */
	protected ResponseEntity<D> responseEntity(D dto, HttpStatus status) {
		return new ResponseEntity<D>(dto, status);
	}
	
	/**
	 * Response OK (200) for the REST requests.
	 * 
	 * @param dto
	 * 		DTO.
	 * @return OK (200).
	 */
	protected ResponseEntity<D> ok(D dto) {
		return new ResponseEntity<D>(dto, HttpStatus.OK);
	}
}
