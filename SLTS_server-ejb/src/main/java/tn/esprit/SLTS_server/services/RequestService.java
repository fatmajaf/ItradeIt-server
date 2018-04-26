package tn.esprit.SLTS_server.services;

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
	public void addRequest(int offId, int traderId, Date date) {
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
	public Request getRequest(int o, int t) {
		Query query=em.createQuery("select r From Request r join r.offer o join r.trader t where o.id=:param1 and t.id=:param2");
		query.setParameter("param1", o);
		query.setParameter("param2", t);
		return (Request)query.getSingleResult();
	}

	@Override
	public Boolean acceptRequest(int o, int t) {
		Request r=getRequest(o, t);
		r.setAccepted(true);
		em.merge(r);
		return r.isAccepted();
	}

	@Override
	public void refuseRequest(int o, int t) {
		Request r=getRequest(o, t);
		em.remove(r);
	}
	

	@Override
	public List<Request> getAllRequests(int idTrader) {
		Query query=em.createQuery("select r from Request r join r.offer o join o.trader t where t.id:=param");
		query.setParameter("param", idTrader);
		return query.getResultList();
	}

	@Override
	public int getNbreRequest(int t) {
		
		return getAllRequests(t).size();
	}

	@Override
	public List<Request> getMyRequests(int idTrader) {
		Query query=em.createQuery("select r from Request r join r.trader t where t.id:=param");
		query.setParameter("param", idTrader);
		return query.getResultList();
	}

	@Override
	public int getNbreMyRequest(int traderId) {
		return getMyRequests(traderId).size();
	}

	

}
