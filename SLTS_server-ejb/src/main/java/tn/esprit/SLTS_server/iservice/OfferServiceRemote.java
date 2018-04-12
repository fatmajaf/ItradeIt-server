package tn.esprit.SLTS_server.iservice;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.Offer;

@Remote
public interface OfferServiceRemote {
	
	int addOffre (Offer o,int traderId,int instId);
	int addOffre (Offer o,int traderId);
	void editOffre(Offer o);
	void deleteOffre(int offId);
	void affectTraderToOffre(Integer offId,Integer tradId);
	List<Offer> getAllOffres();
	List<Offer> getOffresByTrader(int traderId);
	Offer getOffer(int offerId);
}
