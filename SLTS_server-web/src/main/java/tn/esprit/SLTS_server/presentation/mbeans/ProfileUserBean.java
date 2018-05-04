package tn.esprit.SLTS_server.presentation.mbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import javafx.scene.control.Alert;
import tn.esprit.SLTS_server.persistence.Comment;
import tn.esprit.SLTS_server.persistence.Company;
import tn.esprit.SLTS_server.persistence.Customer;
import tn.esprit.SLTS_server.persistence.Trader;
import tn.esprit.SLTS_server.persistence.TradingExchange;
import tn.esprit.SLTS_server.persistence.User;
import tn.esprit.SLTS_server.services.CommentService;
import tn.esprit.SLTS_server.services.UserService;
import tn.esprit.SLTS_server.utils.BadWordFilter;
import tn.esprit.SLTS_server.utils.SentimentAnalysisWithCount;

import twitter4j.TwitterException;

@ManagedBean
@SessionScoped
public class ProfileUserBean {
	private int iduser;
	private User user;
	private String type;

	private HashMap<String, Integer> map;
	private List<Comment> comments;
	private Long nbbannedcomments;
	private int nbpositivecomments;
	private int nbnegativecomments;
	private int nbpositivetwitter;
	private int nbnegativetwitter;
	private int nbnegativetotal;
	private int nbpositivetotal;
	private String photo;
	int[] monthCounter ;
	private List<Customer> customersfortrader;
	private List<TradingExchange> trades;
	private String commentbody;
	private Comment commentedit;
	private List<String> positivetweets;
	private List<String>negativetweets;
	private List<Company>companies;

	@EJB
	UserService service;

	@EJB
	CommentService servicecommentaire;
	
	@ManagedProperty(value="#{loginBean}")
	LoginBean loginBean;
	

	
	public CommentService getServicecommentaire() {
		return servicecommentaire;
	}

	public void setServicecommentaire(CommentService servicecommentaire) {
		this.servicecommentaire = servicecommentaire;
	}
public void preparecomments() throws TwitterException, IOException, NamingException{
	comments = new ArrayList<Comment>();
	comments = servicecommentaire.viewusercomments(user);
	
	String t = "http://localhost:8080/ImagesItradeit/user.png";
	
	
	nbbannedcomments = servicecommentaire.getnbbannedcommentsbycommenterid(iduser);
	comments = servicecommentaire.viewusercomments(user);
	System.out.println(comments);
	map = SentimentAnalysisWithCount.commentsanalysis(comments);
	nbpositivecomments = map.get("positivecomments");
	nbnegativecomments = map.get("negativecomments");
	nbpositivetwitter = map.get("positivetwitter");
	nbnegativetwitter = map.get("negativetwitter");
	nbnegativetotal = nbnegativecomments + nbnegativetwitter;
	nbpositivetotal = nbpositivecomments + nbpositivetwitter;
	
	if (user.getPhoto() == null) {
		photo = "http://localhost:8080/avatar3.png";
	} else {
		photo = user.getPhoto().replace("C:/wamp64/www/", "http://localhost:8080/");
	}
	for (Comment comment : comments) {
		
		String output = BadWordFilter.getCensoredText(comment.getBody());
	
			if (output.contains("*")) {
				comment.setBody(output);
			}
	}
	/*** tweets feed **/
	String negativec = "";
	Integer analysiss;
	String positivec = "";
	positivetweets= new ArrayList<String>();
	negativetweets = new ArrayList<String>();
	System.out.println("-------------------------mape-----------------------");
	for (Map.Entry<String, Integer> mape : map.entrySet())

	{
		System.out.println("key "+ mape.getKey());
		System.out.println("value "+mape.getValue());
		
		
		
		String tt = mape.getKey();
		analysiss = mape.getValue();

		if (analysiss == 1 && (!tt.equals("positivecomments")) && (!tt.equals("negativecomments"))
				&& (!tt.equals("positivetwitter")) && (!tt.equals("negativetwitter"))) {
		
			positivetweets.add(tt);
		} else if (analysiss == 0 && (!tt.equals("positivecomments")) && (!tt.equals("negativecomments"))
				&& (!tt.equals("positivetwitter")) && (!tt.equals("negativetwitter"))) {
		
			negativetweets.add(tt);
		}
		

		
	}
	
	System.out.println("---------------------------mape-----------------------");
	/*** tweets feed **/
}
	public String populateuser(User user) throws TwitterException, IOException, NamingException {
		companies= new ArrayList<>();
		monthCounter= new int[12];
		this.user = new User();
		this.user = user;
		this.iduser = user.getId();
		if (this.user instanceof Trader) {
			type = "trader";
			user = (Trader) user;
			List<Object[]> liste=service.statjsftradestrader(iduser);
			for (Object objects[] : liste) {
				
				monthCounter[Integer.parseInt(objects[1].toString())-1]=
						Integer.parseInt(objects[0].toString());
				
				
			}
			customersfortrader= new ArrayList<>();
			customersfortrader= service.getalltradercustomersbyid(iduser);
			String t = "http://localhost:8080/ImagesItradeit/user.png";
			for (Customer customer : customersfortrader) {
				companies.add(customer.getCompany());
				if(customer.getPhoto()!=null)
					{t=customer.getPhoto().replace("C:/wamp64/www/","http://localhost:8080/");}
				customer.setPhoto(t);
			}
		
			trades=new ArrayList<TradingExchange>();
			trades= service.getalltradesforcustomersbytrader(iduser);
			
		}

		else if (this.user instanceof Customer) {
			type = "customer";
			user = (Customer) user;
			List<Object[]> liste=service.statjsftradescustomers(iduser);
			for (Object objects[] : liste) {
				
				monthCounter[Integer.parseInt(objects[1].toString())-1]=
						Integer.parseInt(objects[0].toString());
				
				
			}
			
			trades=new ArrayList<TradingExchange>();
			trades= service.getalltrades(iduser);
			companies.add(((Customer) user).getCompany());
			
		}

		else {
			type = "Admin";
		}
		preparecomments();
		
		return "/pages/admin/userprofile?faces-redirect=true";
	}
	private void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
public void addcomment() throws TwitterException, IOException, NamingException{
	
	String output = BadWordFilter.getCensoredText(commentbody);

	if (output.contains("*")) {
		
		FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("This comment contains profanity, it won't be diplayed properly for social reasons"));
		
	}
	
	Comment comment = new Comment();
	comment.setCommenter(loginBean.getUs());
	comment.setUser(user);
	comment.setCreationDate(new Date());
	comment.setBody(commentbody);
	int a = servicecommentaire.addComment(comment);
	comment.setId(a);
	commentbody="";
	preparecomments();

}
public void deletecomment(Comment comment) throws TwitterException, IOException, NamingException{

	servicecommentaire.deletecomment(Integer.parseInt(comment.getId().toString()));
	
	preparecomments();
	
}
public void banncomment(Comment comment) throws TwitterException, IOException, NamingException{
	
	servicecommentaire.bancomment(comment.getId());
	preparecomments();
	
}
public void editclickedcomment(Comment comment){
commentedit=new Comment();
commentedit=comment;
System.out.println("the commeeeeeeeeeeeent"+comment);

}
public void updatecomment() throws TwitterException, IOException, NamingException{

	servicecommentaire.updatecommentbody(Integer.parseInt(commentedit.getId().toString()), commentedit.getBody());
	preparecomments();
}
public void banuser(){
	System.out.println("ban here  "+user);
	if (user instanceof Trader){
		Trader trader = (Trader) user;
	if( trader.getIsbanned()==1){
		addMessage("Info", "Trader is already banned");
	}
	else {
		trader.setIsbanned(1);
		service.updateUser(trader);
		addMessage("Info", "Trader banned");
	}
	
	
	}
	else {
		addMessage("Info", "Only traders can be banned");
	}
}
public void unbanuser(){
	System.out.println("ban here  "+user);
	if (user instanceof Trader){
		Trader trader = (Trader) user;
	if( trader.getIsbanned()==0){
		addMessage("Info", "Trader is already active");
	}
	else {
		trader.setIsbanned(0);
		service.updateUser(trader);
		addMessage("Info", "Trader banned");
	}
	
	
	}
	else {
		addMessage("Info", "Only traders can be banned");
	}
}


	public HashMap<String, Integer> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Integer> map) {
		this.map = map;
	}

	public List<String> getPositivetweets() {
		return positivetweets;
	}

	public void setPositivetweets(List<String> positivetweets) {
		this.positivetweets = positivetweets;
	}

	public List<String> getNegativetweets() {
		return negativetweets;
	}

	public void setNegativetweets(List<String> negativetweets) {
		this.negativetweets = negativetweets;
	}

	public List<Comment> getComments() {
		return comments;
	}
	

	public Comment getCommentedit() {
		return commentedit;
	}

	public void setCommentedit(Comment commentedit) {
		this.commentedit = commentedit;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int[] getMonthCounter() {
		return monthCounter;
	}

	public void setMonthCounter(int[] monthCounter) {
		this.monthCounter = monthCounter;
	}

	public List<Customer> getCustomersfortrader() {
		return customersfortrader;
	}

	public void setCustomersfortrader(List<Customer> customersfortrader) {
		this.customersfortrader = customersfortrader;
	}

	public List<TradingExchange> getTrades() {
		return trades;
	}

	public void setTrades(List<TradingExchange> trades) {
		this.trades = trades;
	}

	public String getCommentbody() {
		return commentbody;
	}

	public void setCommentbody(String commentbody) {
		this.commentbody = commentbody;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	

	// http://localhost:18080/SLTS_server-web

}
