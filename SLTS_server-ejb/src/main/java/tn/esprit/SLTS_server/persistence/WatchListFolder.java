package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class WatchListFolder {

	
	private Integer id;
	private String name;
	private WatchListFolderStatus folderStatus;
	//@OneToMany(mappedBy="folder")
	private List<WatchList> watchLists;
	
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WatchListFolderStatus getFolderStatus() {
		return folderStatus;
	}
	public void setFolderStatus(WatchListFolderStatus folderStatus) {
		this.folderStatus = folderStatus;
	}
	public List<WatchList> getWatchLists() {
		return watchLists;
	}
	public void setWatchLists(List<WatchList> watchLists) {
		this.watchLists = watchLists;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
