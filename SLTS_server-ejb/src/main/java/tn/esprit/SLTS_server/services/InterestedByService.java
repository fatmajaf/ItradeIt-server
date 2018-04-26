package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.client.Client;

import tn.esprit.SLTS_server.iservice.InterestedByServiceRemote;
import tn.esprit.SLTS_server.persistence.Customer;
import tn.esprit.SLTS_server.persistence.InteresstedBy;
import tn.esprit.SLTS_server.persistence.InteresstedByPK;
import tn.esprit.SLTS_server.persistence.Offer;
import tn.esprit.SLTS_server.persistence.Request;

@Stateless
public class InterestedByService implements InterestedByServiceRemote {
	
	@PersistenceContext(unitName="SLTS_server-ejb")
	EntityManager em;
	
	@Override
	public InteresstedBy getInteresstedBy(int offId, int clientId) {
		Query query=em.createQuery("select i From InteresstedBy i join i.offre o join i.customer c where o.id=:param1 and c.id=:param2");
		query.setParameter("param1", offId);
		query.setParameter("param2", clientId);
		return (InteresstedBy)query.getSingleResult();
	}

	@Override
	public void addInterestedBy(int offId, int clientId, int note) {

		Offer offer=em.find(Offer.class,offId);
		Customer customer=em.find(Customer.class, clientId);
		InteresstedByPK ipk=new InteresstedByPK();
		ipk.setIdClient(clientId);
		ipk.setIdOffre(offId);
		InteresstedBy intBy=new InteresstedBy();
		intBy.setInteresstedByPk(ipk);
		intBy.setOffre(offer);
		intBy.setCustomer(customer);
		intBy.setNote(note);
		em.persist(intBy);
	}

	@Override
	public void deleteInterestedBy(int offId, int clientId) {
		InteresstedBy interesstedBy=getInteresstedBy(offId, clientId);
		em.remove(interesstedBy);
		
	}

	@Override
	public void updateNote(int offId, int clientId, int note) {
		InteresstedBy interesstedBy=getInteresstedBy(offId, clientId);
		interesstedBy.setNote(note);
		em.merge(interesstedBy);
	}

	@Override
	public Boolean isInterestedBy(int offId, int clientId) {
		Query query=em.createQuery("select count(i) From InteresstedBy i join i.offre o join i.customer c where o.id=:param1 and c.id=:param2");
		query.setParameter("param1", offId);
		query.setParameter("param2", clientId);
		long nbre=(long)query.getSingleResult();
		if(nbre==0)
			return false;
		else return true;
		
	}

	@Override
	public List<InteresstedBy> getAllInterestedBy(int traderId) {
		Query query=em.createQuery("select i from InteresstedBy i join i.customer c join c.trader t where t.id:=param");
		query.setParameter("param", traderId);
		return query.getResultList();
	}

	@Override
	public List<InteresstedBy> getInterestedByClient(int clientId) {
		Query query=em.createQuery("select i from InteresstedBy i join i.customer c where c.id:=param");
		query.setParameter("param", clientId);
		return query.getResultList();
	}

	@Override
	public List<InteresstedBy> getMyInterestedByLevel(int note,int traderId) {
		Query query=em.createQuery("select i from InteresstedBy i join i.customer c where c.id:=param and i.note=param1");
		query.setParameter("param", traderId);
		query.setParameter("param1", note);
		return query.getResultList();
	}

	@Override
	public float getMoy(int clientId,int offerId) {
		Query query=em.createQuery("select avg(i.note) from InteresstedBy i join i.customer c join i.offre o where c.id:=param and o.id:=param1");
		query.setParameter("param", clientId);
		query.setParameter("param1", offerId);
		return (float)query.getSingleResult();
	}

	
	

}
