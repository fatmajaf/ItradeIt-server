package tn.esprit.SLTS_server.iservice;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.History;

@Remote
public interface HistoryServiceRemote {
	
	Integer addHistory(History h);
	void affectHistoryToCompany(Integer h,Integer c);
	List<History> getAllHistory();
	History getHistory(Integer id);
	List<History>getHistoryByCompany(Integer companyId);
	List<History>getHistoryByCompanyAndDate(Integer companyId,Date d);

}
