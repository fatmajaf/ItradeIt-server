package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Forum implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String question;
	private Integer lik;
	private Integer dislike;
	private double rate;
	
	@OneToMany(mappedBy="idquestion")
	private List<Reponse> reponses;
	
	@ManyToOne
	private User iduser;

	public List<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Integer getLik() {
		return lik;
	}
	public void setLik(Integer lik) {
		this.lik = lik;
	}
	public Integer getDislike() {
		return dislike;
	}
	public void setDislike(Integer dislike) {
		this.dislike = dislike;
	}
	@Override
	public String toString() {
		return "Forum [id=" + id + ", question=" + question + ", lik=" + lik + ", dislike=" + dislike + "]";
	}

	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public User getIduser() {
		return iduser;
	}
	public void setIduser(User iduser) {
		this.iduser = iduser;
	}
	
	
	
	

}
