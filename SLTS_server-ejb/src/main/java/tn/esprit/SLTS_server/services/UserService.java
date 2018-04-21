package tn.esprit.SLTS_server.services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tn.esprit.SLTS_server.persistence.Customer;
import tn.esprit.SLTS_server.persistence.Trader;
import tn.esprit.SLTS_server.persistence.TradingExchange;
import tn.esprit.SLTS_server.persistence.User;

@Stateless
@LocalBean
public class UserService implements UserServiceRemote, UserServiceLocal {
	@PersistenceContext
	private EntityManager em;

	@Override
	public User login(User user) {
		Query query = em.createQuery("SELECT u from User u where u.login=:login and u.password=:password");
		query.setParameter("login", user.getLogin());
		query.setParameter("password", user.getPassword());

		List<User> res = query.getResultList();
		if ((res != null) && (res.isEmpty() == false)) {
			System.out.println(res.get(0));
			return res.get(0);
			
		}
		return null;
	}

	public void ajouterUser(User user) {
		em.persist(user.getAddress());
		em.persist(user);
	}

	@Override
	public List<User> findAllUsers() {
		String jpql = "SELECT u FROM User u";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public List<User> findAllTraders() {
		String jpql = "SELECT t FROM Trader t";
		Query query = em.createQuery(jpql);

		return query.getResultList();
	}

	@Override
	public List<User> findAllCustomers() {
		String jpql = "SELECT c FROM Customer c order by c.creationDate desc";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public User findUserById(int id) {
		return em.find(User.class, id);
	}

	@Override
	public void updateUser(User user) {
		em.merge(user);
	}

	

	
	
	@Override
	public void deleteUser(User user) {
		em.remove(user);
	}

	@Override
	public void deleteUserById(int id) {
		em.remove(findUserById(id));
	}

	@Override
	public List<User> SearchAllUsers(String criteria) {
		String jpql = "SELECT u FROM User u where u.firstName like :criteria or u.lastName like :criteria or u.login like :criteria or u.nationality like :criteria or u.email like :criteria or u.creationDate like :criteria or u.birthdate like :criteria or u.phone like :criteria";

		Query query = em.createQuery(jpql);
		query.setParameter("criteria", "%" + criteria + "%");
		return query.getResultList();
	}

	@Override
	public Long getNbCustomersNotActivated() {
		String jpql = "SELECT count(u) FROM Customer u where u.isactive=0";
		Query query = em.createQuery(jpql);
		return (Long) query.getSingleResult();
	}

	@Override
	public Long getNbBannedTraders() {
		String jpql = "SELECT count(u) FROM Trader u where u.isbanned=1";
		Query query = em.createQuery(jpql);
		return (Long) query.getSingleResult();
	}

	@Override
	public List<Customer> getalltradercustomersbyid(int id) {
		String jpql = "SELECT u.customers FROM Trader u where u.id=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<TradingExchange> getalltrades(int id) {
		String jpql = "SELECT u FROM TradingExchange u where u.user.id=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<TradingExchange> getalltradesforcustomersbytrader(int id) {
		String jpql = "SELECT u FROM TradingExchange u where u.user.trader.id=:id order by u.creationDate desc";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public User findUserByphoneNumber(Integer phone) {
		Query query = em.createQuery("SELECT u from User u where u.phone=:phone");

		query.setParameter("phone", phone);

		List<User> res = query.getResultList();
		if ((res != null) && (!res.isEmpty())) {
			return res.get(0);
		}
		return null;
	}

	@Override
	public Trader findtraderactivelazynbtrades(String criteria) {
		String jpql= "SELECT u.user.trader from TradingExchange u join u.user c join c.trader group by u.user.id order by count(*)"
				+ criteria;
		Query query = em.createQuery(jpql
				);

	
		List<Trader> res = query.getResultList();
		if ((res != null) && (!res.isEmpty())) {
			return res.get(0);
		}
		return null;
	}

	@Override
	public Long getnbtradesforactivelazytrader(String criteria) {
String jpql = "SELECT count(*) from TradingExchange u group by u.user.id order by count(*)" + criteria;
		Query query = em
				.createQuery(jpql);
		List<Long> res = query.getResultList();
		if ((res != null) && (!res.isEmpty())) {
			return res.get(0);
		}
		return null;

	}
	@Override
	public List<User> findAlldesactiatedCustomers() {
		String jpql = "SELECT c FROM Customer c where c.isactive=0";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public void updatephotouserbyid(int idu, String photo) {
		String jpql = "Update User c set c.photo=:photo where c.id=:idu";
		Query query = em.createQuery(jpql);
		query.setParameter("photo", photo);
		query.setParameter("idu", idu);
		query.executeUpdate();
		
	}

	

}
