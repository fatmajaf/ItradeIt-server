package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Local;
//import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.Portfolio;
@Local
public interface Portfolioservice {
	void addportfolio (Portfolio portfolio );
	Portfolio searchbyid (int id);
	void deletePortfolio (int id);
	void updatePortfolio (Portfolio portfolio);
	List<Portfolio> findAllPortfolio();
	public Long calculerStatus(String criteria);
	
	
}
