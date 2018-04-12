package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@DiscriminatorValue(value="customer")
public class Customer extends User implements Serializable{
	
	private Integer risk=0;
	@ManyToOne
	private Trader trader;
	private Double money;
	@OneToOne(cascade=CascadeType.ALL)
	private Company company;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="customer",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private List<InteresstedBy> interesstedByList;
	
	public Integer getRisk() {
		return risk;
	}
	public void setRisk(Integer risk) {
		this.risk = risk;
	}
	public Trader getTrader() {
		return trader;
	}
	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return super.getFirstName()+" "+super.getLastName();
	}
	public List<InteresstedBy> getInteresstedByList() {
		return interesstedByList;
	}
	public void setInteresstedByList(List<InteresstedBy> interesstedByList) {
		this.interesstedByList = interesstedByList;
	}
	
	
	

}
