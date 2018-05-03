package tn.esprit.SLTS_server.presentation.mbeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tn.esprit.SLTS_server.persistence.Bond;
import tn.esprit.SLTS_server.persistence.Customer;
import tn.esprit.SLTS_server.persistence.Trader;
import tn.esprit.SLTS_server.services.UserServiceLocal;

import tn.esprit.SLTS_server.instrumentServices.InstrumentServiceRemote;

@ManagedBean
@SessionScoped
public class BondManagementBean {

	private Double parvalue;

	private Double couponrate;

	private Integer callable = 0;

	private Double saleprice;

	private Integer convertible = 0;
	private String backroundType;
	@Temporal(TemporalType.DATE)
	private Date maturitydate;
	@Temporal(TemporalType.DATE)
	private Date callabilityperiod;
	private Float availablecoupon;
	private String typeOfCouponPayment;
	private Date dateOfPublication;

	private Integer id;
	private String currency;
	private Customer InstrumentIssuer;
	private String InstrumentIssuerType;
	private List<Customer> customers;

	private Date datetoday;
	private Double ammount;
	private boolean pricingVar;
	
	private boolean callableTest;
	private boolean convertibleTest;
	private Double x = (Double) 0.0;

	private List<Bond> bonds;

	private Integer instrumentIssuerID;
	
	private Float parValueNew;
	private Float annualRate;
	private int maturityInYears;
	private Float marketValue;
	
	private Float yieldToMaturity;
	private Customer c;
	
	
	private String from;
	private String to;
	private Double soumLekher;
	private Double price;
	private String customerEmail;
	private Integer customerPhone;
	private Long nbrBondsTraded;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Double getSoumLekher() {
		return soumLekher;
	}

	public void setSoumLekher(Double soumLekher) {
		this.soumLekher = soumLekher;
	}

	public Float getAnnualRate() {
		return annualRate;
	}

	public void setAnnualRate(Float annualRate) {
		this.annualRate = annualRate;
	}

	public int getMaturityInYears() {
		return maturityInYears;
	}

	public void setMaturityInYears(int maturityInYears) {
		this.maturityInYears = maturityInYears;
	}

	public Float getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Float marketValue) {
		this.marketValue = marketValue;
	}

	@EJB
	InstrumentServiceRemote instrumentServiceRemote;

	@EJB
	UserServiceLocal userServiceLocal;

	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	public Double getParvalue() {
		return parvalue;
	}

	public void setParvalue(Double parvalue) {
		this.parvalue = parvalue;
	}

	public Double getCouponrate() {
		return couponrate;
	}

	public void setCouponrate(Double couponrate) {
		this.couponrate = couponrate;
	}

	public Integer getCallable() {
		return callable;
	}

	public void setCallable(Integer callable) {
		this.callable = callable;
	}

	public Double getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}

	public Integer getConvertible() {
		return convertible;
	}

	public void setConvertible(Integer convertible) {
		this.convertible = convertible;
	}

	public String getBackroundType() {
		return backroundType;
	}

	public void setBackroundType(String backroundType) {
		this.backroundType = backroundType;
	}

	public Date getMaturitydate() {
		return maturitydate;
	}

	public void setMaturitydate(Date maturitydate) {
		this.maturitydate = maturitydate;
	}

	public Date getCallabilityperiod() {
		return callabilityperiod;
	}

	public void setCallabilityperiod(Date callabilityperiod) {
		this.callabilityperiod = callabilityperiod;
	}

	public Float getAvailablecoupon() {
		return availablecoupon;
	}

	public void setAvailablecoupon(Float availablecoupon) {
		this.availablecoupon = availablecoupon;
	}

	public String getTypeOfCouponPayment() {
		return typeOfCouponPayment;
	}

	public void setTypeOfCouponPayment(String typeOfCouponPayment) {
		this.typeOfCouponPayment = typeOfCouponPayment;
	}

	public Date getDateOfPublication() {
		return dateOfPublication;
	}

	public void setDateOfPublication(Date dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public InstrumentServiceRemote getInstrumentServiceRemote() {
		return instrumentServiceRemote;
	}

	public void setInstrumentServiceRemote(InstrumentServiceRemote instrumentServiceRemote) {
		this.instrumentServiceRemote = instrumentServiceRemote;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getInstrumentIssuer() {
		return InstrumentIssuer;
	}

	public void setInstrumentIssuer(Customer instrumentIssuer) {
		InstrumentIssuer = instrumentIssuer;
	}

	public String getInstrumentIssuerType() {
		return InstrumentIssuerType;
	}

	public void setInstrumentIssuerType(String instrumentIssuerType) {
		InstrumentIssuerType = instrumentIssuerType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currencyy) {
		currency = currencyy;
	}

	public List<Customer> getCustomers() {
		customers = userServiceLocal.getalltradercustomersbyid(19);

		return customers;
	}

	public void setBonds(List<Bond> bonds) {
		this.bonds = bonds;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Date getDatetoday() {
		return datetoday;
	}

	public void setDatetoday(Date datetoday) {
		this.datetoday = datetoday;
	}
	public Integer getInstrumentIssuerID() {
		return instrumentIssuerID;
	}

	public void setInstrumentIssuerID(Integer instrumentIssuerID) {
		this.instrumentIssuerID = instrumentIssuerID;
	}

	public boolean isPricingVar() {
		return pricingVar;
	}

	public void setPricingVar(boolean pricingVar) {
		this.pricingVar = pricingVar;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public boolean isCallableTest() {
		return callableTest;
	}

	public void setCallableTest(boolean callableTest) {
		this.callableTest = callableTest;
	}

	public boolean isConvertibleTest() {
		return convertibleTest;
	}

	public void setConvertibleTest(boolean convertibleTest) {
		this.convertibleTest = convertibleTest;
	}

	public List<Bond> getBonds() {
		bonds = instrumentServiceRemote.findAllBonds();
		return bonds;
	}

	@PostConstruct
	public void init() {

		datetoday = new Date();
		System.out.println(bonds);
		// customers = userServiceLocal.getalltradercustomersbyid(19);

	}

	public void addBond() {
		System.out.println("dhaerli hehdi" + currency);
		System.out.println(instrumentIssuerID);
		
		if (callableTest==true){
			callable=1;
		}else callable=0;
		
		if (convertibleTest==true){
			convertible=1;
		}else convertible=0;
		
		if (pricingVar==false)
		{instrumentServiceRemote.create(new Bond(currency, null, InstrumentIssuerType, ammount, couponrate, callable, x,
				convertible, backroundType, null, null, typeOfCouponPayment, datetoday));
		System.out.println("dkhal lele fct");}
		else if (pricingVar==true){
			instrumentServiceRemote.create(new Bond(currency, null, InstrumentIssuerType, x, couponrate, callable, ammount,
					convertible, backroundType, null, null, typeOfCouponPayment, datetoday));
			System.out.println("dkhal lele fct");
		}
	}

	public Double pricingBond() {
		System.out.println("pricing");
		if (pricingVar == false) {
			Double x =  (double) instrumentServiceRemote.calculFutureValue(ammount, couponrate, 5, typeOfCouponPayment);
			System.out.println(x);
			System.out.println(pricingVar);
		this.setX(x);
			return x;
		} else if (pricingVar == true) {
			Double x = (double) instrumentServiceRemote.calculPrincipleValue(ammount, couponrate, 5,
					typeOfCouponPayment);
			System.out.println(x);
			System.out.println(pricingVar);
			this.setX(x);
			return x;
		}
		return  0.0;

	}

	public void deleteBond(Integer bondId) {
		instrumentServiceRemote.delete(bondId);
		System.out.println("tfasaaaaaakh byeeee");
	}

	public void setIssuer(Bond b) {
		this.setInstrumentIssuer((Customer) b.getInstrumentIssuer());
	}
	public float calculateYieldTomaturity(){
		Float x=instrumentServiceRemote.calculateBondPriceToMaturityDate(parValueNew, marketValue, maturityInYears,annualRate);
	System.out.println("aslema"+x);
	this.setYieldToMaturity(x);
	return (float) 0.0;
	}

	public Float getYieldToMaturity() {
		return yieldToMaturity;
	}

	public void setYieldToMaturity(Float yieldToMaturity) {
		this.yieldToMaturity = yieldToMaturity;
	}

	public Float getParValueNew() {
		return parValueNew;
	}

	public void setParValueNew(Float parValueNew) {
		this.parValueNew = parValueNew;
	}

	public void afficherCustomer(){
		System.out.println("taw"+instrumentIssuerID);
		setInstrumentIssuer((Customer) userServiceLocal.findUserById(instrumentIssuerID));
		System.out.println("sayed"+c);
	}
	public void viewC(){
		System.out.println("l9ah za3ma");
		
		Customer c=(Customer) userServiceLocal.findUserById(instrumentIssuerID);
		this.setCustomerEmail(c.getEmail());
		this.setCustomerPhone(c.getPhone());
		
	   Long nbr=instrumentServiceRemote.nbrBondsByCustomer(instrumentIssuerID);
	    System.out.println(nbr);
		this.setNbrBondsTraded(nbr);
		
		System.out.println(customerEmail+customerPhone);
		
		
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}
	public Double convertCurrency() throws IOException{
		// Setting URL
					String url_str = "https://v3.exchangerate-api.com/bulk/31499df34d95d359397c9efd/";
					String pricee=from;
					// Making Request
					String urlNew =url_str+pricee;
					URL url = new URL(urlNew);
					HttpURLConnection request = (HttpURLConnection) url.openConnection();
					request.connect();

					// Convert to JSON
					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					JsonObject jsonobj = root.getAsJsonObject();

					// Accessing object
					String req_result = jsonobj.get("result").getAsString();
					//String req_result2 = jsonobj.get("timestamp").getAsString();
					//String req_result3 = jsonobj.get("from").getAsString();
					JsonObject req_result4 = jsonobj.get("rates").getAsJsonObject();

					System.out.println(jsonobj);
					
					price=(req_result4.get(to)).getAsDouble()*soumLekher;
					System.out.println(price);
					JsonParser parser = new JsonParser(); 
					JsonElement mJson =  parser.parse(jsonobj.toString());
					System.out.println(price);
					System.out.println("behi ay");
					System.out.println(from);
					System.out.println(to);
					this.setPrice(price);
					return price;
					
		
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(Integer integer) {
		this.customerPhone = integer;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Long getNbrBondsTraded() {
		return nbrBondsTraded;
	}

	public void setNbrBondsTraded(Long nbr) {
		this.nbrBondsTraded = nbr;
	}
	
	
}
