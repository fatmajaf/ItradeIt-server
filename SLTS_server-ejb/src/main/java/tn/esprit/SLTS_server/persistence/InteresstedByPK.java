package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class InteresstedByPK implements Serializable{
	private int idClient;
	private int idOffre;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idClient;
		result = prime * result + idOffre;
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
		InteresstedByPK other = (InteresstedByPK) obj;
		if (idClient != other.idClient)
			return false;
		if (idOffre != other.idOffre)
			return false;
		return true;
	}
	
	

}
