package services;

import javax.ejb.Remote;

import persistence.Publicity;
import persistence.User;

@Remote
public interface ReportingServiceRemote {
	Long findNbPublicitiesByMember(User member);

	User findMemberWithHighestNbPublicities();

	int findNbLikeByPublicity(Publicity publicity);

	int findNbDisLikeByPublicity(Publicity publicity);
}
