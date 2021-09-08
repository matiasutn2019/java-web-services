package ar.com.educacionit.ws.services.impl;

import ar.com.educacionit.ws.domain.User;
import ar.com.educacionit.ws.exceptions.GenericException;
import ar.com.educacionit.ws.exceptions.ServiceException;
import ar.com.educacionit.ws.repository.UserRepository;
import ar.com.educacionit.ws.repository.impl.UserRepositoryHibImpl;
import ar.com.educacionit.ws.services.UserService;

public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceImpl() {
		this.userRepository = new UserRepositoryHibImpl();
	}
	
	@Override
	public User getUserByUsername(String username) throws ServiceException {
		
		try {
			return this.userRepository.getUser(username);
		} catch (GenericException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
