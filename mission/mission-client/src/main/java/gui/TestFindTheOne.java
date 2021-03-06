package gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.User;
import services.ReportingServiceRemote;
import services.UserServiceRemote;

public class TestFindTheOne {
	public static void main(String[] args) throws NamingException {

		Context context = new InitialContext();
		ReportingServiceRemote reportingServiceRemote = (ReportingServiceRemote) context
				.lookup("easy-mission-ear/easy-mission-ejb/ReportingService!services.ReportingServiceRemote");
		UserServiceRemote userServiceRemote = (UserServiceRemote) context
				.lookup("easy-mission-ear/easy-mission-ejb/UserService!services.UserServiceRemote");

		User member = reportingServiceRemote.findMemberWithHighestNbPublicities();

		System.out.println(member.getName());
	}
}
