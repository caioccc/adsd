package br.com.leucotron.livre.service;

import br.com.leucotron.livre.core.dto.ResponseListDTO;
import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.dto.AssociatedSearchFilterDTO;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.repository.UserRepository;
import br.com.leucotron.livre.util.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Crud Service for model: User.
 *
 * @author Virtus
 */
@Service
public class UserService extends CrudService<User, Integer> {

    /**
     * User repository.
     */
    @Autowired
    private UserRepository repository;

    /**
     * (non-Javadoc)
     *
     * @see br.com.leucotron.livre.core.service.CrudService#getRepository()
     */
    @Override
    protected UserRepository getRepository() {
        return repository;
    }

    /**
     * (non-Javadoc)
     *
     * @see br.com.leucotron.livre.core.service.CrudService#insert(br.com.leucotron.livre.core.model.Model)
     */
    @Override
    public User insert(User user) throws BusinessException {
        user.setPassword(CryptoUtil.encrypt(user.getPassword()));

        return super.insert(user);
    }

    /**
     * Login the user.
     *
     * @param username Username.
     * @param password Password.
     * @return User.
     */
    public User login(String username, String password) {
        return getRepository().login(username, CryptoUtil.encrypt(password));
    }

    public ResponseListDTO getAllUsersWithAssociated(Integer id, AssociatedSearchFilterDTO filterDTO) {
        return getRepository().getAllUsersWithAssociated(id, filterDTO);
    }

    public List<User> findByLogin(String login) {
        return getRepository().findByLogin(login);
    }

    @Override
    public void validateInsert(User model) throws BusinessException {
        if (model.getLogin().equals("") || model.getLogin().isEmpty()) {
            throw new BusinessException("NotNull.userDTO.login");
        }
        if (!findByLogin(model.getLogin()).isEmpty()) {
            throw new BusinessException("NotValid.userDTO.login");
        }
        super.validateInsert(model);
    }

    @Override
    public void validateUpdate(User model) throws BusinessException {
        if (model.getLogin().equals("") || model.getLogin().isEmpty()) {
            throw new BusinessException("NotNull.userDTO.login");
        }
        List<User> users = getRepository().findByLogin(model.getLogin());
        for (User user : users) {
            if ((!user.getId().equals(model.getId()))) {
                throw new BusinessException("NotValid.userDTO.login");
            }
        }
        super.validateUpdate(model);
    }
}
