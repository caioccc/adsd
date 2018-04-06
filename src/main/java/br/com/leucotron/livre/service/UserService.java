package br.com.leucotron.livre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leucotron.livre.core.exception.BusinessException;
import br.com.leucotron.livre.core.service.CrudService;
import br.com.leucotron.livre.model.User;
import br.com.leucotron.livre.repository.UserRepository;
import br.com.leucotron.livre.util.CryptoUtil;

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
	 * @see br.com.leucotron.livre.core.service.CrudService#getRepository()
	 */
	@Override
	protected UserRepository getRepository() {
		return repository;
	}
	
	/**
	 * (non-Javadoc)
	 * @see br.com.leucotron.livre.core.service.CrudService#insert(br.com.leucotron.livre.core.model.Model)
	 */
	@Override
	public void insert(User user) throws BusinessException {
		user.setPassword(CryptoUtil.encrypt(user.getPassword()));
		
		super.insert(user);
	}
	
	/**
	 * (non-Javadoc)
	 * @see br.com.leucotron.livre.core.service.CrudService#update(java.io.Serializable, br.com.leucotron.livre.core.model.Model)
	 */
	@Override
	public void update(Integer id, User user) throws BusinessException {
		User dbUser = this.getOne(id);
		
		dbUser.setName(user.getName());
		dbUser.setLogin(user.getLogin());
		dbUser.setSector(user.getSector());
		
		super.update(id, dbUser);
	}

	/**
	 * Login the user.
	 * 
	 * @param username
	 * 		Username.
	 * @param password
	 * 		Password.
	 * @return User.
	 */
	public User login(String username, String password) {
		return getRepository().login(username, CryptoUtil.encrypt(password));
	}
}
