package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import persistence.Agent;
import persistence.Member;
import persistence.User;
import persistence.Visitor;
import services.UserServiceLocal;

@ManagedBean(name = "identity")
@SessionScoped
public class Identity {
	private User user = new User();
	private Boolean isLogged = false;
	private Boolean loggedInAsMember = false;
	private Boolean loggedInAsAgent = false;
	private Boolean loggedInAsVisitor = false;
	@EJB
	private UserServiceLocal userServiceLocal;

	public String doLogin() {
		String navigateTo = null;
		User userLoggedIn = userServiceLocal.login(user.getLogin(), user.getPassword());
		if (userLoggedIn != null) {
			user = userLoggedIn;
			isLogged = true;
			if (userLoggedIn instanceof Member) {
				loggedInAsMember = true;
				navigateTo = "/pages/memberHome/managePublicities?faces-redirect=true";
			} else if (userLoggedIn instanceof Agent) {
				loggedInAsAgent = true;
				navigateTo = "/pages/agentHome/home?faces-redirect=true";
			} else if (userLoggedIn instanceof Visitor) {
				loggedInAsVisitor = true;
				navigateTo = "/pages/visitorHome/listPublicities?faces-redirect=true";
			}
		} else {
			navigateTo = "/fail?faces-redirect=true";
		}
		return navigateTo;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		loggedInAsAgent = false;
		loggedInAsMember = false;
		loggedInAsVisitor = false;

		return "/login?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

	public Boolean getLoggedInAsMember() {
		return loggedInAsMember;
	}

	public void setLoggedInAsMember(Boolean loggedInAsMember) {
		this.loggedInAsMember = loggedInAsMember;
	}

	public UserServiceLocal getUserServiceLocal() {
		return userServiceLocal;
	}

	public void setUserServiceLocal(UserServiceLocal userServiceLocal) {
		this.userServiceLocal = userServiceLocal;
	}

	public Boolean getLoggedInAsAgent() {
		return loggedInAsAgent;
	}

	public void setLoggedInAsAgent(Boolean loggedInAsAgent) {
		this.loggedInAsAgent = loggedInAsAgent;
	}

	public Boolean getLoggedInAsVisitor() {
		return loggedInAsVisitor;
	}

	public void setLoggedInAsVisitor(Boolean loggedInAsVisitor) {
		this.loggedInAsVisitor = loggedInAsVisitor;
	}

}
