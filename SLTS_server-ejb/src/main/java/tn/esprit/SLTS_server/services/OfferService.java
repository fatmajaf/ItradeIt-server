package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.SLTS_server.iservice.OfferServiceRemote;
import tn.esprit.SLTS_server.persistence.Instrument;
import tn.esprit.SLTS_server.persistence.Offer;
import tn.esprit.SLTS_server.persistence.Trader;

@Stateless
public class OfferService implements OfferServiceRemote {
	@PersistenceContext(unitName="SLTS_server-ejb")
	EntityManager em;
	
	@Override
	public int addOffre(Offer o,int traderId,int isntId) {
		Trader t=em.find(Trader.class,traderId);
		Instrument i=em.find(Instrument.class, isntId);
		o.setTrader(t);
		o.setInstrument(i);
		em.persist(o);
		return o.getId();
	}

	@Override
	public void editOffre(Offer o) {
		em.merge(o);
		
		
	}

	@Override
	public void deleteOffre(int offId) {
		Offer o=em.find(Offer.class,offId);
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
		Query query=em.createQuery("select o From Offer o join o.trader t where t.id=:param");
		query.setParameter("param",traderId);
		return query.getResultList();
	}

	@Override
	public Offer getOffer(int offerId) {
		Query query=em.createQuery("select o From Offer o where id=:param");
		query.setParameter("param",offerId);
		return (Offer)query.getSingleResult();
	}

	@Override
	public int addOffre(Offer o, int traderId) {
		Trader t=em.find(Trader.class,traderId);
		o.setTrader(t);
		em.persist(o);
		return o.getId();
	}
	
	
	

}
