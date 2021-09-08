package ar.com.educacionit.ws.repository;

import ar.com.educacionit.ws.domain.User;
import ar.com.educacionit.ws.exceptions.GenericException;

public interface UserRepository {
	
	public User getUser(String username) throws GenericException;

}
