package tn.esprit.SLTS_server.presentation.mbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.zavtech.morpheus.frame.DataFrame;
import com.zavtech.morpheus.yahoo.YahooField;

import javafx.scene.control.TreeItem;
import tn.esprit.SLTS_server.persistence.Trader;
import tn.esprit.SLTS_server.utils.DataCollector;
import tn.esprit.SLTS_server.utils.News;
import tn.esprit.SLTS_server.utils.NewsItem;
import tn.esprit.SLTS_server.utils.Options;
import tn.esprit.SLTS_server.utils.OptionsYahoo;
import tn.esprit.SLTS_server.utils.SentimentAnalysisWithCount;
import tn.esprit.SLTS_server.utils.StatsT;

import twitter4j.TwitterException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@ManagedBean
@ViewScoped
public class YahooFinanceBean {
	private String symbolinput;
	 private List<HistoricalQuote>listehq=new ArrayList<HistoricalQuote>();
	 private List<Options>listopput=new ArrayList<Options>();
	 private List<Options>listopcall=new ArrayList<Options>();
	 private Stock stock;
	 private OptionsYahoo o = new OptionsYahoo();
		private DataFrame<String, YahooField> optionQuotes;
		private List<News> newslist;
		private StatsT stat ;
		private LinkedList<NewsItem> linews;
		private int nbpositivenews;
		private int nbnegativenews;
		private List<HistoricalQuote>listehqchart=new ArrayList<HistoricalQuote>();
		@ManagedProperty(value="#{loginBean}")
		LoginBean loginBean;
	 
		@PostConstruct
		public void init(){
			symbolinput="NVDA";
			 searchforStocksbysymbol("none", 0);
			 populateOptions("call");
		     populateOptions("put");
		     populatenews();
		     populatechartnews();
		     populatestatistics();
		}
	 public String populateyahoo(String symbol){
		 
		 symbolinput=symbol;
		 searchforStocksbysymbol("none", 0);
		 populateOptions("call");
	     populateOptions("put");
	     populatenews();
	     populatechartnews();
	     populatestatistics();
	     if (loginBean.getUs() instanceof Trader){
	    	return "/pages/trader/yahoof?faces-redirect=true";
	     }
	     else 
	 	{return "/pages/admin/yahoof?faces-redirect=true";}
	 }
	 public void populatestatistics(){
		 DataFrame<String, YahooField> stats;
		 
		 stats = o.getstats(symbolinput);
			stats.rows().forEach(x -> {
				// stats atable= new statsT....
				String dexdate = "Nan";
				String lastspdate = "Nan";
				if (x.getValue(YahooField.DIVIDEND_EX_DATE) != null) {
					dexdate = x.getValue(YahooField.DIVIDEND_EX_DATE).toString();
				}
				if (x.getValue(YahooField.LAST_SPLIT_DATE) != null) {
				}
				lastspdate = x.getValue(YahooField.LAST_SPLIT_DATE).toString();

				stat= new StatsT(x.getValue(YahooField.MARKET_CAP).toString(),
						x.getValue(YahooField.PE_TRAILING).toString(), x.getValue(YahooField.PRICE_SALES_RATIO).toString(),
						x.getValue(YahooField.PRICE_BOOK_RATIO).toString(),
						x.getValue(YahooField.FISCAL_YEAR_END).toString(),
						x.getValue(YahooField.MOST_RECENT_QUARTER).toString(),
						x.getValue(YahooField.PROFIT_MARGIN).toString(), x.getValue(YahooField.OPERATING_MARGIN).toString(),
						x.getValue(YahooField.RETURN_ON_ASSETS).toString(),
						x.getValue(YahooField.RETURN_ON_EQUITY).toString(), x.getValue(YahooField.REVENUE_TTM).toString(),
						x.getValue(YahooField.REVENUE_PER_SHARE).toString(),
						x.getValue(YahooField.REVENUE_GROWTH_QTLY).toString(),
						x.getValue(YahooField.GROSS_PROFIT).toString(), x.getValue(YahooField.EBITDA_TTM).toString(),
						x.getValue(YahooField.EPS_DILUTED).toString(),
						x.getValue(YahooField.EARNINGS_GRWOTH_QTLY).toString(), x.getValue(YahooField.BETA).toString(),
						x.getValue(YahooField.CASH_MRQ).toString(), x.getValue(YahooField.CASH_PER_SHARE).toString(),
						x.getValue(YahooField.DEBT_MRQ).toString(), x.getValue(YahooField.DEBT_OVER_EQUITY_MRQ).toString(),
						x.getValue(YahooField.CURRENT_RATIO).toString(),
						x.getValue(YahooField.BOOK_VALUE_PER_SHARE).toString(),
						x.getValue(YahooField.OPERATING_CASH_FLOW).toString(),
						x.getValue(YahooField.LEVERED_FREE_CASH_FLOW).toString(),
						x.getValue(YahooField.ADV_3MONTH).toString(), x.getValue(YahooField.ADV_10DAY).toString(),
						x.getValue(YahooField.SHARES_OUTSTANDING).toString(),
						x.getValue(YahooField.SHARES_FLOAT).toString(),
						x.getValue(YahooField.OWNER_PERCENT_INSIDER).toString(),
						x.getValue(YahooField.OWNER_PERCENT_INSTITUTION).toString(),
						x.getValue(YahooField.SHARES_SHORT).toString(),
						x.getValue(YahooField.SHARES_SHORT_RATIO).toString(),
						x.getValue(YahooField.SHARES_SHORT_PRIOR).toString(),
						x.getValue(YahooField.DIVIDEND_FWD).toString(),
						x.getValue(YahooField.DIVIDEND_FWD_YIELD).toString(),
						x.getValue(YahooField.DIVIDEND_TRAILING).toString(),
						x.getValue(YahooField.DIVIDEND_TRAILING_YIELD).toString(),
						x.getValue(YahooField.DIVIDEND_PAYOUT_RATIO).toString(),
						x.getValue(YahooField.DIVIDEND_PAY_DATE).toString(), dexdate, lastspdate);

				
				
			});
			System.out.println("gggggg" + stat.earningsgrowthqtly);


	 }
	 public void populatechartnews(){
		 HashMap<String, Integer> map = null;
         try {
			 map=  SentimentAnalysisWithCount.newssanalysis(linews);
		} catch (TwitterException | IOException | NamingException e) {
			e.printStackTrace();
		}
             System.out.println("positive news :"+map.get("positivenews").toString());    
             System.out.println("negative news :"+map.get("negativenews").toString());   
             
             
             nbpositivenews = Integer.parseInt(map.get("positivenews").toString());
     		nbnegativenews = Integer.parseInt(map.get("negativenews").toString());
          int somme=nbnegativenews+nbpositivenews;
	 }
	 public void te(HistoricalQuote historicalQuote){
		System.out.println(historicalQuote.getSymbol());
	 }
	 public void searchforStocksbysymbol(String period, int num) {
			
			if (period.equals("none") && num == 0) {
				try {
					System.out.println("bbbbbbbbbbbbbbbbbbbbbbbb"+symbolinput);
					stock = YahooFinance.get(symbolinput, true);
				
				} catch (IOException ex) {
					System.out.println("Symbol not found!!");
			
				}
			} else {
				try {
					Calendar from = Calendar.getInstance();
					Calendar to = Calendar.getInstance();
					if (period.equals("Month")) {
						from.add(Calendar.MONTH, -num);
						stock = YahooFinance.get(symbolinput, from, to, Interval.MONTHLY);
					} else if (period.equals("Year")) {
						from.add(Calendar.YEAR, -num);
						stock = YahooFinance.get(symbolinput, from, to, Interval.WEEKLY);
					} else if (period.equals("Day")) {
						from.add(Calendar.DAY_OF_YEAR, -num);
						stock = YahooFinance.get(symbolinput, from, to, Interval.DAILY);
					}

				} catch (IOException ex) {
					System.out.println("Symbol not found!!");
				}
			}
			System.out.println("name " +stock.getName());
			System.out.println(" "+stock.getDividend().getAnnualYield());
			Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			from.add(Calendar.MONTH, -3);
			Stock stchart = null;
			try {
				stchart= YahooFinance.get(symbolinput, from, to, Interval.MONTHLY);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listehqchart=new ArrayList<HistoricalQuote>();
			try {
				List<HistoricalQuote>lii=new ArrayList<HistoricalQuote>();
						lii=stchart.getHistory();
				if (lii.size() > 12)
				{listehqchart= lii.subList(lii.size()-12, lii.size());}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			populatedatatableStocks(stock);
			
			}
	public void populatedatatableStocks(Stock stock2) {
		listehq = new ArrayList<HistoricalQuote>();
		try {
			listehq = stock2.getHistory();

		} catch (IOException e) {

			e.printStackTrace();
		}

		for (HistoricalQuote historicalQuote : listehq) {

			if (historicalQuote.getClose() != null) {

				System.out.println("adj close "+historicalQuote.getAdjClose());
			
			}}
	}
	public void goclicked(){
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa"+symbolinput);
		 searchforStocksbysymbol("none", 0);
	     populateOptions("call");
	     populateOptions("put");
	     populatenews();
	     populatestatistics();
	     populatechartnews();
		 FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:example");
	 }
	
	
	public void daily1Clicked() {
		if (!symbolinput.equals("")) {
	
			searchforStocksbysymbol("Day", 1);
			
			 FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:example");
		}

	}

	public void populatenews(){
		newslist= new ArrayList<>();
	DataCollector dc = new DataCollector();

		linews = dc.obtainNews(symbolinput);
		System.out.println("Success!");

		for (int i = 0; i < linews.size(); i++) {

			System.out.println("Title : " + linews.get(i).Title);
			System.out.println("Description : " + linews.get(i).Description);
			System.out.println("Url : " + linews.get(i).URL);
			System.out.println("Date : " + linews.get(i).DatePublished);
			System.out.println("--------------------------------");
			News n = new News(linews.get(i).Title.toString(), linews.get(i).Description.toString(), linews.get(i).URL.toString(),
					linews.get(i).DatePublished.toString());
			newslist.add(n);
		}
	}

	public void daily5clicked() {
		if (!symbolinput.equals("")) {
			searchforStocksbysymbol("Day", 5);

		}

	}


	public void monthly3clicked() {
		if (!symbolinput.equals("")) {
			searchforStocksbysymbol("Month", 3);

		}

	}

	
	public void monthly6clicked() {
		if (!symbolinput.equals("")) {
			searchforStocksbysymbol("Month", 6);

		}

	}


	public void yearly1clicked() {
		if (!symbolinput.equals("")) {
			searchforStocksbysymbol("Year", 1);

		}

	}

	
	public void yearly5clicked() {
		if (!symbolinput.equals("")) {
			searchforStocksbysymbol("Year", 5);

		}

	}
	public void populateOptions(String opttype){
	
		if (opttype.equals("call")) {
			listopcall=new ArrayList<Options>();
			optionQuotes = o.optionquotes(symbolinput, "call");
			optionQuotes.rows().forEach(

					x -> {

						String chnge = "Nan";
						if (x.getValue(YahooField.PX_CHANGE) != null) {
							chnge = x.getValue(YahooField.PX_CHANGE).toString();
						}
						Options atable = new Options(x.key(), x.getValue(YahooField.TICKER).toString(),
								x.getValue(YahooField.OPTION_TYPE).toString(),
								x.getValue(YahooField.EXPIRY_DATE).toString(),
								x.getValue(YahooField.PX_STRIKE).toString(), x.getValue(YahooField.PX_LAST).toString(),
								chnge, x.getValue(YahooField.PX_CHANGE_PERCENT).toString(),
								x.getValue(YahooField.PX_BID).toString(), x.getValue(YahooField.PX_ASK).toString(),
								x.getValue(YahooField.PX_VOLUME).toString(),
								x.getValue(YahooField.OPEN_INTEREST).toString(),
								x.getValue(YahooField.IMPLIED_VOLATILITY).toString());
						listopcall.add(atable);
					});

		} else {
			listopput=new ArrayList<Options>();
			optionQuotes = o.optionquotes(symbolinput, "put");
			optionQuotes.rows().forEach(

					x -> {

						Options atable = new Options(x.key(), x.getValue(YahooField.TICKER).toString(),
								x.getValue(YahooField.OPTION_TYPE).toString(),
								x.getValue(YahooField.EXPIRY_DATE).toString(),
								x.getValue(YahooField.PX_STRIKE).toString(), x.getValue(YahooField.PX_LAST).toString(),
								x.getValue(YahooField.PX_CHANGE).toString(),
								x.getValue(YahooField.PX_CHANGE_PERCENT).toString(),
								x.getValue(YahooField.PX_BID).toString(), x.getValue(YahooField.PX_ASK).toString(),
								x.getValue(YahooField.PX_VOLUME).toString(),
								x.getValue(YahooField.OPEN_INTEREST).toString(),
								x.getValue(YahooField.IMPLIED_VOLATILITY).toString());
						listopput.add(atable);
					});
		}
	}

	
	public List<Options> getListopput() {
		return listopput;
	}
	public void setListopput(List<Options> listopput) {
		this.listopput = listopput;
	}
	public List<Options> getListopcall() {
		return listopcall;
	}
	public void setListopcall(List<Options> listopcall) {
		this.listopcall = listopcall;
	}
	public String getSymbolinput() {
		return symbolinput;
	}
	public void setSymbolinput(String symbolinput) {
		this.symbolinput = symbolinput;
	}
	public List<HistoricalQuote> getListehq() {
		return listehq;
	}
	public void setListehq(List<HistoricalQuote> listehq) {
		this.listehq = listehq;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public OptionsYahoo getO() {
		return o;
	}
	public void setO(OptionsYahoo o) {
		this.o = o;
	}
	public DataFrame<String, YahooField> getOptionQuotes() {
		return optionQuotes;
	}
	public void setOptionQuotes(DataFrame<String, YahooField> optionQuotes) {
		this.optionQuotes = optionQuotes;
	}
	public List<News> getNewslist() {
		return newslist;
	}
	public void setNewslist(List<News> newslist) {
		this.newslist = newslist;
	}
	public StatsT getStat() {
		return stat;
	}
	public void setStat(StatsT stat) {
		this.stat = stat;
	}
	public LinkedList<NewsItem> getLinews() {
		return linews;
	}
	public void setLinews(LinkedList<NewsItem> linews) {
		this.linews = linews;
	}
	public int getNbpositivenews() {
		return nbpositivenews;
	}
	public void setNbpositivenews(int nbpositivenews) {
		this.nbpositivenews = nbpositivenews;
	}
	public int getNbnegativenews() {
		return nbnegativenews;
	}
	public void setNbnegativenews(int nbnegativenews) {
		this.nbnegativenews = nbnegativenews;
	}
	public List<HistoricalQuote> getListehqchart() {
		return listehqchart;
	}
	public void setListehqchart(List<HistoricalQuote> listehqchart) {
		this.listehqchart = listehqchart;
	}
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	
}
