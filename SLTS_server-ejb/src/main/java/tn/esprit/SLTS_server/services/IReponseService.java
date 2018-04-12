package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Remote;


import tn.esprit.SLTS_server.persistence.Reponse;
@Remote
public interface IReponseService {
	
	void addAnswer ( Reponse r);
	void updatAnswer (Reponse r);
	Reponse  searchbyid(int id);
	void delete(int id);
	List<Reponse> findAll();
	List<Reponse> findAllbyId(int idd);


}
