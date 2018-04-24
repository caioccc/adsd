package br.com.leucotron.livre.core.controller;

import br.com.leucotron.livre.core.dto.ModelDTO;
import br.com.leucotron.livre.core.model.Model;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.util.NullAwareBeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import javax.validation.Valid;

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
    @PostMapping
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
    @PutMapping("/{id}")
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
    @PatchMapping("/{id}")
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
    @DeleteMapping("/{id}")
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
