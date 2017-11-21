package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import persistence.Publicity;
import persistence.PublicityCategory;
import persistence.PublicityStatus;
import persistence.User;
import services.PublicityServiceLocal;

@ManagedBean
@ViewScoped
public class PublicityBean {
	private List<Publicity> allValidatedPublicities = new ArrayList<>();
	private List<String> statusList = new ArrayList<>();
	private List<Publicity> newPublicities = new ArrayList<>();
	private String statusSelected = "ONHOLD";
	private String categorySelected = "DEFAULT";
	private boolean showForm = false;
	private Publicity publicity = new Publicity();
	private Publicity publicitySelected = new Publicity();
	private List<Publicity> publicities = new ArrayList<>();
	private List<String> categories = new ArrayList<>();
	@EJB
	private PublicityServiceLocal publicityServiceLocal;
	@ManagedProperty(value = "#{identity}")
	private Identity identity;

	public String doAddPublicity() {
		publicity.setMember(identity.getUser());
		publicityServiceLocal.save(publicity);
		return "";
	}

	public void doNew() {
		showForm = true;
		publicitySelected = new Publicity();
	}

	public void doLike() {
		publicitySelected.setNbLike(publicitySelected.getNbLike() + 1);
		publicityServiceLocal.update(publicitySelected);
	}

	public void doDisLike() {
		publicitySelected.setNbDisLike(publicitySelected.getNbDisLike() + 1);
		publicityServiceLocal.update(publicitySelected);
	}

	public void doValidate() {
		showForm = true;
		if (statusSelected.equalsIgnoreCase("ONHOLD")) {
			publicityServiceLocal.traetPublicity(publicitySelected, PublicityStatus.ONHOLD);
		} else if (statusSelected.equalsIgnoreCase("ACCEPTED")) {
			publicityServiceLocal.traetPublicity(publicitySelected, PublicityStatus.ACCEPTED);
		} else if (statusSelected.equalsIgnoreCase("REFUSED")) {
			publicityServiceLocal.traetPublicity(publicitySelected, PublicityStatus.REFUSED);
		}
		showForm = false;
		init();
	}

	public void doSelect() {
		showForm = true;
	}

	public void doCancel() {
		showForm = false;
	}

	public void doSaveOrUpdate() {
		if (categorySelected.equalsIgnoreCase("DEFAULT")) {
			publicitySelected.setPublicityCategory(PublicityCategory.DEFAULT);
		} else if (categorySelected.equalsIgnoreCase("ELECTRO")) {
			publicitySelected.setPublicityCategory(PublicityCategory.ELECTRO);
		} else if (categorySelected.equalsIgnoreCase("EVENTS")) {
			publicitySelected.setPublicityCategory(PublicityCategory.EVENTS);
		} else if (categorySelected.equalsIgnoreCase("FOOD")) {
			publicitySelected.setPublicityCategory(PublicityCategory.FOOD);
		} else if (categorySelected.equalsIgnoreCase("SPORT")) {
			publicitySelected.setPublicityCategory(PublicityCategory.SPORT);
		}
		publicitySelected.setMember(identity.getUser());
		publicitySelected.setPublicityStatus(PublicityStatus.NEW);
		publicityServiceLocal.update(publicitySelected);
		showForm = false;
		init();
	}

	public void doDelete() {
		publicityServiceLocal.delete(publicitySelected);
		showForm = false;
		init();
		publicitySelected = new Publicity();
	}

	@PostConstruct
	public void init() {
		User user = identity.getUser();
		publicities = publicityServiceLocal.findPublicitiesByUser(user);
		newPublicities = publicityServiceLocal.findPublicitiesByStatus(PublicityStatus.NEW);
		allValidatedPublicities = publicityServiceLocal.findPublicitiesByStatus(PublicityStatus.ACCEPTED);
	}

	public Publicity getPublicity() {
		return publicity;
	}

	public void setPublicity(Publicity publicity) {
		this.publicity = publicity;
	}

	public List<Publicity> getPublicities() {
		return publicities;
	}

	public void setPublicities(List<Publicity> publicities) {
		this.publicities = publicities;
	}

	public PublicityServiceLocal getPublicityServiceLocal() {
		return publicityServiceLocal;
	}

	public void setPublicityServiceLocal(PublicityServiceLocal publicityServiceLocal) {
		this.publicityServiceLocal = publicityServiceLocal;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public Publicity getPublicitySelected() {
		return publicitySelected;
	}

	public void setPublicitySelected(Publicity publicitySelected) {
		this.publicitySelected = publicitySelected;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getCategorySelected() {
		return categorySelected;
	}

	public void setCategorySelected(String categorySelected) {
		this.categorySelected = categorySelected;
	}

	public List<Publicity> getNewPublicities() {
		return newPublicities;
	}

	public void setNewPublicities(List<Publicity> newPublicities) {
		this.newPublicities = newPublicities;
	}

	public List<String> getCategories() {
		for (PublicityCategory category : PublicityCategory.values()) {
			if (!categories.contains(category.toString())) {
				categories.add(category.toString());
			} else {

			}

		}
		return categories;
	}

	public List<String> getStatusList() {
		for (PublicityStatus status : PublicityStatus.values())
			if (!statusList.contains(status.toString())) {
				statusList.add(status.toString());
			} else {

			}
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public String getStatusSelected() {
		return statusSelected;
	}

	public void setStatusSelected(String statusSelected) {
		this.statusSelected = statusSelected;
	}

	public List<Publicity> getAllPublicities() {
		return allValidatedPublicities;
	}

	public void setAllPublicities(List<Publicity> allPublicities) {
		this.allValidatedPublicities = allPublicities;
	}

}
