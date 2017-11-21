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
	private List<User> users = new ArrayList<>();

	@PostConstruct
	public void init() {
		users = userServiceLocal.findAll();
		createBarModels();
		createPieModel1();

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

	private BarChartModel initBarModel(int id) {
		BarChartModel model = new BarChartModel();
		List<Publicity> publicities = userServiceLocal.findPublicitiesByMember(id);
		ChartSeries boys = new ChartSeries();
		boys.setLabel("Boys");
		boys.set("2004", 120);
		boys.set("2005", 100);
		boys.set("2006", 44);
		boys.set("2007", 150);
		boys.set("2008", 25);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Girls");
		girls.set("2004", 52);
		girls.set("2005", 60);
		girls.set("2006", 110);
		girls.set("2007", 135);
		girls.set("2008", 120);

		for (Publicity p : publicities) {
			ChartSeries like = new ChartSeries();
			like.setLabel("like");
			like.set(p.getTitle(), reportingServiceLocal.findNbLikeByPublicity(p));

			ChartSeries dislike = new ChartSeries();
			dislike.setLabel("dislike");
			dislike.set(p.getTitle(), reportingServiceLocal.findNbDisLikeByPublicity(p));

			model.addSeries(like);
			model.addSeries(dislike);
		}
		model.addSeries(boys);
		model.addSeries(girls);
		return model;
	}

	private void createBarModels() {
		for (User u : users) {
			if (u instanceof Member) {
				createBarModel(u.getId());
			}
		}

	}

	private void createBarModel(int id) {
		User member = userServiceLocal.find(id);
		barModel = initBarModel(id);

		barModel.setTitle("Bar Chart for " + member.getName());
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("publicity");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("review");
		yAxis.setMin(0);
		yAxis.setMax(200);
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

}
