package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
public class Offer implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String type;
	private float price;
	@Column(name="creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	@ManyToOne
	private Trader trader;
	@OneToMany(mappedBy="offer",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Request> requests;
	@OneToMany(mappedBy="offre",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<InteresstedBy> interesstedByList;
	@OneToOne
	private Instrument instrument;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Trader getTrader() {
		return trader;
	}
	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	public List<Request> getRequests() {
		return requests;
	}
	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
	public List<InteresstedBy> getInteresstedByList() {
		return interesstedByList;
	}
	public void setInteresstedByList(List<InteresstedBy> interesstedByList) {
		this.interesstedByList = interesstedByList;
	}
	public Instrument getInstrument() {
		return instrument;
	}
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	
	
	

}
