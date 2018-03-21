package tn.esprit.SLTS_server.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.Comment;
import tn.esprit.SLTS_server.persistence.User;


@Remote
public interface CommentServiceRemote {
	public int 	addComment(Comment comment);
	public List<Comment> viewall();
	public void affectcommenttouser(int idc , int idu);
	public 	List<Comment> viewusercomments(User u);
	public int deletecomment(int idc);
	public Comment findCommentById(int id);
	public void deleteComment(Comment c) ;
	public int deleteallusercomments(User u);
	public void updatecommentbody(int idc, String body);
}
