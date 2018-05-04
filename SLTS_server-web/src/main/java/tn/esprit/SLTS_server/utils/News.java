package tn.esprit.SLTS_server.utils;



public class News {
	public String titlee;
	public String description;
	public String url;
	public String datepub;
	
	
	
	
	public News(String titlee, String description, String url, String datepub) {
		super();
		this.titlee = titlee;
		this.description = description;
		this.url = url;
		this.datepub = datepub;
	}
	public String getTitlee() {
		return titlee;
	}
	public void setTitlee(String titlee) {
		this.titlee = titlee;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDatepub() {
		return datepub;
	}
	public void setDatepub(String datepub) {
		this.datepub = datepub;
	}
	
}
