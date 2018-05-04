	package tn.esprit.SLTS_server.presentation.mbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.PooledConnection;

import tn.esprit.SLTS_server.persistence.Currency;
import tn.esprit.SLTS_server.persistence.Portfolio;
import tn.esprit.SLTS_server.persistence.User;
import tn.esprit.SLTS_server.services.PortfolioServiceImp;
import tn.esprit.SLTS_server.services.Portfolioservice;
@ViewScoped
@ManagedBean
public class PotfolioBean {

	private double cashflow;
	private Currency currency;
	private Date date_creation;
	private User users;
	private int idUpdate;
	
	public int getIdUpdate() {
		return idUpdate;
	}

	public void setIdUpdate(int idUpdate) {
		this.idUpdate = idUpdate;
	}

	private List<Portfolio> portfolios= new ArrayList<Portfolio>();
	private Portfolio port=new Portfolio();

	@EJB
	private Portfolioservice service;
	private void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String addPortfolio() {
	Date	date1= new Date();
	String navigateTo = null;
		service.addportfolio(new Portfolio(cashflow, currency, date1, idUpdate));

		navigateTo="/pages/portfolio/PortfolioManagement?faces-redirect=true";
	return navigateTo;
	}

	public void supprimer(Integer portfolioId) {
		service.deletePortfolio(portfolioId);
	}

	public void mettreAjourportfolio() {
        System.out.println("ftghjkl"+idUpdate);
        Portfolio p= new Portfolio();
        p.setId(idUpdate);
        p.setCashflow(cashflow);
        p.setCurrency(currency);
        p.setDate_creation(date_creation);
        System.out.println("avant merge "+p);
		service.updatePortfolio(p);
	}

	public void modifier(Portfolio p) {
		this.setCashflow(p.getCashflow());
		this.setCurrency(p.getCurrency());
		this.setDate_creation(p.getDate_creation());
	
		this.setIdUpdate(p.getId());
		System.out.println("id ......"+idUpdate);
		this.setUsers(p.getUsers());

	}

	public double getCashflow() {
		return cashflow;
	}

	public void setCashflow(double cashflow) {
		this.cashflow = cashflow;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public List<Portfolio> getPortfolios() {
		portfolios = service.findAllPortfolio();
		return portfolios;
	}

	public void setPortfolios(List<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	public Portfolio getPort() {
		return port;
	}

	public void setPort(Portfolio port) {
		this.port = port;
	}
	public String gotomangemnt()  {
		String  navigateTo = null;
navigateTo = "/pages/portfolio/PortfolioManagement?faces-redirect=true";
	   return navigateTo;
	}
	public void calcul1(){
		service.calculerStatus("DZD");
	}
	public void calcul2(){
		service.calculerStatus("USD");
	}
	public void calcul3(){
		service.calculerStatus("EUR");
	}
	public void calcul4(){
		service.calculerStatus("CAD");
	}
	public void calcul5(){
		service.calculerStatus("TND");
	}
	public String gotoforum(){
		String navigateTo = null;

		navigateTo="/pages/portfolio/forum?faces-redirect=true";
		return navigateTo;
	}
	public String gotopie(){
		String navigateTo = null;

		navigateTo="/pages/portfolio/pie?faces-redirect=true";
		return navigateTo;
	}
}
