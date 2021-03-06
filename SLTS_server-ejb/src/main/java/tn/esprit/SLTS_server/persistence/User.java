package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
@DiscriminatorColumn(name="role")

public class User implements Serializable{
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String login;
	private String password;
	@Column(name="first_name",length=50)
	private String firstName;
	@Column(name="last_name",length=50)
	private String lastName;
	@Column(length=50)
	private String nationality;
	private String email;
	@Column(name="creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	@Column(name="is_active")
	private Integer isactive=1;
	@Column(name="birth_date")
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	private Integer phone;
	private String photo;
	
	//kaies
	@OneToMany(mappedBy="iduser")
	private List<Reponse> reponses;
	
	@OneToMany(mappedBy="iduser")
	private List<Forum> questions;
	
	@OneToMany(mappedBy="users")
	private List<Portfolio> portfolios;

	//@OneToMany
	//private List<Offer> offers;
	@OneToOne
	private Address address;
	//maryem
	@OneToMany(mappedBy="user")
	private List<WatchList> watchLists;
	
	
	//asma
	@OneToMany(mappedBy="InstrumentIssuer")
	private List<Instrument> instruments;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user",cascade= CascadeType.ALL)
	
	private List<Comment> comments;
	
	
	public List<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	/*public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}*/
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<Instrument> getInstruments() {
		return instruments;
	}
	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName+"\n";
	}
	public void addcomment(Comment comment){
		//comment.setCommenter(this);
		this.comments.add(comment);
	}
	
	
	
	
	

}
