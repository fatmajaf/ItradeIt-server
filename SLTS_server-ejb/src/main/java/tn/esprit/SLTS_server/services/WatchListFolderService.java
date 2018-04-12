package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.SLTS_server.iservice.WatchListFolderServiceRemote;
import tn.esprit.SLTS_server.persistence.User;
import tn.esprit.SLTS_server.persistence.WatchListFolder;
import tn.esprit.SLTS_server.persistence.WatchListFolderStatus;

@Stateless
public class WatchListFolderService implements WatchListFolderServiceRemote{
	
	@PersistenceContext(unitName="SLTS_server-ejb")
	EntityManager em;
	

	@Override
	public Integer createWatchListFolder(WatchListFolder watchListFolder) {
		em.persist(watchListFolder);
		return watchListFolder.getId();
	}

	@Override
	public void deleteWatchListFolder(Integer id) {
		WatchListFolder wlf=em.find(WatchListFolder.class,id);
		em.remove(wlf);
		
	}

	@Override
	public void updateWatchListFolder(WatchListFolder watchListFolder) {
		em.merge(watchListFolder);
		
	}

	@Override
	public WatchListFolder getWatchListFolder(Integer id) {
		
		return  em.find(WatchListFolder.class,id);
	}

	@Override
	public WatchListFolder getWatchListFolderByStatus(WatchListFolderStatus status) {
		Query query=em.createQuery("select w From WatchListFolder w where w.folderStatus=:param");
		query.setParameter("param",status);
		return (WatchListFolder) query.getSingleResult();
	}

	@Override
	public List<WatchListFolder> getAllWatchListFolder() {
		Query query=em.createQuery("select w From WatchListFolder w");
		return query.getResultList();
	}

	@Override
	public int getNbrWatchListFolder() {

		return getAllWatchListFolder().size();
	}

	@Override
	public Boolean haveWatchListFolder(Integer idUser) {

		Query query=em.createQuery("select count(w) From WatchListFolder w where user.id=:param");
		query.setParameter("param",idUser);
		long res= (long) query.getSingleResult();
		if (res==0)
		return false;
		else return true;
	}

	@Override
	public Boolean haveWatchListFolderByStatus(Integer idUser, WatchListFolderStatus status) {
		Query query=em.createQuery("select count(w) From WatchListFolder w where w.folderStatus=:param and user.id=:param1");
		query.setParameter("param",status);
		query.setParameter("param1",idUser);
		long res= (long) query.getSingleResult();
		if (res==0)
		return false;
		else return true;
	}

	@Override
	public void affectWatchListFolderToUser(Integer watchId, Integer userId) {
		User u=em.find(User.class,userId);
		WatchListFolder w=em.find(WatchListFolder.class,watchId);
		w.setUser(u);
		
	}
	

}
