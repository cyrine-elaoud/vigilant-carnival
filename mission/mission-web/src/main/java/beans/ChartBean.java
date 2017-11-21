package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import persistence.Member;
import persistence.Publicity;
import persistence.User;
import services.ReportingServiceLocal;
import services.UserServiceLocal;

@ManagedBean
public class ChartBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ReportingServiceLocal reportingServiceLocal;
	@EJB
	private UserServiceLocal userServiceLocal;

	private PieChartModel pieModel1;
	private BarChartModel barModel;
	private User user = new User();
	private List<User> users = new ArrayList<>();

	@PostConstruct
	public void init() {
		user = reportingServiceLocal.findMemberWithHighestNbPublicities();
		users = userServiceLocal.findAll();
		createBarModels();
		createPieModel1();

	}

	private void createBarModels() {
		createBarModel();
	}

	private void createPieModel1() {
		pieModel1 = new PieChartModel();

		for (User u : users) {
			if (u instanceof Member) {
				pieModel1.set(u.getName(), reportingServiceLocal.findNbPublicitiesByMember(u));
			}

		}
		pieModel1.setFill(false);
		pieModel1.setShowDataLabels(true);
		pieModel1.setDiameter(150);
		pieModel1.setTitle("nb publicities by member Pie");
		pieModel1.setLegendPosition("w");
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		ChartSeries like = new ChartSeries();
		like.setLabel("like");
		ChartSeries dislike = new ChartSeries();
		dislike.setLabel("dislike");
		List<Publicity> publicities = userServiceLocal.findPublicitiesByMember(user.getId());

		for (Publicity p : publicities) {
			like.set(p.getTitle(), reportingServiceLocal.findNbLikeByPublicity(p));
			dislike.set(p.getTitle(), reportingServiceLocal.findNbDisLikeByPublicity(p));
		}

		model.addSeries(like);
		model.addSeries(dislike);

		return model;
	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setTitle("Bar Chart of " + user.getName() + "'s publicities");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("publicity");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("nb like/dislike");
		yAxis.setMin(0);
		yAxis.setMax(50);
	}

	public ReportingServiceLocal getReportingServiceLocal() {
		return reportingServiceLocal;
	}

	public void setReportingServiceLocal(ReportingServiceLocal reportingServiceLocal) {
		this.reportingServiceLocal = reportingServiceLocal;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
