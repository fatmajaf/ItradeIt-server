package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Comment implements Serializable {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
    private String body;
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	@OneToOne
	private User commenter;
	
	@ManyToOne
	private User user;
	
	
	public Integer getId() {
		return id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public User getCommenter() {
		return commenter;
	}



	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}



	@Override
	public String toString() {
		return "Comment [id=" + id + ", body=" + body + ", creationDate=" + creationDate + ", commenter=" + commenter
				+ "]";
	}
	
    
	
}
