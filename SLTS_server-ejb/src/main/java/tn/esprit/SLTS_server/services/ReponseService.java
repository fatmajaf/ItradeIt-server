package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.SLTS_server.persistence.Forum;
import tn.esprit.SLTS_server.persistence.Portfolio;
import tn.esprit.SLTS_server.persistence.Reponse;
@LocalBean
@Stateless
public class ReponseService implements IReponseService{
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addAnswer(Reponse r) {
		entityManager.persist(r);
		
	}

	@Override
	public void updatAnswer(Reponse r) {
		entityManager.merge(r);
		
	}

	@Override
	public Reponse searchbyid(int id) {

		return entityManager.find(Reponse.class, id);
	}

	@Override
	public void delete(int id) {
		entityManager.remove(searchbyid(id));
		
	}

	@Override
	public List<Reponse> findAll() {
		TypedQuery<Reponse> query = entityManager.createQuery("Select c from Reponse c", Reponse.class);
		return query.getResultList();
	}

	@Override
	public List<Reponse> findAllbyId(int iddd) {
		Forum f = entityManager.find(Forum.class, iddd);
		TypedQuery<Reponse> query = entityManager.createQuery("Select c from Reponse c where c.idquestion =:f  ", Reponse.class);
		query.setParameter("f", f);
		return  query.getResultList() ;
	
	}
}
