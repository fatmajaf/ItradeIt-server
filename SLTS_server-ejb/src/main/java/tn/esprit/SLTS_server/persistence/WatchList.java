package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class WatchList implements Serializable{

	@EmbeddedId
	private WatchListPK watchListPK;
	@ManyToOne
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="idCompany",referencedColumnName="id",insertable=false,updatable=false)
	private Company company;
	
	public WatchListPK getWatchListPK() {
		return watchListPK;
	}
	public void setWatchListPK(WatchListPK watchListPK) {
		this.watchListPK = watchListPK;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
	
	

}
