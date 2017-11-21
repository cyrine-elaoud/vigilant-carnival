package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Publicity;
import persistence.PublicityStatus;
import persistence.User;
import utilities.IGenericDAO;

@Local
public interface PublicityServiceLocal extends IGenericDAO<Publicity> {
	List<Publicity> findPublicitiesByUser(User user);

	void traetPublicity(Publicity publicity, PublicityStatus publicityStatus);

	List<Publicity> findPublicitiesByStatus(PublicityStatus status);
}
