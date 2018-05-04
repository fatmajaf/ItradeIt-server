package tn.esprit.SLTS_server.utils;



public class Options {
	public String index;
	public String ticker;
	public String optiontype;
	public String exirydate;
	public String strikeprice;
	public String lastprice;
	public String change;
	public String changepercent;
	public String bid;
	public String ask;
	public String volume;
	public String openinterest;
	public String impvolatility;
	public Options(String index, String ticker, String optiontype, String exirydate, String strikeprice,
			String lastprice, String change, String changepercent, String bid, String ask, String volume,
			String openinterest, String impvolatility) {
		super();
		this.index = index;
		this.ticker = ticker;
		this.optiontype = optiontype;
		this.exirydate = exirydate;
		this.strikeprice = strikeprice;
		this.lastprice = lastprice;
		this.change = change;
		this.changepercent = changepercent;
		this.bid = bid;
		this.ask = ask;
		this.volume = volume;
		this.openinterest = openinterest;
		this.impvolatility = impvolatility;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getOptiontype() {
		return optiontype;
	}
	public void setOptiontype(String optiontype) {
		this.optiontype = optiontype;
	}
	public String getExirydate() {
		return exirydate;
	}
	public void setExirydate(String exirydate) {
		this.exirydate = exirydate;
	}
	public String getStrikeprice() {
		return strikeprice;
	}
	public void setStrikeprice(String strikeprice) {
		this.strikeprice = strikeprice;
	}
	public String getLastprice() {
		return lastprice;
	}
	public void setLastprice(String lastprice) {
		this.lastprice = lastprice;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getChangepercent() {
		return changepercent;
	}
	public void setChangepercent(String changepercent) {
		this.changepercent = changepercent;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getAsk() {
		return ask;
	}
	public void setAsk(String ask) {
		this.ask = ask;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getOpeninterest() {
		return openinterest;
	}
	public void setOpeninterest(String openinterest) {
		this.openinterest = openinterest;
	}
	public String getImpvolatility() {
		return impvolatility;
	}
	public void setImpvolatility(String impvolatility) {
		this.impvolatility = impvolatility;
	}

}
