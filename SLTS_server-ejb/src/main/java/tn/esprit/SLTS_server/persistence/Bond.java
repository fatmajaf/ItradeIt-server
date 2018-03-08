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
	/*
	 * 
Par value is the face value of a bond. 
Par value is important for a bond or fixed-income instrument because it determines its maturity value as well as the dollar value of coupon payments. 
Par value for a bond is typically $1,000 or $100. 
The market price of a bond may be above or below par, depending on factors such as the level of interest rates and the bond’s credit status.


	 */
	private Double couponrate;
	//interest rate
		//Once set at the issuance date, a bond's coupon rate remains unchanged, and holders of the bond receive fixed interest payments at a predetermined time frequency
	    //A bond issuer decides on the coupon rate based on prevalent market interest rates, among others, at the time of the issuance. Market interest rates change over time, and as they move higher or lower than a bond's coupon rate, the value of the bond increases or decreases, respectively.

	private Integer callable=0;
	/*A callable bond is a debt instrument in which the issuer reserves the right to return the investor's principal and stop interest payments before the bond's maturity date.

	*/
	

	private Double saleprice;
	private Integer convertible=0;
	private String backroundType;
	@Temporal(TemporalType.DATE)
	private Date maturitydate;
	@Temporal(TemporalType.DATE)
	private Date callabilityperiod;
	private Float availablecoupon;
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
	

}
