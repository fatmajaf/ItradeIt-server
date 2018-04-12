package tn.esprit.SLTS_server.services;

import java.util.List;


import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.Portfolio;
@Remote
public interface Portfolioservice {
	void addportfolio (Portfolio portfolio );
	Portfolio searchbyid (int id);
	void deletePortfolio (int id);
	void updatePortfolio (Portfolio portfolio);
	List<Portfolio> findAllPortfolio();
	public Long calculerStatus1();
	public Long calculerStatus2();
	public Long calculerStatus3();
	public Long calculerStatus4();
	public Long calculerStatus5();
	public Long calculerStatus6();
	
}
