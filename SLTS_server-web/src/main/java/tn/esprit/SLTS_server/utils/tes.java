package tn.esprit.SLTS_server.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class tes {
	public void searchforStocksbysymbol(String period, int num) {
		Stock stock = null;
		if (period.equals("none") && num == 0) {
			try {
				stock = YahooFinance.get("nvda", true);
			} catch (IOException ex) {
				System.out.println("symbol not found");
			}
		} else {
			try {
				Calendar from = Calendar.getInstance();
				Calendar to = Calendar.getInstance();
				if (period.equals("Month")) {
					from.add(Calendar.MONTH, -num);
					stock = YahooFinance.get("nvda", from, to, Interval.MONTHLY);
				} else if (period.equals("Year")) {
					from.add(Calendar.YEAR, -num);
					stock = YahooFinance.get("nvda", from, to, Interval.WEEKLY);
				} else if (period.equals("Day")) {
					from.add(Calendar.DAY_OF_YEAR, -num);
					stock = YahooFinance.get("nvda", from, to, Interval.DAILY);
				}

			} catch (IOException ex) {
				System.out.println("symbol nt found");
			}
		}

		if (stock != null) {
			System.out.println((stock.getSymbol()));
		System.out.println((stock.getName()));
		System.out.println(stock.getStockExchange());
		System.out.println(stock.getCurrency());
		List<HistoricalQuote>listehq = new ArrayList<HistoricalQuote>();
		try {
			listehq = stock.getHistory();

		} catch (IOException e) {

			e.printStackTrace();
		}

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

		}
		

	}
	public static void main(String[] args) throws IOException {
		/*String[] symbols = new String[] {"INTC", "BABA", "TSLA", "YHOO"};
		Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
		Stock intel = stocks.get("INTC");
		Stock airbus = stocks.get("AIR.PA");
		
		*/
		System.out.println("jjjjj");
		Stock goog = YahooFinance.get("GOOG");
		
		goog.print();
		System.out.println(goog.getQuote().getChangeInPercent());
		
		//System.out.println(YahooFinance.getFx("GOOG").);
	
	}

}
