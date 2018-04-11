package tn.esprit.SLTS_server.util;

import javax.ejb.Remote;

import com.zavtech.morpheus.frame.DataFrame;
import com.zavtech.morpheus.yahoo.YahooField;


public interface OptionsYahooRemote {
	public DataFrame<String, YahooField> optionquotes(String ticker, String optionType);
	public DataFrame<String, YahooField> getstats(String ticker);
}
