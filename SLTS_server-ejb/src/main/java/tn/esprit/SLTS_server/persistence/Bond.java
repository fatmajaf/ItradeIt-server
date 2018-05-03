package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue(value = "bond")
public class Bond extends Instrument implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double parvalue;

	private Double couponrate;

	private Integer callable = 0;

	private Double saleprice;
	private Integer convertible = 0;
	private String backroundType;
	@Temporal(TemporalType.DATE)
	private Date maturitydate;
	@Temporal(TemporalType.DATE)
	private Date callabilityperiod;
	private Float availablecoupon;

	// added values

	private String typeOfCouponPayment;
	private Date dateOfPublication;

	// end add

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

	public Integer getConvertible() {
		return convertible;
	}

	public void setConvertible(Integer convertible) {
		this.convertible = convertible;
	}

	public String getBackroundType() {
		return backroundType;
	}

	public void setBackroundType(String backroundType) {
		this.backroundType = backroundType;
	}

	public Float getAvailablecoupon() {
		return availablecoupon;
	}

	public void setAvailablecoupon(Float availablecoupon) {
		this.availablecoupon = availablecoupon;
	}

	public void setCallable(Integer callable) {
		this.callable = callable;
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

	@Override
	public String toString() {
		return "Bond [parvalue=" + parvalue + ", couponrate=" + couponrate + ", callable=" + callable + ", saleprice="
				+ saleprice + ", convertible=" + convertible + ", backroundType=" + backroundType + ", maturitydate="
				+ maturitydate + ", callabilityperiod=" + callabilityperiod + ", availablecoupon=" + availablecoupon
				+ "]";
	}

	public String getTypeOfCouponPayment() {
		return typeOfCouponPayment;
	}

	public void setTypeOfCouponPayment(String typeOfCouponPayment) {
		this.typeOfCouponPayment = typeOfCouponPayment;
	}

	public Date getDateOfPublication() {
		return dateOfPublication;
	}

	public void setDateOfPublication(Date dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public Bond(String currency, Customer instrumentIssuer, String instrumentIssuerType, Double parvalue,
			Double couponrate, Integer callable, Double salePrice, Integer convertible, String backroundType,
			Date maturitydate, Date callabilityperiod, String typeOfCouponPayment, Date dateOfPublication) {
		super( currency, instrumentIssuer, instrumentIssuerType);
		this.parvalue = parvalue;
		this.couponrate = couponrate;
		this.callable = callable;
		this.saleprice = salePrice;
		this.convertible = convertible;
		this.backroundType = backroundType;
		this.maturitydate = maturitydate;
		this.callabilityperiod = callabilityperiod;
		this.typeOfCouponPayment = typeOfCouponPayment;
		this.dateOfPublication = dateOfPublication;
	}

	public Bond() {
		super();
	}

	
}
