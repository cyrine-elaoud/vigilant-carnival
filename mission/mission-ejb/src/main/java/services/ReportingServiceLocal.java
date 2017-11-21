package services;

import javax.ejb.Local;

import persistence.Publicity;
import persistence.User;

@Local
public interface ReportingServiceLocal {
	Long findNbPublicitiesByMember(User member);

	User findMemberWithHighestNbPublicities();

	int findNbLikeByPublicity(Publicity publicity);

	int findNbDisLikeByPublicity(Publicity publicity);

}
