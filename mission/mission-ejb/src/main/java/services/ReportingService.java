package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import persistence.Publicity;
import persistence.User;

/**
 * Session Bean implementation class ReportingService
 */
@Stateless
public class ReportingService implements ReportingServiceRemote, ReportingServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	private UserServiceLocal userServiceLocal;

	/**
	 * Default constructor.
	 */
	public ReportingService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long findNbPublicitiesByMember(User member) {
		return (Long) entityManager.createQuery("select count (p) from Publicity p where p.member=:p")
				.setParameter("p", member).getSingleResult();
	}

	@Override
	public User findMemberWithHighestNbPublicities() {
		List<User> users = userServiceLocal.findAll();
		User theOne = users.get(0);
		for (User u : users) {
			if (findNbPublicitiesByMember(u) > findNbPublicitiesByMember(theOne)) {
				theOne = u;

			}
		}
		return theOne;
	}

	@Override
	public int findNbLikeByPublicity(Publicity publicity) {
		return (int) entityManager.createQuery("select  (p.nbLike) from Publicity p where p=:p")
				.setParameter("p", publicity).getSingleResult();
	}

	@Override
	public int findNbDisLikeByPublicity(Publicity publicity) {
		return (int) entityManager.createQuery("select  (p.nbDisLike) from Publicity p where p=:p")
				.setParameter("p", publicity).getSingleResult();
	}

}
