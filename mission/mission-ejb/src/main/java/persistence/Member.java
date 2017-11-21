package persistence;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity

public class Member extends User implements Serializable {

	private String memberCard;
	private static final long serialVersionUID = 1L;

	public Member() {
		super();
	}

	public Member(String name, String login, String password, String memberCard) {
		super(name, login, password);
		this.memberCard = memberCard;
	}

	public String getMemberCard() {
		return this.memberCard;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

}
