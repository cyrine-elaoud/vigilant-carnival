package utilities;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import persistence.Agent;
import persistence.Member;
import persistence.Publicity;
import persistence.PublicityCategory;
import persistence.Visitor;
import services.PublicityServiceLocal;
import services.UserServiceLocal;

/**
 * Session Bean implementation class UtilityApp
 */
@Singleton
@LocalBean
@Startup
public class UtilityApp {
	@EJB
	private PublicityServiceLocal publicityServiceLocal;
	@EJB
	private UserServiceLocal userServiceLocal;

	/**
	 * Default constructor.
	 */
	public UtilityApp() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		Publicity publicity = new Publicity("pub1", "for sport", new Date(), new Date(), PublicityCategory.SPORT);
		Publicity publicity2 = new Publicity("pub2", "for electro", new Date(), new Date(), PublicityCategory.ELECTRO);
		Publicity publicity3 = new Publicity("pub3", "for food", new Date(), new Date(), PublicityCategory.FOOD);

		Member member = new Member("daly", "d", "d", "L1");
		Member member2 = new Member("syrine", "s", "s", "L2");
		Member member3 = new Member("sinda", "sin", "sin", "VIP");

		Agent agent = new Agent("mohamed", "m", "m", "first");

		Visitor visitor = new Visitor("samir", "sa", "sa");

		publicityServiceLocal.save(publicity);
		publicityServiceLocal.save(publicity2);
		publicityServiceLocal.save(publicity3);

		userServiceLocal.save(member);
		userServiceLocal.save(member2);
		userServiceLocal.save(member3);
		userServiceLocal.save(agent);
		userServiceLocal.save(visitor);
	}

}
