package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.SLTS_server.iservice.CompanyServiceRemote;
import tn.esprit.SLTS_server.persistence.Activity;
import tn.esprit.SLTS_server.persistence.Company;
import tn.esprit.SLTS_server.persistence.Customer;
import tn.esprit.SLTS_server.persistence.JuridicalStatus;
import tn.esprit.SLTS_server.persistence.Sector;
import tn.esprit.SLTS_server.persistence.Size;
import tn.esprit.SLTS_server.persistence.User;
@Stateless
public class CompanyService implements CompanyServiceRemote{

	@PersistenceContext(unitName="SLTS_server-ejb")
	EntityManager em;
	
	@Override
	public Integer addCompany(Company company) {
		em.persist(company);
		return company.getId();
	}

	@Override
	public void deleteCompany(Integer id) {
		Company c=em.find(Company.class,id);
		em.remove(c);
		
	}

	@Override
	public void editCompany(Company company) {
		em.merge(company);
	}

	@Override
	public Company getCompany(Integer id) {
		return em.find(Company.class,id);
		
	}

	@Override
	public List<Company> getAllCompany() {
		Query query=em.createQuery("select c From Company c");
		return query.getResultList();
	}

	@Override
	public List<Company> getAllCompanySortByRevenue() {
		Query query=em.createQuery("select c From Company c order by c.revenue desc");
		return query.getResultList();
	}

	@Override
	public List<Company> findCompany(String rech) {
		Query query=em.createQuery("select c From Company c where c.name like :param or c.address like :param or c.email like :param or c.webSite like :param ");
		query.setParameter("param","%"+rech+"%");
		return query.getResultList();
	}

	@Override
	public void affectComapnyToCustomer(Integer companyId, Integer customerId) {
		Company c=em.find(Company.class,companyId);
		Customer u=em.find(Customer.class,customerId);
		c.setCustomer(u);
		
		
	}

	@Override
	public Company getCompanyByCustomer(Integer customerId) {
		Query query=em.createQuery("select c From Company c where c.customer.id=:param");
		query.setParameter("param",customerId);
		return (Company)query.getSingleResult();
	}

	@Override
	public List<Company> getCompanyByActivity(Activity a) {
		Query query=em.createQuery("select c From Company c where c.activity=:param");
		query.setParameter("param",a);
		return query.getResultList();
	}

	@Override
	public List<Company> getCompanyBySize(Size s) {
		Query query=em.createQuery("select c From Company c where c.companySize=:param");
		query.setParameter("param",s);
		return query.getResultList();
	}

	@Override
	public List<Company> getCompanyByStatus(JuridicalStatus s) {
		Query query=em.createQuery("select c From Company c where c.companySize=:param");
		query.setParameter("param",s);
		return query.getResultList();
	}

	@Override
	public List<Company> getCompanyBySector(Sector s) {
		Query query=em.createQuery("select c From Company c where c.sector=:param");
		query.setParameter("param",s);
		return query.getResultList();
	}

	@Override
	public int getNbreCompanyByActivity(Activity a) {
		return getCompanyByActivity(a).size();
	}

	@Override
	public int getNbreCompanyBySize(Size s) {
		return getCompanyBySize(s).size();
	}

	@Override
	public int getNbreCompanyByStatus(JuridicalStatus s) {
		return getCompanyByStatus(s).size();
	}

	@Override
	public int getNbreCompanyBySector(Sector s) {
		return getCompanyBySector(s).size();
	}

	@Override
	public List<Company> getCompanyFollowed(int userId) {
		Query query=em.createQuery("select w.company From WatchList w join w.company c join w.user u where u.id=:param");
		query.setParameter("param",userId);
		return query.getResultList();
	}

}
