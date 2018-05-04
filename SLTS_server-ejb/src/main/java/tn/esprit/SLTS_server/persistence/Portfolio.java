package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Portfolio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double cashflow;
	@Enumerated(EnumType.STRING)
	private Currency currency;
	private Date date_creation;
	@ManyToOne
	private User users;
	
	
	public Portfolio() {
		
	}
	public Portfolio(double cashflow2, Currency currency2, Date date1, int idUpdate) {
		cashflow=cashflow2;
		currency=currency2;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public double getCashflow() {
		return cashflow;
	}
	public void setCashflow(double cashflow) {
		this.cashflow = cashflow;
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
	public Currency getCurrency() {
		// TODO Auto-generated method stub
		return currency;
	}
	@Override
	public String toString() {
		return "Portfolio [id=" + id + ", cashflow=" + cashflow + ", currency=" + currency + ", date_creation="
				+ date_creation + ", users=" + users + "]";
	}
	
	

}
