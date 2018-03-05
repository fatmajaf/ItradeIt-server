package tn.esprit.SLTS_server.iservice;

import javax.ejb.Remote;

@Remote
public interface InterestedByServiceRemote {

	void ajouterInterestedBy(int offId,int clientId,int note);
}
