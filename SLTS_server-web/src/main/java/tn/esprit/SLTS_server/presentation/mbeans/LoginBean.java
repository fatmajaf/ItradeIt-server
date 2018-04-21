package tn.esprit.SLTS_server.presentation.mbeans;

import java.security.MessageDigest;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import sun.misc.BASE64Encoder;
import tn.esprit.SLTS_server.persistence.Admin;
import tn.esprit.SLTS_server.persistence.User;
import tn.esprit.SLTS_server.services.UserService;

@ManagedBean
@ApplicationScoped
public class LoginBean {
	private String login;
	private String password;
	private User us;
	private boolean loggedIn;

	@EJB
	UserService service;
	private void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String verifConnexion() throws NamingException {
		String  navigateTo = null;
		if (login.equals("") && password.equals(""))

			addMessage("Info", "Please enter your login and password");
			
		else
			us= new User();
			us.setPassword(encryptLdapPassword("SHA",password));
			us.setLogin(login);
			User user = service.login(us);
			if (user == null || (!loginfromLDAP(password, login))) 
				addMessage("Info", "Login or password incorrect");
				
			else 
				us=user;
				if (us instanceof Admin)
					loggedIn=true;
					addMessage("Info", "welcome");
					navigateTo = "/pages/admin/home?faces-redirect=true";
	   return navigateTo;
				
			

	}
	 private String encryptLdapPassword(String algorithm, String password) {
	        String sEncrypted = password;
	        if ((password != null) && (password.length() > 0)) {
	            boolean bMD5 = algorithm.equalsIgnoreCase("MD5");
	            boolean bSHA = algorithm.equalsIgnoreCase("SHA")
	                    || algorithm.equalsIgnoreCase("SHA1")
	                    || algorithm.equalsIgnoreCase("SHA-1");
	            if (bSHA || bMD5) {
	                String sAlgorithm = "MD5";
	                if (bSHA) {
	                    sAlgorithm = "SHA";
	                }
	                try {
	                    MessageDigest md = MessageDigest.getInstance(sAlgorithm);
	                    md.update(password.getBytes("UTF-8"));
	                    sEncrypted = "{" + sAlgorithm + "}" + (new BASE64Encoder()).encode(md.digest());
	                } catch (Exception e) {
	                    sEncrypted = null;
	                   
	                }
	            }
	        }
	        return sEncrypted;
	    }
	public boolean loginfromLDAP(String passw,String username) throws NamingException
	{
		
		Properties initialProperties = new Properties();
		initialProperties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		initialProperties.put(Context.PROVIDER_URL, "ldap://localhost:10389");
		initialProperties.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		initialProperties.put(Context.SECURITY_CREDENTIALS, "secret");
		DirContext  context = new InitialDirContext(initialProperties);
		
		String searchFilter="(objectClass=inetOrgPerson)";
		String[] requiredAttributes={"sn","cn","employeeNumber" , "userPassword" };
		SearchControls controls=new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		controls.setReturningAttributes(requiredAttributes);
		NamingEnumeration users=context.search("ou=users,o=itradeit", searchFilter, controls);
		SearchResult searchResult=null;
		String commonName=null;
		String surName=null;
		String employeeNum=null;
		String userp=null;
		while(users.hasMore())
		{
			
			searchResult=(SearchResult) users.next();
			Attributes attr=searchResult.getAttributes();
			
			commonName=attr.get("cn").get(0).toString();
			/**surName=attr.get("sn").get(0).toString();***/

			/***employeeNum=attr.get("employeeNumber").get(0).toString();***/
			/***System.out.println("Name = "+commonName);
			System.out.println("Surname  = "+surName);
			System.out.println("Employee number = "+employeeNum);*****/
			Attribute pwd = attr.get("userPassword");
	        String pass= new String((byte[])pwd.get());
	       /**** System.out.println("=> userPassword : " + pass);
			System.out.println("---------------------------"+attr.get("userpassword").get(0).toString()+"---------------");****/
			String passwsha=encryptLdapPassword("SHA",passw);
			if (pass.equals(passwsha) && commonName.equals(username)){
				
				return true;
			}
			
		}
		return false;
	}

	
	public UserService getService() {
		return service;
	}
	public User getUs() {
		return us;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	public void setUs(User us) {
		this.us = us;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
