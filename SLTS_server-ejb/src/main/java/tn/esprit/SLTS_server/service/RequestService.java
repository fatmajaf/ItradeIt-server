package tn.esprit.SLTS_server.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.SLTS_server.iservice.RequestServiceRemote;
import tn.esprit.SLTS_server.persistence.Offer;
import tn.esprit.SLTS_server.persistence.Request;
import tn.esprit.SLTS_server.persistence.RequestPK;
import tn.esprit.SLTS_server.persistence.Trader;

@Stateless
public class RequestService implements RequestServiceRemote {

	@PersistenceContext(unitName="SLTS_server-ejb")
	EntityManager em;
	
	@Override
	public void ajouterRequest(int offId, int traderId, Date date) {
		Offer o=em.find(Offer.class,offId);
		Trader t=em.find(Trader.class,traderId);
		RequestPK rpk=new RequestPK();
		rpk.setIdOffre(offId);
		rpk.setIdTrader(traderId);
		rpk.setDate(date);
		Request request=new Request();
		request.setRequestPk(rpk);
		request.setOffer(o);
		request.setTrader(t);
		request.setAccepted(false);
		em.persist(request);
	}

	@Override
	public Request getRequest(Offer o, Trader t) {
		Query query=em.createQuery("select r From Request where r.offer=:param and r.trader=:param1");
		query.setParameter("param", o);
		query.setParameter("param1", t);
		return (Request)query.getSingleResult();
	}

	@Override
	public Boolean acceptRequest(Offer o, Trader t) {
		Request r=getRequest(o, t);
		r.setAccepted(true);
		em.merge(r);
		return r.isAccepted();
	}

	@Override
	public void refuseRequest(Offer o, Trader t) {
		Request r=getRequest(o, t);
		em.remove(r);
	}
	

	@Override
	public List<Request> getAllRequests(int idTrader) {
		Query query=em.createQuery("select r from Request where r.offer.id:=param");
		query.setParameter("param", idTrader);
		return query.getResultList();
	}

	@Override
	public int getNbreRequest(Trader t) {
		
		return getAllRequests(t.getId()).size();
	}

	

}
