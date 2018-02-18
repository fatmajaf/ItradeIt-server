package tn.esprit.SLTS_server.persistence;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue(value="admin")
public class Admin extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
