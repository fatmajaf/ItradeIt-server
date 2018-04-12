package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.Forum;
import tn.esprit.SLTS_server.persistence.Portfolio;
@Remote
public interface IForumService {
	
	void addQuestion ( Forum f);
	void updatQuestion (Forum f);
	Forum searchbyid(int id);
	void delete(int id);
	public List<Forum> findAll();
	
}
