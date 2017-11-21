package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Publicity;
import persistence.PublicityStatus;
import utilities.IGenericDAO;

@Remote
public interface PublicityServiceRemote extends IGenericDAO<Publicity> {
	void traetPublicity(Publicity publicity, PublicityStatus publicityStatus);

	List<Publicity> findPublicitiesByStatus(PublicityStatus status);
}
