package tn.esprit.SLTS_server.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.SLTS_server.iservice.OfferServiceRemote;
import tn.esprit.SLTS_server.persistence.Offer;
import tn.esprit.SLTS_server.persistence.Trader;

@Stateless
public class OfferService implements OfferServiceRemote {
	@PersistenceContext(unitName="SLTS_server-ejb")
	EntityManager em;
	
	@Override
	public int addOffre(Offer o) {
		em.persist(o);
		return o.getId();
	}

	@Override
	public void editOffre(Offer o) {
		em.merge(o);
		
		
	}

	@Override
	public void deleteOffre(Offer o) {
		em.remove(o);
		
	}

	@Override
	public void affectTraderToOffre(Integer offId, Integer tradId) {
		Trader t=em.find(Trader.class,tradId);
		Offer o=em.find(Offer.class,offId);
		o.setTrader(t);
		
	}

	@Override
	public List<Offer> getAllOffres() {
		Query query=em.createQuery("select o From Offer o");
		List<Offer> lst=query.getResultList();
		return lst;
	}

	@Override
	public List<Offer> getOffresByTrader(int traderId) {
		Query query=em.createQuery("select o From Offer o where trader.id=:traderId");
		List<Offer> lst=query.getResultList();
		return lst;
	}
	
	
	

}
