package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bond extends Instrument implements Serializable{
	private Float parvalue;
	private Float couponrate;
	private Integer callable=1;
	private String type;
	private Float saleprice;
	@Temporal(TemporalType.DATE)
	private Date maturitydate;
	@Temporal(TemporalType.DATE)
	private Date callabilityperiod;
	public Float getParvalue() {
		return parvalue;
	}
	public void setParvalue(Float parvalue) {
		this.parvalue = parvalue;
	}
	public Float getCouponrate() {
		return couponrate;
	}
	public void setCouponrate(Float couponrate) {
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
	public Float getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(Float saleprice) {
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
