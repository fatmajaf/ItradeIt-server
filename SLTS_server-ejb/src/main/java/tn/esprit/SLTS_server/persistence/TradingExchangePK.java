package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TradingExchangePK implements Serializable {
	@Column(name = "id_instrument")
	private Integer idInstrument;
	@Column(name = "id_user")
	private Integer idUser;

	public Integer getIdInstrument() {
		return idInstrument;
	}

	public void setIdInstrument(Integer idInstrument) {
		this.idInstrument = idInstrument;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idInstrument == null) ? 0 : idInstrument.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradingExchangePK other = (TradingExchangePK) obj;
		if (idInstrument == null) {
			if (other.idInstrument != null)
				return false;
		} else if (!idInstrument.equals(other.idInstrument))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}

}
