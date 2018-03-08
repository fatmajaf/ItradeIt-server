package tn.esprit.SLTS_server.instrumentServices;



import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.SLTS_server.persistence.Bond;
import tn.esprit.SLTS_server.persistence.Instrument;
import tn.esprit.SLTS_server.persistence.User;


@Stateless
public class InstrumentService implements InstrumentServiceRemote,InstrumentServiceLocal {
	
	@PersistenceContext(unitName="SLTS_server-ejb")
	private EntityManager em;
	
		
	public InstrumentService() {
		
	}
	
	@Override
	public void create(Instrument instrument) {
		//((Bond)instrument).setAvailablecoupon(((Bond) instrument).calculCouponAvailable());
		
		em.persist(instrument);
		em.refresh(instrument);
	
	}
	@Override
	public List<Instrument> findAll() {
		return em.createQuery("from Instrument", Instrument.class).getResultList();
	}
	@Override
	public void delete(Integer id) {
		em.remove(em.find(Instrument.class,id));
	}
	@Override
		public void updatee(Integer id,float changee){
		System.out.println("aaa");
		Instrument inst=em.find(Instrument.class,id);
		System.out.println("aaa");
		inst.setChangee(changee);
		em.flush();
				
	}
	
	@Override
	public Instrument find(Integer id){
		Instrument inst=em.find(Instrument.class,id);
		return inst;
		
	}
	@Override
	public void InstrumentUser(int instrumentId,int userId){
		User user=em.find(User.class, userId);
		Instrument inst=em.find(Instrument.class, instrumentId);
	
		
	}
	
	/*@Override
	public Long getNbBannedTraders() {
		String jpql = "SELECT count(u) FROM Trader u where u.isbanned=1";
		Query query = em.createQuery(jpql);
		return (Long) query.getSingleResult();
	}*/

	@Override
	public List<String> findAllCustomers() {
		return em.createNativeQuery("select e.login from User e", User.class).getResultList();
	}
	
	@Override
	public List<Bond> findAllBonds() {
		return em.createQuery("select B from Bond B", Bond.class).getResultList();
	}
	
	
}
