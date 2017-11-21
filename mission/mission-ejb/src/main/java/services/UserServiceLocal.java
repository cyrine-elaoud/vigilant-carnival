package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Publicity;
import persistence.User;
import utilities.IGenericDAO;

@Local
public interface UserServiceLocal extends IGenericDAO<User> {
	User login(String login, String password);

	List<Publicity> findPublicitiesByMember(int id);
}
