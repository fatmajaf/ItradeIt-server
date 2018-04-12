package tn.esprit.SLTS_server.instrumentServices;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class InstrumentService implements InstrumentServiceRemote, InstrumentServiceLocal {

	@PersistenceContext(unitName = "SLTS_server-ejb")
	private EntityManager em;

	public InstrumentService() {

	}

	@Override
	public void create(Instrument instrument) {
		// ((Bond)instrument).setAvailablecoupon(((Bond)
		// instrument).calculCouponAvailable());

		em.persist(instrument);
		em.refresh(instrument);

	}

	@Override
	public List<Instrument> findAll() {
		return em.createQuery("from Instrument", Instrument.class).getResultList();
	}

	@Override
	public void delete(Integer id) {
		em.remove(em.find(Instrument.class, id));
	}

	@Override
	public void updatee(Integer id, float changee) {
		System.out.println("aaa");
		Instrument inst = em.find(Instrument.class, id);
		System.out.println("aaa");
		inst.setChangee(changee);
		em.flush();

	}

	@Override
	public Instrument find(Integer id) {
		Instrument inst = em.find(Instrument.class, id);
		return inst;

	}

	@Override
	public void InstrumentUser(int instrumentId, int userId) {
		User user = em.find(User.class, userId);
		Instrument inst = em.find(Instrument.class, instrumentId);

	}

	/*
	 * @Override public Long getNbBannedTraders() { String jpql =
	 * "SELECT count(u) FROM Trader u where u.isbanned=1"; Query query =
	 * em.createQuery(jpql); return (Long) query.getSingleResult(); }
	 */

	@Override
	public List<String> findAllCustomers() {
		return em.createNativeQuery("select e.login from User e", User.class).getResultList();
	}

	@Override
	public List<Bond> findAllBonds() {
		return em.createQuery("select B from Bond B", Bond.class).getResultList();
	}

	@Override
	public Integer calculNumberOfYears(Date maturityPeriod, Date startDate) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
		Integer.parseInt(simpleDateformat.format(startDate));

		return Integer.parseInt(simpleDateformat.format(maturityPeriod))
				- Integer.parseInt(simpleDateformat.format(startDate));

	}

	@Override
	public Double getTypeOfPaymentInteger(String typeOfPayment) {
		if (typeOfPayment.equals("annually"))
			return (double) 1;
		if (typeOfPayment.equals("semi annually"))
			return (double) 2;
		if (typeOfPayment.equals("quarterly"))
			return (double) 4;
		if (typeOfPayment.equals("monthly"))
			return (double) 12;
		if (typeOfPayment.equals("bi annual"))
			return 0.5;
		return (double) 0;

	}

	@Override
	public float calculFutureValue(Double ammount, Double interestRate, Integer numberOfYears, String typeOfPayment) {
		interestRate = interestRate / 100;
		Double alpha = getTypeOfPaymentInteger(typeOfPayment);
		Double beta = alpha * numberOfYears;
		Double gamma = (1 + interestRate / alpha);
		return (float) (ammount * Math.pow(gamma, beta));
	}

	@Override
	public float calculPrincipleValue(Double ammount, Double interestRate, Integer numberOfYears,
			String typeOfPayment) {
		Double alpha = getTypeOfPaymentInteger(typeOfPayment);
		interestRate = interestRate / 100;
		Double beta = alpha * numberOfYears;
		
		if (alpha != 0) {
						Double gamma = (1 + interestRate / alpha);
			return (float) (ammount * (1 / (Math.pow(gamma, beta))));
		} else {
			
			Double x=Math.exp(interestRate*numberOfYears);
			return (float) (ammount/x);
		}
	}

	@Override
	public String concatCurrencyConversion(String firstValue, String secondValue) {
		return secondValue + secondValue;

	}
	
	public int getNbEu(String string){
		return 0;
		
	}

}
