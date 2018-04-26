package tn.esprit.SLTS_server.iservice;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.Activity;
import tn.esprit.SLTS_server.persistence.Company;
import tn.esprit.SLTS_server.persistence.JuridicalStatus;
import tn.esprit.SLTS_server.persistence.Sector;
import tn.esprit.SLTS_server.persistence.Size;

@Remote
public interface CompanyServiceRemote {
	
	Integer addCompany(Company company);
	void deleteCompany(Integer id);
	void editCompany(Company company);
	Company getCompany(Integer id);
	List<Company> getAllCompany();
	List<Company> getAllCompanySortByRevenue();
	List<Company> findCompany(String rech);
	void affectComapnyToCustomer(Integer companyId,Integer customerId);
	Company getCompanyByCustomer(Integer customerId);
	List<Company> getCompanyByActivity(Activity a);
	List<Company> getCompanyBySize(Size s);
	List<Company> getCompanyByStatus(JuridicalStatus s);
	List<Company> getCompanyBySector(Sector s);
	int getNbreCompanyByActivity(Activity a);
	int getNbreCompanyBySize(Size s);
	int getNbreCompanyByStatus(JuridicalStatus s);
	int getNbreCompanyBySector(Sector s);
	List<Company> getCompanyFollowed(int userId);
	
}
