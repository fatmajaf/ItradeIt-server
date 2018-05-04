package tn.esprit.SLTS_server.presentation.mbeans;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.SLTS_server.persistence.Comment;
import tn.esprit.SLTS_server.persistence.User;
import tn.esprit.SLTS_server.services.CommentService;

@ManagedBean
@ViewScoped
public class CommentBean {

	private static User user;
	
	
	@EJB
	CommentService servicecommenr;
	public static void populateuser(User us){
		user= new User();
		user=us;
	}
	@PostConstruct
	public void init() {
		
		/*comments = servicecommenr.viewusercomments(user);
		servicecommenr.getnbbannedcommentsbycommenterid(user.getId());
		//map = SentimentAnalysisWithCount.commentsanalysis(comments);
		/*nbpositivecomments= map.get("positivecomments");
		nbnegativecomments=map.get("negativecomments");
		nbpositivetwitter=map.get("positivetwitter");
		nbnegativetwitter=map.get("negativetwitter");
		nbnegativetotal=nbnegativecomments+nbnegativetwitter;
		nbpositivetotal=nbpositivecomments+nbpositivetwitter;*/

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*public HashMap<String, Integer> getMap() {
	/*	return map;
	}

	public void setMap(HashMap<String, Integer> map) {
		this.map = map;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Long getNbbannedcomments() {
		return nbbannedcomments;
	}

	public void setNbbannedcomments(Long nbbannedcomments) {
		this.nbbannedcomments = nbbannedcomments;
	}

	public int getNbpositivecomments() {
		return nbpositivecomments;
	}

	public void setNbpositivecomments(int nbpositivecomments) {
		this.nbpositivecomments = nbpositivecomments;
	}

	public int getNbnegativecomments() {
		return nbnegativecomments;
	}

	public void setNbnegativecomments(int nbnegativecomments) {
		this.nbnegativecomments = nbnegativecomments;
	}

	public int getNbpositivetwitter() {
		return nbpositivetwitter;
	}

	public void setNbpositivetwitter(int nbpositivetwitter) {
		this.nbpositivetwitter = nbpositivetwitter;
	}

	public int getNbnegativetwitter() {
		return nbnegativetwitter;
	}

	public void setNbnegativetwitter(int nbnegativetwitter) {
		this.nbnegativetwitter = nbnegativetwitter;
	}

	public int getNbnegativetotal() {
		return nbnegativetotal;
	}

	public void setNbnegativetotal(int nbnegativetotal) {
		this.nbnegativetotal = nbnegativetotal;
	}

	public int getNbpositivetotal() {
		return nbpositivetotal;
	}

	public void setNbpositivetotal(int nbpositivetotal) {
		this.nbpositivetotal = nbpositivetotal;
	}

	public CommentService getServicecommenr() {
		return servicecommenr;
	}

	public void setServicecommenr(CommentService servicecommenr) {
		this.servicecommenr = servicecommenr;
	}
	
	*/
}
