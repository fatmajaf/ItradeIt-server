package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
@Entity
@DiscriminatorValue(value="customer")
public class Customer extends User implements Serializable{
	
	private Integer risk=0;
	
	public Integer getRisk() {
		return risk;
	}
	public void setRisk(Integer risk) {
		this.risk = risk;
	}
	

}
