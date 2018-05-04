package tn.esprit.SLTS_server.instrumentServices;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tn.esprit.SLTS_server.persistence.Bond;
import tn.esprit.SLTS_server.persistence.Currency;
import tn.esprit.SLTS_server.persistence.Instrument;

import tn.esprit.SLTS_server.persistence.User;


@Stateless
@LocalBean
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
		if (typeOfPayment.equals("Annually"))
			return (double) 1;
		if (typeOfPayment.equals("Semi Annually"))
			return (double) 2;
		if (typeOfPayment.equals("Quarterly"))
			return (double) 4;
		if (typeOfPayment.equals("Monthly"))
			return (double) 12;
		if (typeOfPayment.equals("Bi Annual"))
			return 0.5;
		return (double) 0;

	}

	@Override
	public float calculFutureValue(Double ammount, Double interestRate, Integer numberOfYears, String typeOfPayment) {
		interestRate = interestRate / 100;
		Double alpha = getTypeOfPaymentInteger(typeOfPayment);
		Double beta = alpha * numberOfYears;
		Double gamma = (1 + interestRate / alpha);System.out.println("hseb7a");
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

	public List<Currency> getCurrencies() {
		Query query= em.createQuery("select c from Currency c");
		return (List<Currency>) query.getResultList();
	}
	
	
	/**Yield until maturity *****/
	public Float calculateBondPriceToMaturityDate(Float parValue,Float futureValue,int maturityPeriod ,Float interestRate){
		Float alpha=(float) (1/Math.pow((1+(interestRate/100)),maturityPeriod));
		Float yield=(parValue-futureValue*alpha)*((interestRate/100)/(1-alpha));
		System.out.println(yield);
		return yield;
	}
	/**Yield to call*****/
	public Float calculateBondPriceCallableAndPuttable(Float parValue,Float futureValue,int periodCallOrPut ,Float interestRate){
		Float alpha=(float) (1/Math.pow((1+(interestRate/100)),periodCallOrPut));
		Float yield=(parValue-futureValue*alpha)/((1-alpha)/(interestRate/100));
		System.out.println(yield);
		return yield;
	}
	@Override
	public  float currencyConverter(String from,String to , Double price) throws IOException{
		// Setting URL
					String url_str = "https://v3.exchangerate-api.com/bulk/31499df34d95d359397c9efd/";
					
					// Making Request
					String urlNew =url_str+from;
					URL url = new URL(urlNew);
					HttpURLConnection request = (HttpURLConnection) url.openConnection();
					request.connect();

					// Convert to JSON
					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					JsonObject jsonobj = root.getAsJsonObject();

					// Accessing object
					String req_result = jsonobj.get("result").getAsString();
					String req_result2 = jsonobj.get("timestamp").getAsString();
					String req_result3 = jsonobj.get("from").getAsString();
					JsonObject req_result4 = jsonobj.get("rates").getAsJsonObject();

					System.out.println(jsonobj);
					float result=(float) (req_result4.get(to).getAsDouble()*price);
					System.out.println(result);
					JsonParser parser = new JsonParser(); 
					JsonElement mJson =  parser.parse(jsonobj.toString());
					//Gson gson = new Gson();
					//MyDataObject object = gson.fromJson(mJson, MyDataObject.class);
					//System.out.println(object);
					return result;
	}
	
	@Override
	public Long nbrBondsByCustomer(Integer idu){
		//return em.createQuery("SELECT count(b) FROM Instrument b where b.instrumentIssuer.customer.id=:idu",Integer.class).setParameter("idu", idu).getSingleResult();
		
		return em.createQuery("SELECT count(*) FROM Instrument b where b.InstrumentIssuer.id=:idu",Long.class).setParameter("idu", idu).getSingleResult();

	
	}
	@Override
	public Long nbrBondsByType(String type){
		return em.createQuery("SELECT count(*) FROM Instrument b where b.typeOfCouponPayment=:type",Long.class).setParameter("type", type).getSingleResult();
	}

}
