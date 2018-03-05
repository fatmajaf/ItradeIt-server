package usertest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.SLTS_server.persistence.Instrument;
import tn.esprit.SLTS_server.persistence.User;
@Stateless
public class UserService implements UserServiceRemote{

	@PersistenceContext(unitName="SLTS_server-ejb")
	private EntityManager em;
	public UserService(){
		
	}
	@Override
	public int create(User user){
		em.persist(user);
		em.refresh(user);
		return user.getId();
	}
	@Override
	public User find(Integer id){
		User user=(User)em.find(User.class,id);
		return user;
		
	}
	public User find2(Integer id){
		User user=(User)em.find(User.class,id);
		User enis=new User();
		enis.setAddress(user.getAddress());
		enis.setBirthdate(user.getBirthdate());
		enis.setCreationDate(user.getCreationDate());
		enis.setEmail(user.getEmail());
		enis.setFirstName(user.getFirstName());
		enis.setLogin(user.getLogin());
		enis.setNationality(user.getNationality());
		return enis;
		
	}
	
	
	
	
	
	
}
