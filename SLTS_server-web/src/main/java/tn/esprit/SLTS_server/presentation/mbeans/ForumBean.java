package tn.esprit.SLTS_server.presentation.mbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import tn.esprit.SLTS_server.persistence.Admin;
import tn.esprit.SLTS_server.persistence.Forum;
import tn.esprit.SLTS_server.persistence.User;
import tn.esprit.SLTS_server.services.ForumService;

@ManagedBean
public class ForumBean {
	private Integer id;
	private String question;
	private Integer lik;
	private Integer dislike;
	private double rate;
	private List<Forum> listquestion;

	@EJB
	private ForumService service;

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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public List<Forum> getListquestion() {

		return listquestion = service.findAll();
	}

	public void setListquestion(List<Forum> listquestion) {
		this.listquestion = listquestion;
	}

	public void addquestion() {
		service.addQuestion(new Forum(question));
	}

	public void MiseJour() {
		service.updatQuestion(new Forum(question));
	}

	public void delete(int id) {
		service.delete(id);
	}

	public String gotochat() {
		String navigateTo = null;

		navigateTo = "/index2?faces-redirect=true";
		return navigateTo;
	}
	

}
