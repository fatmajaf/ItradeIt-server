package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InteresstedBy implements Serializable {
	
	@EmbeddedId
	private InteresstedByPK interesstedByPk;
	private Integer note;
	
	@ManyToOne
	@JoinColumn(name="idClient",referencedColumnName="id",insertable=false,updatable=false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="idOffre",referencedColumnName="id",insertable=false,updatable=false)
	private Offer offre;

	public InteresstedByPK getInteresstedByPk() {
		return interesstedByPk; 
	}

	public void setInteresstedByPk(InteresstedByPK interesstedByPk) {
		this.interesstedByPk = interesstedByPk;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Offer getOffre() {
		return offre;
	}

	public void setOffre(Offer offre) {
		this.offre = offre;
	}
	
	

}
