package br.com.leucotron.livre.core.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.leucotron.livre.core.dto.ModelDTO;
import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.dto.SearchFilterDTO;
import br.com.leucotron.livre.core.model.Model;
import br.com.leucotron.livre.core.service.SearchService;
import br.com.leucotron.livre.util.JSonUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.jodah.typetools.TypeResolver;

/**
 * The 'SearchBaseController' class provides the common API for controllers
 * to search models.
 * <p>
 * If a controller needs to search using a model, this controller MUST extend this class.
 * <p>
 * Provides search operations.
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
 * @author Virtus
 */
public abstract class SearchBaseController<M extends Model<T>, T extends Serializable, D extends ModelDTO> extends BaseController {

	/**
	 * Index order of the Model class in generics declaration. 
	 */
	protected static final Integer MODEL_INDEX_ORDER = 0;
	
	/**
	 * Index order of the DTO class in generics declaration. 
	 */
	protected static final Integer DTO_INDEX_ORDER = 2;
	
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
    @ApiOperation(value = "View a list of available items", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/v1.0", method = RequestMethod.GET)
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
     * @param filterJSon Filter as JSon text.
     * @return DTO with list of model founded and filtered.
     */
    @ApiOperation(value = "View a list of items filtered by the filter param", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/v1.0", method = RequestMethod.GET, params = {"filter"})
	public ResponseListDTO search(@RequestParam("filter") String filterJSon) {
		SearchFilterDTO filter = JSonUtil.fromJSon(filterJSon, SearchFilterDTO.class);
		ResponseListDTO response = getService().search(filter);
		response.setItems(toListDTO(response.getItems()));

		return response;
	}

    /**
     * Gets one model by its specific ID as DTO.
     *
     * @param id ID of instance.
     * @return DTO of Model instance founded.
     */
    @ApiOperation(value = "Get a item with an ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved object"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/v1.0/{id}")
    public D getOne(@PathVariable T id) {
        return toDTO(getOneModel(id));
    }
    
    /**
     * Gets one model by its specific ID as DTO.
     *
     * @param id ID of instance.
     * @return Model instance founded.
     */
    protected M getOneModel(T id) {
    	return getService().getOne(id);
    }

    /**
     * Converts the Model DTO into Model.
     *
     * @param modelDTO Model DTO.
     * @return Model.
     */
    protected M toModel(D modelDTO) {
        return mapTo(modelDTO, getModelClass());
    }

    /**
     * Converts the Model into Model DTO.
     *
     * @param model Model.
     * @return Model DTO.
     */
    protected D toDTO(M model) {
        return mapTo(model, getDTOClass());
    }

    /**
     * Converts the list of models into list of DTOs.
     *
     * @param items Model items.
     * @return DTOs.
     */
    protected List<D> toListDTO(List<?> items) {
        return toList(items, getDTOClass());
    }

    /**
     * Response for the REST requests.
     *
     * @param dto    DTO.
     * @param status Http Status.
     * @return Response.
     */
    protected ResponseEntity<D> responseEntity(D dto, HttpStatus status) {
        return new ResponseEntity<D>(dto, status);
    }

    /**
     * Response OK (200) for the REST requests.
     *
     * @param dto DTO.
     * @return OK (200).
     */
    protected ResponseEntity<D> ok(D dto) {
        return new ResponseEntity<D>(dto, HttpStatus.OK);
    }
    
    /**
     * Gets the Model class.
     * 
     * @return Model class.
     */
    @SuppressWarnings("unchecked")
	protected Class<M> getModelClass() {
    	return (Class<M>) getTypeArg()[MODEL_INDEX_ORDER];
    }
    
    /**
     * Gets the DTO class.
     * 
     * @return DTO class.
     */
    @SuppressWarnings("unchecked")
	protected Class<D> getDTOClass() {
    	return (Class<D>) getTypeArg()[DTO_INDEX_ORDER];
    }
    
    /**
     * Gets the type args of the class.
     * 
     * @return Type args.
     */
    protected Class<?>[] getTypeArg() {
    	return TypeResolver.resolveRawArguments(SearchBaseController.class, getClass());
    }
}
