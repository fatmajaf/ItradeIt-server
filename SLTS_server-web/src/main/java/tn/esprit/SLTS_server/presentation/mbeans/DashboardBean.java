package tn.esprit.SLTS_server.presentation.mbeans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.SLTS_server.persistence.Customer;
import tn.esprit.SLTS_server.persistence.Trader;
import tn.esprit.SLTS_server.persistence.User;
import tn.esprit.SLTS_server.services.CommentService;
import tn.esprit.SLTS_server.services.UserService;
import tn.esprit.SLTS_server.utils.CurrencyConvert;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@ManagedBean
@ViewScoped
public class DashboardBean {
	// active trader
	private Trader activetrader;
	private int nbcustforactivetrader;
	private Long nbtradesforactivetrader;
	private String photoactivetrader;
	// lazy tradet
	private Trader lazytrader;
	private int nbcustforlazytrader;
	private Long nbtradesforformazytrader;
	private String photolazytrader;
	
	// newest customer
	private Customer newcustomer;
	private String photonewcustomer;
	//mostbannedcommenter
	private User mostbanneduser;
	private String photomostbanneduser;
	private Long nbcommentsbanned;
	//suggest 
	private int nbcusttoapp;
	private int nbcusttodelete;
	private int nbcusttorefuse;
	
	 private String[] symbols ;
	 private List<HistoricalQuote>listehq; 
	 ScheduledExecutorService servicex ;
	 Map<String, Stock> stocks;
	 private List<String> symbolss;
	@EJB
	UserService service;
	@EJB
	CommentService commentservice;
	
	@PostConstruct
	public void init() throws IOException{
		//active trader
		activetrader=new Trader();
		activetrader=service.findtraderactivelazynbtrades("desc");
		nbcustforactivetrader = activetrader.getCustomers().size();
		nbtradesforactivetrader=service.getnbtradesforactivelazytrader("desc");
		if(activetrader.getPhoto()==null)
			{photoactivetrader="http://localhost:8080/ImagesItradeit/user.png";}
		else 
			{photoactivetrader=activetrader.getPhoto().replace("C:/wamp64/www/","http://localhost:8080/");}
		//lazy trader
		lazytrader= new Trader();
		lazytrader= service.findtraderactivelazynbtrades("asc");
		nbcustforlazytrader=lazytrader.getCustomers().size();
		nbtradesforformazytrader=service.getnbtradesforactivelazytrader("asc");
		if(lazytrader.getPhoto()==null)
			{photolazytrader="http://localhost:8080/ImagesItradeit/user.png";}
		else 
			{photolazytrader=lazytrader.getPhoto().replace("C:/wamp64/www/","http://localhost:8080/");}
		
        populate();
		
	
        List<User> liste= new ArrayList<>();
        liste=service.findAllCustomers();
        newcustomer= new Customer();
        if (!liste.isEmpty() && liste.get(0) instanceof Customer)
        {newcustomer=(Customer) liste.get(0);}
        if(newcustomer.getPhoto()==null)
		{photonewcustomer="http://localhost:8080/ImagesItradeit/user.png";}
	else 
		{photonewcustomer=newcustomer.getPhoto().replace("C:/wamp64/www/","http://localhost:8080/");}
	
        mostbanneduser = new User();
        mostbanneduser=commentservice.getmostbannedcommenter();
        System.out.println("most banneeeeeeeeeed " +mostbanneduser);
     if(mostbanneduser.getPhoto()==null)
		{photomostbanneduser="http://localhost:8080/ImagesItradeit/user.png";}
	else 
		{photomostbanneduser=mostbanneduser.getPhoto().replace("C:/wamp64/www/","http://localhost:8080/");}
	
        nbcommentsbanned=commentservice.getnbmaxbannedcomments();
	}
	

	public void populate() throws IOException{
		symbolss= new ArrayList<String>();
		   String csvFile = "D:/jee/SLTS_server/SLTS_server-web/src/main/java/symbols.csv";
	        String line = "";
	        String cvsSplitBy = ",";
	       
	        int i=0;
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {
	              i++;

	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        symbols = new String[i];
	        i=0;
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {

	                

	            	System.out.println("k  "+line);
	            	symbols[i]=line;
	            	symbolss.add(line);
	            	System.out.println("sym "+symbols[i]);
	            	//searchforStocksbysymbol("none", 0, symbols[i]);
	            	i++;
	           

	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
			/*Runnable runnable = new Runnable() {
			      public void run() {*/
			    	  //for (int j = 0; j < symbols.length; j++) {
			    		  searchforStocksbysymbol("none", 0, symbols);
			    		  
			    	 
			   // }
			    		  //}
			// };
			/*  servicex = Executors
			                    .newSingleThreadScheduledExecutor();
			    servicex.scheduleAtFixedRate(runnable, 0,1, TimeUnit.MINUTES);
			    		  Runnable runnable = new Runnable() {
						      public void run() {
						    	  searchforStocksbysymbol("none", 0, "goog");
						    	  for (HistoricalQuote historicalQuote : listehq) {

					    				if (historicalQuote.getClose() != null) {

					    					System.out.println("symbol "+historicalQuote.getSymbol());
					    					System.out.println("adj close"+historicalQuote.getAdjClose());
					    					System.out.println("close " +historicalQuote.getClose());
					    					System.out.println("date "+historicalQuote.getDate().getTime());
					    					System.out.println("high"+historicalQuote.getHigh());
					    					System.out.println("low "+historicalQuote.getLow());
					    					System.out.println("open "+historicalQuote.getOpen());
					    					System.out.println("volume "+historicalQuote.getVolume());
					    				}
					    			}  
						      
						      }};
						    	  servicex = Executors
						                    .newSingleThreadScheduledExecutor();
						    servicex.scheduleAtFixedRate(runnable, 0,1, TimeUnit.MINUTES);
	
	*/
	
	}
	
	
	public ScheduledExecutorService getServicex() {
		return servicex;
	}





	public void setServicex(ScheduledExecutorService servicex) {
		this.servicex = servicex;
	}

public void suggestionpopulate(){
	 CurrencyConvert convert = new CurrencyConvert();
     Map<String, String> countries = new HashMap<>();
     for (String iso : Locale.getISOCountries()) {
         Locale l = new Locale("", iso);
         countries.put(l.getDisplayCountry(Locale.ENGLISH), iso);
        
     }
	        List<Customer> customerstorefus= new ArrayList<>();
	        List<Customer> customerstoaccept= new ArrayList<>();
	        List<Customer> customerstodelet= new ArrayList<>();
	    Date currentDate= new Date();
	    List<User> custs= service.findAlldesactiatedCustomers();
	   
	  
		if (!custs.isEmpty()){
			for (User user : custs) {
				if (user instanceof Customer)
				{
					
					Customer c=(Customer)user;
					System.out.println(c.getAddress().getSountry());
					  String fromCountry = c.getAddress().getSountry();
	                    String toCountry = "New Zealand";
	                    String toCurrency  = Currency.getInstance(new Locale("",countries.get(toCountry))).getCurrencyCode();
	                    String fromCurrency = Currency.getInstance(new Locale("",countries.get(fromCountry))).getCurrencyCode();
	                    
	                
	                    double rate = convert.convert(fromCurrency,toCurrency);
	                            rate = rate * Double.parseDouble("1");
	                            System.out.println(rate);
	                            if (rate<1000 )
	                            {
	                            	if ( c.getCreationDate().getMonth()< currentDate.getMonth() && c.getCreationDate().getYear() < currentDate.getYear())
	                            	{customerstorefus.add(c);}
	                            	else {
	                            		customerstodelet.add(c);
	                            	}
	                            }
	                            else {
	                            	customerstoaccept.add(c);
	                            }
	    	    
						
				}
			}
			
			
		}
		
		nbcusttoapp= customerstoaccept.size();
		
		
		nbcusttorefuse=customerstorefus.size();
		
	
		nbcusttodelete=customerstodelet.size();
}



	public void searchforStocksbysymbol(String period, int num, String []s) throws IOException {
		stocks = null;
		if (period.equals("none") && num == 0) {
			try {
				stocks = YahooFinance.get(s);
			} catch (IOException ex) {
				System.out.println("symbol not found");
			}
		} else {
			try {
				Calendar from = Calendar.getInstance();
				Calendar to = Calendar.getInstance();
				if (period.equals("Month")) {
					from.add(Calendar.MONTH, -num);
					stocks = YahooFinance.get(s, from, to, Interval.MONTHLY);
				} else if (period.equals("Year")) {
					from.add(Calendar.YEAR, -num);
					stocks = YahooFinance.get(s, from, to, Interval.WEEKLY);
				} else if (period.equals("Day")) {
					from.add(Calendar.DAY_OF_YEAR, -num);
					stocks = YahooFinance.get(s, from, to, Interval.DAILY);
				}

			} catch (IOException ex) {
				System.out.println("symbol nt found");
			}
		}

		/*if (stock != null) {
			
			
			System.out.println((stock.getSymbol()));
		System.out.println((stock.getName()));
		System.out.println(stock.getStockExchange());
		System.out.println(stock.getCurrency());
		listehq = new ArrayList<HistoricalQuote>();
		try {
			listehq = stock.getHistory();

		} catch (IOException e) {

			e.printStackTrace();
		}
*/
		
		

		//}
		listehq=new ArrayList<HistoricalQuote>(); 
		Set<String> st= stocks.keySet();
		
    	System.out.println(st);
    	for (String string : st) {
    		Stock stock= stocks.get(string);
    	
    		
			listehq.addAll(stock.getHistory());
			 for (HistoricalQuote historicalQuote : listehq) {
					System.out.println("symbol "+historicalQuote.getSymbol());
					System.out.println("adj close"+historicalQuote.getAdjClose());
					System.out.println("close " +historicalQuote.getClose());
					System.out.println("date "+historicalQuote.getDate().getTime());
					System.out.println("high"+historicalQuote.getHigh());
					System.out.println("low "+historicalQuote.getLow());
					System.out.println("open "+historicalQuote.getOpen());
					System.out.println("volume "+historicalQuote.getVolume());
					
					
					
				}
    		
		}
    	
		

	}
	
	
	public String getPhotoactivetrader() {
		return photoactivetrader;
	}


	public List<String> getSymbolss() {
		return symbolss;
	}


	public User getMostbanneduser() {
		return mostbanneduser;
	}


	public void setMostbanneduser(User mostbanneduser) {
		this.mostbanneduser = mostbanneduser;
	}


	public String getPhotomostbanneduser() {
		return photomostbanneduser;
	}


	public Long getNbcommentsbanned() {
		return nbcommentsbanned;
	}


	public void setNbcommentsbanned(Long nbcommentsbanned) {
		this.nbcommentsbanned = nbcommentsbanned;
	}


	public void setPhotomostbanneduser(String photomostbanneduser) {
		this.photomostbanneduser = photomostbanneduser;
	}


	public CommentService getCommentservice() {
		return commentservice;
	}


	public void setCommentservice(CommentService commentservice) {
		this.commentservice = commentservice;
	}


	public void setSymbolss(List<String> symbolss) {
		this.symbolss = symbolss;
	}


	public void setPhotoactivetrader(String photoactivetrader) {
		this.photoactivetrader = photoactivetrader;
	}


	public Trader getActivetrader() {
		return activetrader;
	}

	public void setActivetrader(Trader activetrader) {
		this.activetrader = activetrader;
	}

	public int getNbcustforactivetrader() {
		return nbcustforactivetrader;
	}

	public void setNbcustforactivetrader(int nbcustforactivetrader) {
		this.nbcustforactivetrader = nbcustforactivetrader;
	}

	public Long getNbtradesforactivetrader() {
		return nbtradesforactivetrader;
	}

	public void setNbtradesforactivetrader(Long nbtradesforactivetrader) {
		this.nbtradesforactivetrader = nbtradesforactivetrader;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}





	public Trader getLazytrader() {
		return lazytrader;
	}





	public void setLazytrader(Trader lazytrader) {
		this.lazytrader = lazytrader;
	}





	public int getNbcustforlazytrader() {
		return nbcustforlazytrader;
	}





	public void setNbcustforlazytrader(int nbcustforlazytrader) {
		this.nbcustforlazytrader = nbcustforlazytrader;
	}





	public Long getNbtradesforformazytrader() {
		return nbtradesforformazytrader;
	}





	public String getPhotonewcustomer() {
		return photonewcustomer;
	}


	public void setPhotonewcustomer(String photonewcustomer) {
		this.photonewcustomer = photonewcustomer;
	}


	public void setNbtradesforformazytrader(Long nbtradesforformazytrader) {
		this.nbtradesforformazytrader = nbtradesforformazytrader;
	}





	public String getPhotolazytrader() {
		return photolazytrader;
	}





	public void setPhotolazytrader(String photolazytrader) {
		this.photolazytrader = photolazytrader;
	}





	public String[] getSymbols() {
		return symbols;
	}





	public void setSymbols(String[] symbols) {
		this.symbols = symbols;
	}





	public List<HistoricalQuote> getListehq() {
		return listehq;
	}





	public Customer getNewcustomer() {
		return newcustomer;
	}


	public void setNewcustomer(Customer newcustomer) {
		this.newcustomer = newcustomer;
	}


	public void setListehq(List<HistoricalQuote> listehq) {
		this.listehq = listehq;
	}


	public Map<String, Stock> getStocks() {
		return stocks;
	}


	public void setStocks(Map<String, Stock> stocks) {
		this.stocks = stocks;
	}


	public int getNbcusttoapp() {
		return nbcusttoapp;
	}


	public void setNbcusttoapp(int nbcusttoapp) {
		this.nbcusttoapp = nbcusttoapp;
	}


	public int getNbcusttodelete() {
		return nbcusttodelete;
	}


	public void setNbcusttodelete(int nbcusttodelete) {
		this.nbcusttodelete = nbcusttodelete;
	}


	public int getNbcusttorefuse() {
		return nbcusttorefuse;
	}


	public void setNbcusttorefuse(int nbcusttorefuse) {
		this.nbcusttorefuse = nbcusttorefuse;
	}
	
	
	
	

}
