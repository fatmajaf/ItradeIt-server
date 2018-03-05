package usertest;

import javax.ejb.Remote;
import javax.persistence.Entity;

import tn.esprit.SLTS_server.persistence.User;

@Remote
public interface UserServiceRemote {

	public int create(User user);

	public User find(Integer id);
	public User find2(Integer id);
	

	
}
