package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import persistence.Publicity;
import persistence.PublicityStatus;
import persistence.User;
import utilities.GenericDAO;

/**
 * Session Bean implementation class PublicityService
 */
@Stateless
public class PublicityService extends GenericDAO<Publicity> implements PublicityServiceRemote, PublicityServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private PublicityServiceLocal publicityServiceLocal;

	/**
	 * Default constructor.
	 */
	public PublicityService() {
		super(Publicity.class);
	}

	@Override
	public List<Publicity> findPublicitiesByUser(User user) {
		return entityManager.createQuery("select p from Publicity p where p.member=:u").setParameter("u", user)
				.getResultList();
	}

	@Override
	public void traetPublicity(Publicity publicity, PublicityStatus publicityStatus) {
		publicity.setPublicityStatus(publicityStatus);

		publicityServiceLocal.update(publicity);

	}

	@Override
	public List<Publicity> findPublicitiesByStatus(PublicityStatus status) {
		return entityManager.createQuery("select p from Publicity p where p.publicityStatus=:u")
				.setParameter("u", status).getResultList();
	}

}
