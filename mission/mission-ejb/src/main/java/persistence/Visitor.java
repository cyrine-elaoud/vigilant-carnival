package persistence;

import java.io.Serializable;
import javax.persistence.*;
import persistence.User;

/**
 * Entity implementation class for Entity: Visitor
 *
 */
@Entity

public class Visitor extends User implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Visitor() {
		super();
	}

	public Visitor(String name, String login, String password) {
		super(name, login, password);
		// TODO Auto-generated constructor stub
	}
   
}
