package gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Publicity;
import services.PublicityServiceRemote;
import services.ReportingServiceRemote;
import services.UserServiceRemote;

public class TestFindNbLikeByPublicity {
	public static void main(String[] args) throws NamingException {

		Context context = new InitialContext();
		ReportingServiceRemote reportingServiceRemote = (ReportingServiceRemote) context
				.lookup("easy-mission-ear/easy-mission-ejb/ReportingService!services.ReportingServiceRemote");
		UserServiceRemote userServiceRemote = (UserServiceRemote) context
				.lookup("easy-mission-ear/easy-mission-ejb/UserService!services.UserServiceRemote");

		PublicityServiceRemote publicityServiceRemote = (PublicityServiceRemote) context
				.lookup("easy-mission-ear/easy-mission-ejb/PublicityService!services.PublicityServiceRemote");

		Publicity publicity = publicityServiceRemote.find(1);
		System.out.println(reportingServiceRemote.findNbLikeByPublicity(publicity));
	}
}
