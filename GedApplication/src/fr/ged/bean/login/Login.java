/**
 * Managed Bean for JSF
 * Domain class = fr.sfr.bean.login
 * @author SHA
 */
package fr.ged.bean.login;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Employe;
import fr.ged.data.configuration.HibernateSessionFactory;


/**
 * The class Login.
 */


public class Login extends GeneralBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The login. */
	private String login ="";

	/** The password. */
	private String password ="";
	
	public static final String AUTH_KEY = "username";
	 private String role ="";

	/**
	 * Log.
	 * 
	 * @return the string
	 * @author SHA
	 */
	public String log() {
		Session session = HibernateSessionFactory.getSession();

		try {
			Criteria criteriaEaquals = session.createCriteria(Employe.class);
			criteriaEaquals.add(Restrictions.eq("login", login));
			criteriaEaquals.add(Restrictions.eq("password", password));
			List<Employe> list = criteriaEaquals.list();
			Iterator<Employe> itr = list.iterator();
			if (list.size() == 0) {
				sendMessageJSF("login_validationError", LEVEL_ERROR);
				return "LoginFailed";
			}
			while (itr.hasNext()) {
				Employe employe = itr.next();
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
						AUTH_KEY, employe.getNomEmploye()+employe.getPrenomEmploye());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
					"employe", employe);
				this.setRole(employe.getRole().getNomRole());
			}
			return "LoginSuccess";
		}catch(Exception e){
			sendMessageJSF("login_validationError", LEVEL_ERROR);
			return "LoginFailed";
		}finally {
			session.close();
		}

	}

	/**
	 * Log out.
	 * 
	 * @return the string
	 * @author SHA
	 */
	public String logOut() {
		this.login = "";
		this.password = "";
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(AUTH_KEY);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("employe");
		return "LogOutSuccess";

	}
	/**
	 * Initialiser detailRowForSti bean 
	 * @param event
	 */
	public void cleanUpMethod(ActionEvent event){	   
		
	}

	/**
	 * The Constructor.
	 * 
	 * @author SHA
	 */
	public Login() {

	}

	/**
	 * The Constructor.
	 * 
	 * @author SHA
	 * @return
	 */
	public void selectTest() {

	}

	/**
	 * Gets the login.
	 * 
	 * @return the login
	 * @author SHA
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 * 
	 * @param login
	 *            the login to set
	 * @author SHA
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 * @author SHA
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the password to set
	 * @author SHA
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
