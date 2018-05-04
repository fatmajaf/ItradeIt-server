package tn.esprit.SLTS_server.presentation.mbeans;
 
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import tn.esprit.SLTS_server.services.PortfolioServiceImp;
import tn.esprit.SLTS_server.services.Portfolioservice;
 
@ManagedBean
@ViewScoped
public class ChartView implements Serializable {
 
	@EJB
	PortfolioServiceImp service; 
	private Long uzd ;
	private Long usd;
	private Long eur;
	private Long cad;
	private Long tnd;
	
    /**
	 *  pieModel1.set("DZD", service.calculerStatus1());
        System.out.println(service.calculerStatus1());
        pieModel1.set(" USD", service.calculerStatus2());
        pieModel1.set("EUR", service.calculerStatus3());
        pieModel1.set("CAD", service.calculerStatus4());
        pieModel1.set("TND", service.calculerStatus5());
         
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel1;
 
    @PostConstruct
    public void init() {
       uzd= service.calculerStatus("DZD");
       usd=  service.calculerStatus("USD");
       eur=  service.calculerStatus("EUR");
       cad=  service.calculerStatus("CAD");
       tnd= service.calculerStatus("TND");
    }

	public PortfolioServiceImp getService() {
		return service;
	}

	public void setService(PortfolioServiceImp service) {
		this.service = service;
	}

	public Long getUzd() {
		return uzd;
	}

	public void setUzd(Long uzd) {
		this.uzd = uzd;
	}

	public Long getUsd() {
		return usd;
	}

	public void setUsd(Long usd) {
		this.usd = usd;
	}

	public Long getEur() {
		return eur;
	}

	public void setEur(Long eur) {
		this.eur = eur;
	}

	public Long getCad() {
		return cad;
	}

	public void setCad(Long cad) {
		this.cad = cad;
	}

	public Long getTnd() {
		return tnd;
	}

	public void setTnd(Long tnd) {
		this.tnd = tnd;
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
    
 
    
    
}