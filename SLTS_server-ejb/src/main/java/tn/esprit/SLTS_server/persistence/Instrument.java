package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
@DiscriminatorColumn(name="typeinstrument")
public class Instrument implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String currency;
	private float changee;
	private float highh;
	private float volume;
	private float open;
	private int available=1;
	
    private boolean confirmed=false;
	@ManyToOne
	private Customer InstrumentIssuer;
	
	
	private String InstrumentIssuerType;
	
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
