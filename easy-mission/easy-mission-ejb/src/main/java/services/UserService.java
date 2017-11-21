package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.User;
import utilities.GenericDAO;

/**
 * Session Bean implementation class UserService
 */
@Stateless
public class UserService extends GenericDAO<User> implements UserServiceRemote, UserServiceLocal {
@PersistenceContext
private EntityManager entityManager;
	/**
	 * Default constructor.
	 */
	public UserService() {
		super(User.class);
	}

	@Override
	public User login(String login, String password) {
		User user = null;
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login=:param1 AND u.password=:param2");
		query.setParameter("param1", login);
		query.setParameter("param2", password);
		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			System.err.println("user not found");
		}
		return user;
	}

}
