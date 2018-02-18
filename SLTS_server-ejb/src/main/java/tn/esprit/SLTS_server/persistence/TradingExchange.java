package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TradingExchange implements Serializable {

	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	private Integer status;
	@EmbeddedId
	private TradingExchangePK rolePK;
	@ManyToOne
	@JoinColumn(name = "id_instrument", referencedColumnName = "id", insertable = false, updatable = false)
	private Instrument instrument;
	@ManyToOne
	@JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public TradingExchangePK getRolePK() {
		return rolePK;
	}

	public void setRolePK(TradingExchangePK rolePK) {
		this.rolePK = rolePK;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
