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
	
	void addRequest(int offId,int traderId,Date date);
	Request getRequest(int o,int t);
	Boolean acceptRequest(int o,int t);
	void refuseRequest(int o,int t);
	List<Request> getAllRequests(int idTrader);
	List<Request> getMyRequests(int idTrader);
	int getNbreRequest(int traderId);
	int getNbreMyRequest(int traderId);
	

}
