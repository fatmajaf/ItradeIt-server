package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class History implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date date;
	private Double revenue;
	private Double operationIncome;
	private Double netIncome;
	private Double totalAssets;
	private Double totalEquity;
	@ManyToOne
	private Company company;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	public Double getOperationIncome() {
		return operationIncome;
	}
	public void setOperationIncome(Double operationIncome) {
		this.operationIncome = operationIncome;
	}
	public Double getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}
	public Double getTotalAssets() {
		return totalAssets;
	}
	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}
	public Double getTotalEquity() {
		return totalEquity;
	}
	public void setTotalEquity(Double totalEquity) {
		this.totalEquity = totalEquity;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	

}
