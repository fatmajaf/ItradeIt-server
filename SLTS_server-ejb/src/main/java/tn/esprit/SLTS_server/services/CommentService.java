package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.SLTS_server.persistence.Comment;
import tn.esprit.SLTS_server.persistence.User;

@Stateless
@LocalBean
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
		Comment co = em.find(Comment.class, c.getId());
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

	@Override
	public void bancomment(int idc) {
		String jpql = "Update Comment c set c.banned=1 where c.id=:idc";
		Query query = em.createQuery(jpql);
		query.setParameter("idc", idc);
		query.executeUpdate();

	}

	@Override
	public User getmostbannedcommenter() {
		String jpql = "SELECT u.commenter from Comment u  where u.banned=1 and MONTH(u.creationDate)=MONTH(current_date()) and year(u.creationDate) = year(current_date()) group by u.commenter.id order by count(*) desc";

		Query query = em.createQuery(jpql);

		List<User> res = query.getResultList();
		if ((res != null) && (!res.isEmpty())) {
			return res.get(0);
		}
		return null;

	}

	@Override
	public Long getnbmaxbannedcomments() {
		String jpql = "SELECT count(*) from Comment u where u.banned=1 and MONTH(u.creationDate)=MONTH(current_date()) and year(u.creationDate) = year(current_date())group by u.commenter.id order by count(*) desc";
		Query query = em.createQuery(jpql);
		List<Long> res = query.getResultList();
		if ((res != null) && (!res.isEmpty())) {
			return res.get(0);
		}
		return null;

	}

	@Override
	public Long totalbannedcommentscurmonth() {
		String jpql = "SELECT count(*) from Comment u where u.banned=1 and MONTH(u.creationDate)=MONTH(current_date()) and year(u.creationDate) = year(current_date())";
		Query query = em.createQuery(jpql);
		List<Long> res = query.getResultList();
		if ((res != null) && (!res.isEmpty())) {
			return res.get(0);
		}
		return null;
	}

	@Override
	public Long getnbbannedcommentsbycommenterid(int idc) {
		String jpql = "SELECT count(*) from Comment u where u.banned=1 and u.commenter.id=:idc";
		Query query = em.createQuery(jpql);
		query.setParameter("idc", idc);
		List<Long> res = query.getResultList();
		if ((res != null) && (!res.isEmpty())) {
			return res.get(0);
		}
		return null;
	}

}
