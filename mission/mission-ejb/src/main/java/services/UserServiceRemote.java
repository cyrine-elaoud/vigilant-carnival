package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Publicity;
import persistence.User;
import utilities.IGenericDAO;

@Remote
public interface UserServiceRemote extends IGenericDAO<User> {
	User login(String login, String password);

	List<Publicity> findPublicitiesByMember(int id);
}
