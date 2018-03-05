package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value="trader")
public class Trader extends User implements Serializable{
	@Column(name="trader_type")
	private String tradertype;
	@Column(name="is_banned")
	private Integer isbanned;
	@OneToMany(mappedBy="trader")
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
	
	
	
	
	

}
