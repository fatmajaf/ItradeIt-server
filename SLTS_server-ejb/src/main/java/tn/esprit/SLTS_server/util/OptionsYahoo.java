package tn.esprit.SLTS_server.util;

import java.time.LocalDate;
import java.util.Set;


import javax.ejb.Stateless;

import com.zavtech.morpheus.frame.DataFrame;
import com.zavtech.morpheus.yahoo.YahooField;
import com.zavtech.morpheus.yahoo.YahooFinance;

/**
 * Session Bean implementation class OptionsYahoo
 */


public class OptionsYahoo implements OptionsYahooRemote, OptionsYahooLocal {

	YahooFinance yahoo = new YahooFinance();

	@Override
	public DataFrame<String, YahooField> optionquotes(String ticker, String optionType) {
	
		
		Set<LocalDate> expiryDates = yahoo.getOptionExpiryDates(ticker);
		LocalDate nextExpiry = expiryDates.iterator().next();
		DataFrame<String,YahooField> optionQuotes = yahoo.getOptionQuotes(ticker, nextExpiry);
		if (optionType.equals("all")){
			return optionQuotes;	
		}
		else if (optionType.equals("call")){
			DataFrame<String,YahooField> calls = optionQuotes.rows().select(row -> {
			    final String type = row.getValue(YahooField.OPTION_TYPE);
			    return type.equalsIgnoreCase("Call");
			    
			});
		return calls;	
		}
			else if (optionType.equals("put")){
				DataFrame<String,YahooField> puts = optionQuotes.rows().select(row -> {
				    final String type = row.getValue(YahooField.OPTION_TYPE);
				    return type.equalsIgnoreCase("Put");
				    
				});
				return puts;
		}
			
		
		return null;
		
		
	}

	@Override
	public DataFrame<String, YahooField> getstats(String ticker) {
		
		return yahoo.getStatistics(ticker);
	}

}
