package services;

import javax.ejb.Remote;

import persistence.User;
import utilities.IGenericDAO;

@Remote
public interface UserServiceRemote extends IGenericDAO<User> {
	User login(String login, String password);
}
