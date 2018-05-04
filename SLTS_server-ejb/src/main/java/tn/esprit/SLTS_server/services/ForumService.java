package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.SLTS_server.persistence.Forum;
import tn.esprit.SLTS_server.persistence.Portfolio;
@LocalBean
@Stateless
public class ForumService implements IForumService {
	@PersistenceContext
	EntityManager entityManager;
	

	@Override
	public void addQuestion(Forum f) {
		System.out.println("siys");
		entityManager.persist(f);
		
	}

	@Override
	public void updatQuestion(Forum f) {
		entityManager.merge(f);
		
	}

	@Override
	public void delete(int id) {
		entityManager.remove(searchbyid(id));
		
	}

	@Override
	public Forum searchbyid(int id) {
		
		return entityManager.find(Forum.class, id);
	}
	@Override
	public List<Forum> findAll() {
		TypedQuery<Forum> query = entityManager.createQuery("Select c from Forum c", Forum.class);
		return query.getResultList();
	}


}
