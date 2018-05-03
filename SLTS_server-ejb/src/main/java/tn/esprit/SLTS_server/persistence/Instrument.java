package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorColumn(name="typeinstrument")
public class Instrument implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String currency;
	//private List<Currency> currency;
	private float changee;
	private float highh;
	private float volume;
	private float open;
	private int available=1;
	
	@Column(unique=true)
	private String Reference; 
	//@OneToOne(mappedBy="instrument")
	//private Offer offer;

	
    private boolean confirmed=false;
	@ManyToOne
	private Customer InstrumentIssuer;
	
	
	private String InstrumentIssuerType;
	




	/*public List<Currency> getCurrency() {
		return currency;
	}



	public void setCurrency(List<Currency> currency) {
		this.currency = currency;
	}
*/

	
	public Instrument() {
		super();
	}



	public Instrument(String currency, Customer instrumentIssuer, String instrumentIssuerType) {
		super();
		
		this.currency = currency;
		InstrumentIssuer = instrumentIssuer;
		InstrumentIssuerType = instrumentIssuerType;
	}



	public Instrument(String currency, float changee, float highh, float volume, float open, int available,
			String reference, boolean confirmed) {
		super();
		this.currency = currency;
		this.changee = changee;
		this.highh = highh;
		this.volume = volume;
		this.open = open;
		this.available = available;
		Reference = reference;
		this.confirmed = confirmed;
	}



	public Integer getId() {
		return id;
	}
	
	
	
	/*
	
	public boolean isConfirmed() {
		return confirmed;
	}





	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}





	public User getInstrumentIssuer() {
		return InstrumentIssuer;
	}





	public void setInstrumentIssuer(User instrumentIssuer) {
		InstrumentIssuer = instrumentIssuer;
	}





	public String getInstrumentIssuerType() {
		return InstrumentIssuerType;
	}





	public void setInstrumentIssuerType(String instrumentIssuerType) {
		InstrumentIssuerType = instrumentIssuerType;
	}


*/


	public String getReference() {
		return Reference;
	}



	public void setReference(String reference) {
		Reference = reference;
	}



/*	public Offer getOffer() {
		return offer;
	}



	public void setOffer(Offer offer) {
		this.offer = offer;
	}

*/

	@Override
	public String toString() {
		return "Instrument [id=" + id + ", currency=" + currency + ", changee=" + changee + ", highh=" + highh
				+ ", volume=" + volume + ", open=" + open + ", available=" + available + ", confirmed=" + confirmed
				+ ", InstrumentIssuer=" + InstrumentIssuer + ", InstrumentIssuerType=" + InstrumentIssuerType + "]";
	}



	public boolean isConfirmed() {
		return confirmed;
	}



	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}



	public User getInstrumentIssuer() {
		return InstrumentIssuer;
	}



	public void setInstrumentIssuer(Customer instrumentIssuer) {
		InstrumentIssuer = instrumentIssuer;
	}



	public String getInstrumentIssuerType() {
		return InstrumentIssuerType;
	}



	public void setInstrumentIssuerType(String instrumentIssuerType) {
		InstrumentIssuerType = instrumentIssuerType;
	}



	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public float getChangee() {
		return changee;
	}
	public void setChangee(float changee) {
		this.changee = changee;
	}
	public float getHighh() {
		return highh;
	}
	public void setHighh(float highh) {
		this.highh = highh;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public float getOpen() {
		return open;
	}
	public void setOpen(float open) {
		this.open = open;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
		

}
