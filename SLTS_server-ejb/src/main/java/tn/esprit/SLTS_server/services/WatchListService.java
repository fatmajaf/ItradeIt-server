package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.SLTS_server.iservice.WatchListRemote;
import tn.esprit.SLTS_server.persistence.Company;
import tn.esprit.SLTS_server.persistence.Request;
import tn.esprit.SLTS_server.persistence.User;
import tn.esprit.SLTS_server.persistence.WatchList;
import tn.esprit.SLTS_server.persistence.WatchListFolderStatus;
import tn.esprit.SLTS_server.persistence.WatchListPK;
@Stateless
public class WatchListService implements WatchListRemote{

	@PersistenceContext(unitName="SLTS_server-ejb")
	EntityManager em;
	
	@Override
	public void addWatchList(int companyId, int userId, WatchListFolderStatus status) {
		Company c=em.find(Company.class,companyId);
		User u=em.find(User.class,userId);
		WatchListPK wpk=new WatchListPK();
		wpk.setIdCompany(companyId);
		wpk.setIdUser(userId);
		wpk.setWatchListStatus(status);
		WatchList w=new WatchList();
		w.setCompany(c);
		w.setUser(u);
		w.setWatchListPK(wpk);
		em.persist(w);
		
	}

	@Override
	public void deleteWatchList(int companyId, int userId) {
		WatchList w=getWatchList(companyId, userId);
		em.remove(w);
		
	}

	@Override
	public void editWatchList(int companyId, int userId, WatchListFolderStatus status) {
		
		
	}

	@Override
	public WatchList getWatchList(int companyId, int userId) {
		Query query=em.createQuery("select w From WatchList w join w.company c join w.user u where c.id=:param1 and u.id=:param2");
		query.setParameter("param1", companyId);
		query.setParameter("param2", userId);
		return (WatchList)query.getSingleResult();
	}

	@Override
	public List<WatchList> getAllWatchLists() {
		Query query=em.createQuery("select w From WatchList w ");
		return query.getResultList();
	}

	@Override
	public List<WatchList> getWatchListsByStatus(WatchListFolderStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WatchList> getWatchListByUser(int userId) {
		Query query=em.createQuery("select w From WatchList w join w.user u where u.id=:param1 ");
		query.setParameter("param1", userId);
		return query.getResultList();
	}

	@Override
	public List<WatchList> getWatchListByCompany(int companyId) {
		Query query=em.createQuery("select w From WatchList w join w.company c  where c.id=:param1 ");
		query.setParameter("param1", companyId);
		return query.getResultList();
	}

	@Override
	public int getNbreWatchListByUser(int userId) {
		return getWatchListByUser(userId).size();
	}

	@Override
	public int getnbreWatchListByCompany(int companyId) {
		
		return getWatchListByCompany(companyId).size();
	}

	@Override
	public boolean isWatched(int companyId, int userId) {
		Query query=em.createQuery("select count(w) From WatchList w join w.company c join w.user u where c.id=:param1 and u.id=:param2");
		query.setParameter("param1", companyId);
		query.setParameter("param2", userId);
		long nbre=(long)query.getSingleResult();
		if(nbre==0)
			return false;
		else return true;
	}

}
