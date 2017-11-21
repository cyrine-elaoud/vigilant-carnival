package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import persistence.User;
import services.ReportingServiceLocal;

@ManagedBean
public class ChartBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ReportingServiceLocal reportingS;
	private User member;

	private PieChartModel pieModel1;

	@PostConstruct
	public void init() {
		createPieModels();
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	private void createPieModels() {
		createPieModel1();

	}

	private void createPieModel1() {
		pieModel1 = new PieChartModel();

		pieModel1.set("Brand 1", reportingS.findNbPublicitiesByMember(member));
		pieModel1.set("Brand 2", reportingS.findNbPublicitiesByMember(member));
		pieModel1.set("Brand 3", reportingS.findNbPublicitiesByMember(member));
		pieModel1.set("Brand 4", reportingS.findNbPublicitiesByMember(member));

		pieModel1.setTitle("Simple Pie");
		pieModel1.setLegendPosition("w");
	}

}
