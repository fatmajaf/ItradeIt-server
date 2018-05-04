package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import tn.esprit.SLTS_server.persistence.Portfolio;
import java.sql.*;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
@LocalBean
@Stateless
public class PortfolioServiceImp implements Portfolioservice {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addportfolio(Portfolio portfolio) {
		
		entityManager.persist(portfolio);

	}

	@Override
	public Portfolio searchbyid(int id) {

		return entityManager.find(Portfolio.class, id);
	}

	@Override
	public void deletePortfolio(int id) {
		entityManager.remove(searchbyid(id));

	}

	@Override
	public void updatePortfolio(Portfolio portfolio) {
		System.out.println("in merge");
		System.out.println(portfolio);
		entityManager.merge(portfolio);

	}

	@Override
	public List<Portfolio> findAllPortfolio() {
		TypedQuery<Portfolio> query = entityManager.createQuery("Select c from Portfolio c", Portfolio.class);
		return query.getResultList();
	}



	@Override
	public Long calculerStatus(String criteria) {
		String jpql="";
		if (criteria.equals("USD"))
		{ jpql= "SELECT count(*) from Portfolio WHERE currency= tn.esprit.SLTS_server.persistence.Currency.USD";}
		else if (criteria.equals("CAD"))
		{jpql = "SELECT count(*) from Portfolio WHERE currency= tn.esprit.SLTS_server.persistence.Currency.CAD";}
		else if (criteria.equals("EUR"))
		{jpql = "SELECT count(*) from Portfolio WHERE currency= tn.esprit.SLTS_server.persistence.Currency.EUR";}
		else if (criteria.equals("TND"))
		{jpql = "SELECT count(*) from Portfolio WHERE currency= tn.esprit.SLTS_server.persistence.Currency.TND";}
		else
		{jpql = "SELECT count(*) from Portfolio WHERE currency= tn.esprit.SLTS_server.persistence.Currency.DZD";}

		Query query = entityManager.createQuery(jpql);
		List<Long> res1 = query.getResultList();
		if ((res1 != null) && (!res1.isEmpty())) {
			return res1.get(0);
		}
		return null;
	}

	

}
