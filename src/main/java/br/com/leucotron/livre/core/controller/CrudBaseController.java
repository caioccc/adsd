package br.com.leucotron.livre.core.controller;

import br.com.leucotron.livre.core.dto.ModelDTO;
import br.com.leucotron.livre.core.model.Model;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.util.NullAwareBeanUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * The 'CrudBaseController' class provides the common API for CRUD controllers.
 * <p>
 * If a controller is a model CRUD, this controller MUST extend this class.
 * <p>
 * Provides insertion, update and deletion for the model.
 *
 * @param <M> Model type.
 * @param <T> Model ID type.
 * @param <D> Model DTO type.
 * @author Virtus
 */
public abstract class CrudBaseController<M extends Model<T>, T extends Serializable, D extends ModelDTO> extends SearchBaseController<M, T, D> {

    /**
     * Gets the model CRUD service.
     *
     * @return Model CRUD service.
     */
    protected abstract CrudService<M, T> getService();

    /**
     * Inserts the model instance.
     *
     * @param modelDTO Model DTO.
     * @return Response.
     * @throws Exception
     */
    @ApiOperation(value = "Add an item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created object"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/v1.0")
	public ResponseEntity<D> insert(@Valid @RequestBody D modelDTO) throws Exception {
        M model = getService().insert(toModel(modelDTO));

        return created(toDTO(model));
    }

    /**
     * Updates the model instance.
     *
     * @param id       ID of instance.
     * @param modelDTO Model DTO.
     * @return Response.
     * @throws Exception
     */
    @ApiOperation(value = "Update an item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully item updated"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("/v1.0/{id}")
    public ResponseEntity<D> update(@PathVariable T id, @RequestBody D modelDTO) throws Exception {
        getService().update(id, toModel(modelDTO));

        return ok(modelDTO);
    }

    /**
     * Updates the model instance partially.
     *
     * @param id    ID of instance.
     * @param modelDTO Model.
     * @return Response.
     * @throws Exception
     */
    @ApiOperation(value = "Update partial of an item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Item partially changed successfully"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PatchMapping("/v1.0/{id}")
    public ResponseEntity<D> updatePartial(@PathVariable T id, @RequestBody D modelDTO) throws Exception {
        M model = toModel(modelDTO);
        M dbModel = getService().getOne(id);

        NullAwareBeanUtils.getInstance().copyProperties(dbModel, model);

        getService().update(id, dbModel);

        return ok(modelDTO);
    }

    /**
     * Deletes the model instance.
     *
     * @param id ID of instance.
     * @return Response.
     * @throws Exception
     */
    @ApiOperation(value = "Delete an item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted object."),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("/v1.0/{id}")
    public ResponseEntity<?> delete(@PathVariable T id) throws Exception {
        getService().delete(id);

        return success();
    }

	/**
	 * Response CREATED (201) for the REST requests.
	 * 
	 * @param dto
	 * 		DTO.
	 * @return CREATED (201).
	 */
	protected ResponseEntity<D> created(D dto) {
		return new ResponseEntity<D>(dto, HttpStatus.CREATED);
	}
}
