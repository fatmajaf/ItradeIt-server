package tn.esprit.SLTS_server.presentation.mbeans;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import tn.esprit.SLTS_server.instrumentServices.InstrumentServiceRemote;
import tn.esprit.SLTS_server.persistence.Bond;

 
@ManagedBean
public class ChartView implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LineChartModel lineModel;
    

  
    
    private BarChartModel barModel;
    
    @EJB
	private InstrumentServiceRemote instrumentServiceRemote;
     
    @PostConstruct
    public void init() {
        createLineModels();
        createBarModels();
    }
 

 
 
     
    private void createLineModels() {
      //  lineModel1 = initLinearModel();
       // lineModel1.setTitle("Linear Chart");
      //  lineModel1.setLegendPosition("e");
      //  Axis yAxis = lineModel.getAxis(AxisType.Y);
       // yAxis.setMin(0);
     //   yAxis.setMax(10);
         
        lineModel = lineModelMethod();
        lineModel.setTitle("Saleprice Chart Of Bonds Published");
        lineModel.setLegendPosition("Price");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Saleprice"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Bond's Id");
       
        
       
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
     
    public LineChartModel lineModelMethod() {
    	ChartSeries series1 = new ChartSeries();
    	
    	
    	List<Bond> series1List = instrumentServiceRemote.findAllBonds();
    	System.out.println("hii"+series1List);
    	for (Bond t : series1List) {
    	    series1.set(t.getId(), t.getSaleprice());
    	}

    	LineChartModel lineModel = new LineChartModel();
    	lineModel.addSeries(series1);

    	return lineModel;
    	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public InstrumentServiceRemote getInstrumentServiceRemote() {
		return instrumentServiceRemote;
	}

	public void setInstrumentServiceRemote(InstrumentServiceRemote instrumentServiceRemote) {
		this.instrumentServiceRemote = instrumentServiceRemote;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}


	 private BarChartModel initBarModel() {
	        BarChartModel model = new BarChartModel();
	 
	        ChartSeries boys = new ChartSeries();
	        boys.setLabel("Type Of Coupon Payment");
	        boys.set("Monthly",instrumentServiceRemote.nbrBondsByType("Monthly") );
	        boys.set("Quarterly", instrumentServiceRemote.nbrBondsByType("Quarterly") );
	       
	        boys.set(" Semi Annually", instrumentServiceRemote.nbrBondsByType(" Semi Annually"));
	        boys.set("Annually", instrumentServiceRemote.nbrBondsByType("Annually"));
	        boys.set("Bi Annual", instrumentServiceRemote.nbrBondsByType("Bi Annual"));
	 
	     
	 
	        model.addSeries(boys);
	     
	         
	        return model;
	    }
	     
	    private void createBarModels() {
	        createBarModel();
	        createBarModel();
	    }
	     
	    private void createBarModel() {
	        barModel = initBarModel();
	         
	        barModel.setTitle("Statistics");
	        barModel.setLegendPosition("ne");
	         
	        Axis xAxis = barModel.getAxis(AxisType.X);
	        xAxis.setLabel("Bonds classified By Type Of Coupon Payment");
	         
	        Axis yAxis = barModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Number of Bonds");
	        yAxis.setMin(0);
	        yAxis.setMax(20);
	    }
}