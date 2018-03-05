package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.SLTS_server.persistence.Customer;
import tn.esprit.SLTS_server.persistence.Trader;
import tn.esprit.SLTS_server.persistence.TradingExchange;
import tn.esprit.SLTS_server.persistence.User;

@Local
public interface UserServiceLocal {
	public User login(User user);
	 public void ajouterUser(User user);
	 public List<User> findAllUsers();
	 public List<User> findAllTraders();
	 public List<User> findAllCustomers();
	public User findUserById(int id);
	 
	// public List<User> findAllCompanies();
	public void updateUser(User user);
	void deleteUser(User user);
	void deleteUserById(int id);
	List<User> SearchAllUsers(String criteria);
	public Long getNbCustomersNotActivated();
	public Long getNbBannedTraders();
	List<Customer> getalltradercustomersbyid(int id);
	List<TradingExchange> getalltrades(int id);
	public List<TradingExchange> getalltradesforcustomersbytrader(int id);
	User findUserByphoneNumber(Integer phone);
}
