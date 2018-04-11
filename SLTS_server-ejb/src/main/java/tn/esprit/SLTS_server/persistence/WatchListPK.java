package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class WatchListPK implements Serializable{
	
	private Integer idCompany;
	private Integer idUser;
	private WatchListFolderStatus WatchListStatus;
	
	
	public Integer getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idFolder) {
		this.idUser = idFolder;
	}
	public WatchListFolderStatus getWatchListStatus() {
		return WatchListStatus;
	}
	public void setWatchListStatus(WatchListFolderStatus watchListStatus) {
		WatchListStatus = watchListStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((WatchListStatus == null) ? 0 : WatchListStatus.hashCode());
		result = prime * result + idCompany;
		result = prime * result + idUser;
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
		WatchListPK other = (WatchListPK) obj;
		if (WatchListStatus != other.WatchListStatus)
			return false;
		if (idCompany != other.idCompany)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
	
	
	
	
}
