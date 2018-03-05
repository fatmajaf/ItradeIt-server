package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Request implements Serializable{
	
	@EmbeddedId
	private RequestPK requestPk;
	@Column(name="accepted", columnDefinition = "TINYINT(1)", length=1)
	private boolean accepted=false;
	
	@ManyToOne
	@JoinColumn(name="idOffre",referencedColumnName="id",insertable=false,updatable=false)
	private Offer offer;
	
	@ManyToOne
	@JoinColumn(name="idTrader",referencedColumnName="id",insertable=false,updatable=false)
	private Trader trader;

	public RequestPK getRequestPk() {
		return requestPk;
	}

	public void setRequestPk(RequestPK requestPk) {
		this.requestPk = requestPk;
	}

	public Boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	
	

}
