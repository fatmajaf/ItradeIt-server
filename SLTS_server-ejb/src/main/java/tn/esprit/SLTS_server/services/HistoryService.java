package tn.esprit.SLTS_server.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.SLTS_server.iservice.HistoryServiceRemote;
import tn.esprit.SLTS_server.persistence.Company;
import tn.esprit.SLTS_server.persistence.History;

@Stateless
public class HistoryService implements HistoryServiceRemote{

	@PersistenceContext(unitName="SLTS_server-ejb")
	EntityManager em;
	
	@Override
	public Integer addHistory(History h) {
		em.persist(h);
		return h.getId();
	}

	@Override
	public List<History> getAllHistory() {
		Query query=em.createQuery("select h From History h");
		return query.getResultList();
	}

	@Override
	public History getHistory(Integer id) {
		return em.find(History.class,id);
	}

	@Override
	public List<History> getHistoryByCompany(Integer companyId) {
		Query query=em.createQuery("select h From History h join h.company c where c.id=:param");
		query.setParameter("param", companyId);
		return query.getResultList();
	}

	@Override
	public List<History> getHistoryByCompanyAndDate(Integer companyId, Date d) {
		Query query=em.createQuery("select h From History h join h.company c where c.id=:param and h.date=:param1");
		query.setParameter("param", companyId);
		query.setParameter("param1", d);
		return query.getResultList();
	}

	@Override
	public void affectHistoryToCompany(Integer historyId, Integer companyId) {
		Company c=em.find(Company.class,companyId);
		History h=em.find(History.class,historyId);
		h.setCompany(c);
		
	}

}
