package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
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
	private String currency;
	private Date date_creation;
	@ManyToOne
	private User users;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCashflow() {
		return cashflow;
	}
	public void setCashflow(double cashflow) {
		this.cashflow = cashflow;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String i) {
		this.currency = i;
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
	
	

}
