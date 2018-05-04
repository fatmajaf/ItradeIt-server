package tn.esprit.SLTS_server.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tn.esprit.SLTS_server.persistence.Trader;
import tn.esprit.SLTS_server.persistence.User;
import tn.esprit.SLTS_server.services.UserService;

@ManagedBean
@ViewScoped
public class UsersBean {
	private List<User> users;
	private String recherche;
	
	@EJB
	UserService service;
	
	@PostConstruct
	public void init(){
		users= new ArrayList<User>();
		String t = "http://localhost:8080/ImagesItradeit/user.png";
		users= service.findAllUsers();
		for (User user : users) {
			if(user.getPhoto()!=null)
				t=user.getPhoto().replace("C:/wamp64/www/","http://localhost:8080/");
			user.setPhoto(t);
			
		}
	}
	public void test(String s){
		System.out.println("here :"+s);
		users=new ArrayList<>();
		users=service.SearchAllUsersStartwith(s);
		String t = "http://localhost:8080/ImagesItradeit/user.png";
		for (User user : users) {
			if(user.getPhoto()!=null)
				t=user.getPhoto().replace("C:/wamp64/www/","http://localhost:8080/");
			user.setPhoto(t);
			
		}
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:uss");
	}
	public void search(){
		
		users=new ArrayList<>();
		users=service.SearchAllUsers(recherche);
		String t = "http://localhost:8080/ImagesItradeit/user.png";
		for (User user : users) {
			if(user.getPhoto()!=null)
				t=user.getPhoto().replace("C:/wamp64/www/","http://localhost:8080/");
			user.setPhoto(t);
			
		}
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:uss");
	}
	private void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public void bantrader(User user){
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
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:uss");
	}
	public void unbantrader(User user){
		System.out.println("ban here  "+user);
		if (user instanceof Trader){
			Trader trader = (Trader) user;
		if( trader.getIsbanned()==0){
			addMessage("Info", "Trader is already active");
		}
		else {
			trader.setIsbanned(0);
			service.updateUser(trader);
			addMessage("Info", "Trader activated");
		}
		
		
		}
		else {
			addMessage("Info", "Only traders can be banned");
		}
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:uss");
	}

	public UserService getService() {
		return service;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getRecherche() {
		return recherche;
	}
	public void setRecherche(String recherche) {
		this.recherche = recherche;
	}
	
}
