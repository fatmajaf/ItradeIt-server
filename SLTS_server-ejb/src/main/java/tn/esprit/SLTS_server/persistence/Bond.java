package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@DiscriminatorValue(value="bond")
public class Bond extends Instrument implements Serializable{
	private Double parvalue;
	private Double couponrate;
	private Integer callable=1;
	private String type;
	private Double saleprice;
	@Temporal(TemporalType.DATE)
	private Date maturitydate;
	@Temporal(TemporalType.DATE)
	private Date callabilityperiod;
	public Double getParvalue() {
		return parvalue;
	}
	public void setParvalue(Double parvalue) {
		this.parvalue = parvalue;
	}
	public Double getCouponrate() {
		return couponrate;
	}
	public void setCouponrate(Double couponrate) {
		this.couponrate = couponrate;
	}
	public Integer getCallable() {
		return callable;
	}
	public void setCallable(Integer callable) {
		this.callable = callable;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}
	public Date getMaturitydate() {
		return maturitydate;
	}
	public void setMaturitydate(Date maturitydate) {
		this.maturitydate = maturitydate;
	}
	public Date getCallabilityperiod() {
		return callabilityperiod;
	}
	public void setCallabilityperiod(Date callabilityperiod) {
		this.callabilityperiod = callabilityperiod;
	}

}
