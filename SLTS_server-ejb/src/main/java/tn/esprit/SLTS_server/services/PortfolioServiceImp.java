package tn.esprit.SLTS_server.services;

import java.util.List;

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



@Stateless
public class PortfolioServiceImp implements Portfolioservice{

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
		entityManager.merge(portfolio);
		
	}

	@Override
	public List<Portfolio> findAllPortfolio() {
		TypedQuery<Portfolio> query = entityManager.createQuery("Select c from Portfolio c", Portfolio.class);
		return query.getResultList();
	}

	@Override
	public Long calculerStatus1() {
		String jpql = "SELECT count(*) from Portfolio WHERE currency=DZD " ;
		Query query = entityManager
				.createQuery(jpql);
		List<Long> res1 = query.getResultList();
		if ((res1 != null) && (!res1.isEmpty())) {
			return res1.get(0);
		}
		return null;
	}

	@Override
	public Long calculerStatus2() {
		String jpql = "SELECT count(*) from Portfolio WHERE currency=USD " ;
		Query query = entityManager
				.createQuery(jpql);
		List<Long> res1 = query.getResultList();
		if ((res1 != null) && (!res1.isEmpty())) {
			return res1.get(0);
		}
		return null;
	}

	@Override
	public Long calculerStatus3() {
		String jpql = "SELECT count(*) from Portfolio WHERE currency=EUR " ;
		Query query = entityManager
				.createQuery(jpql);
		List<Long> res1 = query.getResultList();
		if ((res1 != null) && (!res1.isEmpty())) {
			return res1.get(0);
		}
		return null;
	}

	@Override
	public Long calculerStatus4() {
		String jpql = "SELECT count(*) from Portfolio WHERE currency=CAD " ;
		Query query = entityManager
				.createQuery(jpql);
		List<Long> res1 = query.getResultList();
		if ((res1 != null) && (!res1.isEmpty())) {
			return res1.get(0);
		}
		return null;
	}

	@Override
	public Long calculerStatus5() {
		String jpql = "SELECT count(*) from Portfolio WHERE currency=BRL " ;
		Query query = entityManager
				.createQuery(jpql);
		List<Long> res1 = query.getResultList();
		if ((res1 != null) && (!res1.isEmpty())) {
			return res1.get(0);
		}
		return null;
	}

	@Override
	public Long calculerStatus6() {
		String jpql = "SELECT count(*) from Portfolio WHERE currency=TND " ;
		Query query = entityManager
				.createQuery(jpql);
		List<Long> res1 = query.getResultList();
		if ((res1 != null) && (!res1.isEmpty())) {
			return res1.get(0);
		}
		return null;
	}

	

}
