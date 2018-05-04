package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Reponse implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String rep;
	@ManyToOne
	private User iduser;
	
	@ManyToOne
	private Forum idquestion;
	
	public Reponse() {
	
	}
	public Reponse(String rep2, User iduser2, Forum f) {
		rep=rep2;
		iduser=iduser2;
		idquestion=f;
	}
	public User getIduser() {
		return iduser;
	}
	public void setIduser(User iduser) {
		this.iduser = iduser;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRep() {
		return rep;
	}
	public void setRep(String rep) {
		this.rep = rep;
	}
	@Override
	public String toString() {
		return "Reponse [id=" + id + ", rep=" + rep + "]";
	}
	public Forum getIdquestion() {
		return idquestion;
	}
	public void setIdquestion(Forum idquestion) {
		this.idquestion = idquestion;
	}
	
	

}
