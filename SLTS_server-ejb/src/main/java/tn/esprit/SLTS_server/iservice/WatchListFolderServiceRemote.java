package tn.esprit.SLTS_server.iservice;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.WatchListFolder;
import tn.esprit.SLTS_server.persistence.WatchListFolderStatus;

@Remote
public interface WatchListFolderServiceRemote {

	Integer createWatchListFolder(WatchListFolder watchListFolder);
	void affectWatchListFolderToUser(Integer watchId,Integer userId);
	void deleteWatchListFolder(Integer id);
	void updateWatchListFolder(WatchListFolder watchListFolder);
	WatchListFolder getWatchListFolder(Integer id);
	WatchListFolder getWatchListFolderByStatus(WatchListFolderStatus status);
	List<WatchListFolder> getAllWatchListFolder();
	int getNbrWatchListFolder();
	Boolean haveWatchListFolder(Integer idUser);
	Boolean haveWatchListFolderByStatus(Integer idUser,WatchListFolderStatus status);
	
	
}
