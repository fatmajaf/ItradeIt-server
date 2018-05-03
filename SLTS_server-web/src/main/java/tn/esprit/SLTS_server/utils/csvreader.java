package tn.esprit.SLTS_server.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

public class csvreader {

	public static void main(String[] args) throws IOException {
		

	        String csvFile = "D:/jee/SLTS_server/SLTS_server-web/src/main/java/symbols.csv";
	        String line = "";
	        String cvsSplitBy = ",";
	        String[] symbols = null ;
	        int i=0;
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {
                  i++;

	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.println("i "+i);
	        symbols = new String[i];
	        i=0;
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {

	                

	            	System.out.println("k  "+line);
	            	symbols[i]=line;
	            	System.out.println("sym "+symbols[i]);
	            	i++;
	           

	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        symbols = new String[] {"NVDA", "GOOG", "FB"};
	        String[] symbols2= new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
	        Map<String, Stock> stocks = YahooFinance.get(symbols2); // single request
	        /*Stock intel = stocks.get("INTC");
	        Stock airbus = stocks.get("AIR.PA");
	        */
	        
	        /*List<HistoricalQuote>listehq=new ArrayList<HistoricalQuote>(); 
			 listehq=goog.getHistory();
			 for (HistoricalQuote historicalQuote : listehq) {
					System.out.println("symbol "+historicalQuote.getSymbol());
					System.out.println("adj close"+historicalQuote.getAdjClose());
					System.out.println("close " +historicalQuote.getClose());
					System.out.println("date "+historicalQuote.getDate().getTime());
					System.out.println("high"+historicalQuote.getHigh());
					System.out.println("low "+historicalQuote.getLow());
					System.out.println("open "+historicalQuote.getOpen());
					System.out.println("volume "+historicalQuote.getVolume());
					
					
				}*/
	      /*  for (Map.Entry<String, Stock> mape : stocks.entrySet())

	    	{*/
	        	Set<String> st= stocks.keySet();
	        	System.out.println(st);
	        	for (String string : st) {
	        		Stock stock= stocks.get(string);
	        		
	        		List<HistoricalQuote>listehq=new ArrayList<HistoricalQuote>(); 
	   			listehq=stock.getHistory();
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
	        	
	        	
	    	//}
	        

	   
	}

}
