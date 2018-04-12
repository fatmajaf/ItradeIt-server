package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@DiscriminatorValue(value = "trader")
public class Trader extends User implements Serializable {
	@Column(name = "trader_type")
	private String tradertype;
	@Column(name = "is_banned")
	private Integer isbanned;
	@OneToMany(mappedBy = "trader", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	// @LazyCollection(LazyCollectionOption.FALSE)
	private List<Offer> offers;
	@OneToMany(mappedBy = "trader", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Request> requests;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany( mappedBy = "trader")
	private List<Customer> customers;

	public String getTradertype() {
		return tradertype;
	}

	public void setTradertype(String tradertype) {
		this.tradertype = tradertype;
	}

	public Integer getIsbanned() {
		return isbanned;
	}

	public void setIsbanned(Integer isbanned) {
		this.isbanned = isbanned;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

}
