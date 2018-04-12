package tn.esprit.SLTS_server.iservice;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.WatchList;
import tn.esprit.SLTS_server.persistence.WatchListFolderStatus;

@Remote
public interface WatchListRemote {
	
	void addWatchList(int companyId,int userId,WatchListFolderStatus status);
	void deleteWatchList(int companyId,int userId);
	void editWatchList(int companyId,int userId,WatchListFolderStatus status);
	WatchList getWatchList(int companyId,int userId);
	List<WatchList> getAllWatchLists();
	List<WatchList> getWatchListsByStatus(WatchListFolderStatus status);
	List<WatchList> getWatchListByUser(int userId);
	List<WatchList> getWatchListByCompany(int companyId);
	int getNbreWatchListByUser(int userId);
	int getnbreWatchListByCompany(int companyId);

}
