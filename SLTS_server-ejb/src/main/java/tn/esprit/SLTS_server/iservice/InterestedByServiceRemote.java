package tn.esprit.SLTS_server.iservice;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.InteresstedBy;

@Remote
public interface InterestedByServiceRemote {

	InteresstedBy getInteresstedBy(int offId,int clientId);
	void addInterestedBy(int offId,int clientId,int note);
	void deleteInterestedBy(int offId,int clientId);
	void updateNote(int offId,int clientId,int note);
	Boolean isInterestedBy(int offId,int clientId);
	List<InteresstedBy> getAllInterestedBy(int traderId);
	List<InteresstedBy> getInterestedByClient(int clientId);
	List<InteresstedBy> getMyInterestedByLevel(int note,int traderId);
	float getMoy(int clientId,int offerId);
	
}
