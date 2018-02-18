package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Instrument implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String currency;
	private Float change;
	private Float high;
	private Float volume;
	private Float open;
	private int available=1;
	
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
	public Float getChange() {
		return change;
	}
	public void setChange(Float change) {
		this.change = change;
	}
	public Float getHigh() {
		return high;
	}
	public void setHigh(Float high) {
		this.high = high;
	}
	public Float getVolume() {
		return volume;
	}
	public void setVolume(Float volume) {
		this.volume = volume;
	}
	public Float getOpen() {
		return open;
	}
	public void setOpen(Float open) {
		this.open = open;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	

}
