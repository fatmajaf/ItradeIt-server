package tn.esprit.SLTS_server.iservice;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.swing.text.StyledEditorKit.BoldAction;

import tn.esprit.SLTS_server.persistence.Offer;
import tn.esprit.SLTS_server.persistence.Request;
import tn.esprit.SLTS_server.persistence.Trader;

@Remote
public interface RequestServiceRemote {
	
	void ajouterRequest(int offId,int traderId,Date date);
	Request getRequest(Offer o,Trader t);
	Boolean acceptRequest(Offer o,Trader t);
	void refuseRequest(Offer o,Trader t);
	int getNbreRequest(Trader t);
	List<Request> getAllRequests(int idTrader);
	

}
