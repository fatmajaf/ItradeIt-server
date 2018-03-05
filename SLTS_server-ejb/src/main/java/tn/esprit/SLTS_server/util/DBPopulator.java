package tn.esprit.SLTS_server.util;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import tn.esprit.SLTS_server.persistence.Address;
import tn.esprit.SLTS_server.persistence.Trader;
import tn.esprit.SLTS_server.services.UserServiceLocal;



@Singleton
@Startup
public class DBPopulator {
	@EJB
	private UserServiceLocal userServiceLocal;
	
	public DBPopulator() {
	}
	
	@PostConstruct
	public void init(){
	    Trader  trader= new Trader();
	    Address address= new Address();
	    trader.setBirthdate(new Date("2014/02/02"));
	    trader.setLastName("test");
	    trader.setFirstName("j");
	    trader.setEmail("b");
	    trader.setLogin("tttttt");
	    trader.setPassword("htest");
	    
	//userServiceLocal.ajouterUser(trader);
	/*Trader t= (Trader) userServiceLocal.login(trader);
	if (t==null){
		System.out.print("noonoooooooooooooooooooo");
	}
	else System.out.print("yeaaaaaaaaaaaa");*/
	
	
	}

}
