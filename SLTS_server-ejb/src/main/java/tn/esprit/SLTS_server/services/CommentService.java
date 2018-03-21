package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.SLTS_server.persistence.Comment;
import tn.esprit.SLTS_server.persistence.User;

@Stateless
public class CommentService implements CommentServiceLocal, CommentServiceRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public int addComment(Comment comment) {
		em.persist(comment);
		return comment.getId();

	}

	@Override
	public List<Comment> viewall() {
		String jpql = "SELECT u FROM Comment u";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public void affectcommenttouser(int idc, int idu) {
		User user = em.find(User.class, idu);
		Comment comment = em.find(Comment.class, idc);

	}

	@Override
	public List<Comment> viewusercomments(User u) {
		String jpql = "SELECT c FROM Comment c where c.user=:u";
		Query query = em.createQuery(jpql);
		query.setParameter("u", u);
		return query.getResultList();
	}

	@Override
	public int deletecomment(int idc) {
		
        String jpql = "DELETE FROM Comment c where c.id=:idc";
		Query query = em.createQuery(jpql);
		query.setParameter("idc", idc);
		return (query.executeUpdate());

	}

	@Override
	public Comment findCommentById(int id) {
		return em.find(Comment.class, id);
	}

	@Override
	public void deleteComment(Comment c) {
		Comment co=em.find(Comment.class, c.getId());
		co.setCommenter(null);
		co.setUser(null);
		em.remove(co);
		
	}

	@Override
	public int deleteallusercomments(User u) {
		 String jpql = "DELETE FROM Comment c where c.user=:u";
			Query query = em.createQuery(jpql);
			query.setParameter("u", u);
			return (query.executeUpdate());
	}

	@Override
	public void updatecommentbody(int idc, String body) {
		
		 String jpql = "Update Comment c set c.body=:body where c.id=:idc";
			Query query = em.createQuery(jpql);
			query.setParameter("body", body);
			query.setParameter("idc", idc);
			query.executeUpdate();
	}

}
