package tn.esprit.SLTS_server.presentation.mbeans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import tn.esprit.SLTS_server.utils.BSOption;

@ManagedBean
@ViewScoped
public class CalculationBean {
	//options
	private double spotprice;
	private double strikeprice;
	private double maturity;
	private double volatility;
	private double riskfreerate;
	private double annualyield;
	private BSOption optionCall;
	private BSOption optionPut;
	private double callprice;
	private double  putprice;
	//compound interest
	private double principal;
	private double rate;
	private double nbyears;
	private String type;
	private double compoundinterestresult;
	//forward
	private double spotpricef;
	private double riskfreeratef;
	private int matyears;
	private double forwardprice;
	
		
	@PostConstruct
	public void init(){
		optionCall= new BSOption(120, 100, 5, 0.005, 0.001, 0.1, 0, 'c');
		optionPut= new BSOption(120, 100, 5, 0.005, 0.001, 0.1, 0, 'p');
		System.out.println("ini" + spotprice);
	
	}
	
	public void changesmanagement() {
		
		System.out.println("changement here "+spotprice);
		optionCall.setK(strikeprice);
		optionPut.setK(strikeprice);
		optionCall.setS(spotprice);
		optionPut.setS(spotprice);
		optionCall.setT(maturity);
		optionPut.setT(maturity);
		optionCall.setVol(volatility);
		optionPut.setVol(volatility);
		optionCall.setR(riskfreerate);
		optionPut.setR(riskfreerate);
		optionCall.setQ(annualyield);
		optionPut.setQ(annualyield);
		callprice = optionCall.computePrice();
		putprice=optionPut.computePrice();
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:foo");
		
	}
	
	public void calculinterestcounpound(){
		
		System.out.println("calcul interest heere");
		
				Client client = ClientBuilder.newClient();
			   WebTarget target= client.target("http://localhost:18080/ForwardsCalculation/rest/");
			   WebTarget calcul= target.path("calcul").path(Double.toString(principal)).path(Double.toString(rate)).path(Double.toString(nbyears)).path(type);
			   Response response=calcul.request().get();
			   double result=response.readEntity(Double.class);
			   System.out.println("result : "+result);
			   compoundinterestresult=result;
			   response.close();
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form2:fooo");
		
	}
public void calculforward(){
		
		System.out.println("forward heere");
		
			   Client client = ClientBuilder.newClient();
			   WebTarget target= client.target("http://localhost:18080/ForwardsCalculation/rest/");
			   WebTarget calcul= target.path("calcul").path(Double.toString(spotpricef)).path(Double.toString(riskfreeratef)).path(Integer.toString(matyears));
			   Response response=calcul.request().get();
			   double result=response.readEntity(Double.class);
			   System.out.println("result : "+result);
			   forwardprice=result;
			   response.close();
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form3:foooo");	
	}


	public Double getSpotprice() {
		return spotprice;
	}
	public void setSpotprice(Double spotprice) {
		this.spotprice = spotprice;
	}
	public Double getStrikeprice() {
		return strikeprice;
	}
	public void setStrikeprice(Double strikeprice) {
		this.strikeprice = strikeprice;
	}
	public Double getMaturity() {
		return maturity;
	}
	public void setMaturity(Double maturity) {
		this.maturity = maturity;
	}
	public Double getVolatility() {
		return volatility;
	}
	public void setVolatility(Double volatility) {
		this.volatility = volatility;
	}
	public Double getRiskfreerate() {
		return riskfreerate;
	}
	public void setRiskfreerate(Double riskfreerate) {
		this.riskfreerate = riskfreerate;
	}
	public Double getAnnualyield() {
		return annualyield;
	}
	public void setAnnualyield(Double annualyield) {
		this.annualyield = annualyield;
	}
	public BSOption getOptionCall() {
		return optionCall;
	}
	public void setOptionCall(BSOption optionCall) {
		this.optionCall = optionCall;
	}
	public BSOption getOptionPut() {
		return optionPut;
	}
	public void setOptionPut(BSOption optionPut) {
		this.optionPut = optionPut;
	}

	public void setSpotprice(double spotprice) {
		this.spotprice = spotprice;
	}

	public void setStrikeprice(double strikeprice) {
		this.strikeprice = strikeprice;
	}

	public void setMaturity(double maturity) {
		this.maturity = maturity;
	}

	public void setVolatility(double volatility) {
		this.volatility = volatility;
	}

	public void setRiskfreerate(double riskfreerate) {
		this.riskfreerate = riskfreerate;
	}

	public void setAnnualyield(double annualyield) {
		this.annualyield = annualyield;
	}

	public double getCallprice() {
		return callprice;
	}

	public void setCallprice(double callprice) {
		this.callprice = callprice;
	}

	public double getPutprice() {
		return putprice;
	}

	public void setPutprice(double putprice) {
		this.putprice = putprice;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getNbyears() {
		return nbyears;
	}

	public void setNbyears(double nbyears) {
		this.nbyears = nbyears;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCompoundinterestresult() {
		return compoundinterestresult;
	}

	public void setCompoundinterestresult(double compoundinterestresult) {
		this.compoundinterestresult = compoundinterestresult;
	}

	public double getSpotpricef() {
		return spotpricef;
	}

	public void setSpotpricef(double spotpricef) {
		this.spotpricef = spotpricef;
	}

	public double getRiskfreeratef() {
		return riskfreeratef;
	}

	public void setRiskfreeratef(double riskfreeratef) {
		this.riskfreeratef = riskfreeratef;
	}

	

	public int getMatyears() {
		return matyears;
	}

	public void setMatyears(int matyears) {
		this.matyears = matyears;
	}

	public double getForwardprice() {
		return forwardprice;
	}

	public void setForwardprice(double forwardprice) {
		this.forwardprice = forwardprice;
	}
	
	
	
	
	

}
