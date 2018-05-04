package tn.esprit.SLTS_server.presentation.mbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.SLTS_server.persistence.Forum;
import tn.esprit.SLTS_server.persistence.Reponse;
import tn.esprit.SLTS_server.persistence.User;
import tn.esprit.SLTS_server.services.ReponseService;

@ManagedBean

public class ReponseBean {
	private Integer id;
	private String rep;
	private User iduser;
	private List<Reponse> reponses;
	private Forum idquestion;
	
	

	@EJB
	private ReponseService service;
	
	public void addrep(Forum f){
		
		service.addAnswer(new Reponse(rep,iduser, f));
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

	public User getIduser() {
		return iduser;
	}

	public void setIduser(User iduser) {
		this.iduser = iduser;
	}
	public Forum getIdquestion() {
		return idquestion;
	}

	public void setIdquestion(Forum idquestion) {
		this.idquestion = idquestion;
	}

	public List<Reponse> getReponses() {
		return reponses=service.findAll();
	}
	public List<Reponse> getReponsesbyQ(int idd) {
		return reponses=service.findAllbyId(idd);
	}
	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}

 
	

}
