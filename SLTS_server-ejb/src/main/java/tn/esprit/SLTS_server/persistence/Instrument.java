package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Instrument implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String currency;
	private float changee;
	private float high;
	private float volume;
	private float open;
	private int available=1;
	private String confirmed;
	
	
	@ManyToOne
	private User InstrumentIssuer;
	
	
	private String InstrumentIssuerType;
	//Issuers may be corporations, investment trusts, or domestic or foreign governments. 
	
	public Instrument(){
		
	}
	public Instrument(String currency,float d,float e,float f,float g,int available){
		this.currency=currency;
		this.changee=d;
		this.high=e;
		this.volume=f;
		this.open=g;
		this.available=available;
	}
	public Integer getId() {
		return id;
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
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
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
	public String toString(){
		return "id="+id+",currency:"+currency+",change:"+changee;
		
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
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	

}
