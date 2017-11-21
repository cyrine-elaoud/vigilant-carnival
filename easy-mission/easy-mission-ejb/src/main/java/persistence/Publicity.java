package persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Publicity
 *
 */
@Entity
@Table(name = "t_publicity")
public class Publicity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	@Enumerated(EnumType.STRING)
	private PublicityCategory publicityCategory;
	@Enumerated(EnumType.STRING)
	private PublicityStatus publicityStatus;
	@ManyToOne
	private User member;
	private int nbLike=0;
	private int nbDisLike=0;
	private static final long serialVersionUID = 1L;

	public Publicity() {
		super();
	}

	public Publicity(String title, String description, Date startDate, Date endDate,
			PublicityCategory publicityCategory) {
		super();
		this.publicityStatus = PublicityStatus.NEW;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.publicityCategory = publicityCategory;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PublicityCategory getPublicityCategory() {
		return publicityCategory;
	}

	public void setPublicityCategory(PublicityCategory publicityCategory) {
		this.publicityCategory = publicityCategory;
	}

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public PublicityStatus getPublicityStatus() {
		return publicityStatus;
	}

	public void setPublicityStatus(PublicityStatus publicityStatus) {
		this.publicityStatus = publicityStatus;
	}

	public int getNbLike() {
		return nbLike;
	}

	public void setNbLike(int nbLike) {
		this.nbLike = nbLike;
	}

	public int getNbDisLike() {
		return nbDisLike;
	}

	public void setNbDisLike(int nbDisLike) {
		this.nbDisLike = nbDisLike;
	}

}
