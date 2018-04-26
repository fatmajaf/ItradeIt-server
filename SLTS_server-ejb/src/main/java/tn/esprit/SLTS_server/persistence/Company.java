package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Company implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(length=50)
	private String name;
	@Column(name="creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	@OneToOne(mappedBy="company")
	private Customer customer;
	@Column(length=512)
	private String photo;
	private String address;
	private String country;
	private String city;
	private double longitude;
	private double latitude;
	private long phoneNumber;
	private String email;
	private String webSite;
	private long coveredCountries;
	private long nbrEmployees;
	@Enumerated(EnumType.STRING)
	private JuridicalStatus juridicalStatus;
	@Enumerated(EnumType.STRING)
	private Size companySize;
	@Enumerated(EnumType.STRING)
	private Sector sector;
	@Enumerated(EnumType.STRING)
	private Activity activity;
	private Double revenue;
	private Double operationIncome;
	private Double netIncome;
	private Double totalAssets;
	private Double totalEquity;
	
	@OneToMany(mappedBy="company")
	private List<History> histories;
	
	
	@OneToMany(mappedBy="company")
	private List<WatchList> watchLists;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", creationDate=" + creationDate + ", customer=" + customer
				+ "]";
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public long getCoveredCountries() {
		return coveredCountries;
	}
	public void setCoveredCountries(long coveredCountries) {
		this.coveredCountries = coveredCountries;
	}
	public long getNbrEmployees() {
		return nbrEmployees;
	}
	public void setNbrEmployees(long nbrEmployees) {
		this.nbrEmployees = nbrEmployees;
	}
	public JuridicalStatus getJuridicalStatus() {
		return juridicalStatus;
	}
	public void setJuridicalStatus(JuridicalStatus juridicalStatus) {
		this.juridicalStatus = juridicalStatus;
	}
	public Size getCompanySize() {
		return companySize;
	}
	public void setCompanySize(Size companySize) {
		this.companySize = companySize;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	public Double getOperationIncome() {
		return operationIncome;
	}
	public void setOperationIncome(Double operationIncome) {
		this.operationIncome = operationIncome;
	}
	public Double getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}
	public Double getTotalAssets() {
		return totalAssets;
	}
	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}
	public Double getTotalEquity() {
		return totalEquity;
	}
	public void setTotalEquity(Double totalEquity) {
		this.totalEquity = totalEquity;
	}
	public List<WatchList> getWatchLists() {
		return watchLists;
	}
	public void setWatchLists(List<WatchList> watchLists) {
		this.watchLists = watchLists;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	 
	public List<History> getHistories() {
		return histories;
	}
	public void setHistories(List<History> histories) {
		this.histories = histories;
	}
		

}
