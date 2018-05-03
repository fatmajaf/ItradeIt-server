package tn.esprit.SLTS_server.instrumentServices;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.Bond;
import tn.esprit.SLTS_server.persistence.Currency;
import tn.esprit.SLTS_server.persistence.Instrument;
import tn.esprit.SLTS_server.persistence.User;

@Remote
public interface InstrumentServiceRemote {
	public void create(Instrument instrument);

	List<Instrument> findAll();

	public void delete(Integer id);

	public void updatee(Integer id, float changee);

	public Instrument find(Integer id);

	void InstrumentUser(int instrumentId, int userId);

	List<String> findAllCustomers();

	public List<Bond> findAllBonds();

	Integer calculNumberOfYears(Date callabilityPeriod, Date startDate);

	float calculFutureValue(Double double1, Double double2, Integer numberOfYears, String typeOfPayment);

	Double getTypeOfPaymentInteger(String typeOfPayment);

	float calculPrincipleValue(Double ammount, Double interestRate, Integer numberOfYears, String typeOfPayment);

	String concatCurrencyConversion(String firstValue, String secondValue);

	public int getNbEu(String string);

	public List<Currency> getCurrencies();
	public Float calculateBondPriceToMaturityDate(Float parValue,Float futureValue,int maturityPeriod ,Float interestRate);

	public  float currencyConverter(String from,String to , Double price) throws IOException;
	public Long nbrBondsByCustomer(Integer idu);

}
