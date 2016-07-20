/**
 * Managed Bean for JSF
 * @author SHA
 */
package fr.ged.bean.employe;

import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Employe;
import fr.ged.data.Role;
import fr.ged.data.dao.EmployeDAO;
import fr.ged.data.dao.RoleDAO;

/**
 * The class Employes.
 */
public class Employes extends GeneralBean {


	 private Integer idEmploye;
     private String nomEmploye;
     private String prenomEmploye;
     private String cinEmploye;
     private String adresseEmploye;
     private String login;
     private String password; 
     private  EmployeDAO employeDAO;
     private Role role;
     private RoleDAO roleDAO;
     private Integer idRole ;
     

 
     private Integer idRoleSelected ;
     
     private Employe employeSelected;

    /**
     * Gets the Employes
     * 
     * @return the Employes
     * @author SHA
     */
    public List getEmployes() {
    	List<Employe> l = null;
    	if(this.employeDAO!= null ){
    		   l = this.employeDAO.getHibernateTemplate().find("From Employe ORDER BY idEmploye DESC", null);
    	}
      
        return l;
    }

    /**
     * Gets the Employes as items.
     * 
     * @return the Employes as items
     * @author SHA
     */
    public List getEmployesAsItems() {
        List l = this.getEmployes();
        List employesItems = new LinkedList();
        if (l != null && !l.isEmpty()) {
            for (int i = 0; i < l.size(); i++) {
                // TODO
                SelectItem SelectItem = new SelectItem(((Employe) l.get(i)), ((Employe) l.get(i)).getNomEmploye()+" "+((Employe) l.get(i)).getPrenomEmploye(), "", false);
                employesItems.add(SelectItem);
            }
        }
        return employesItems;
    }

    /**
     * Adds the Employes.
     * 
     * @return the string
     * @author SHA
     */
    public String addEmploye() {
        try {
            Employe employe = new Employe();
            employe.setAdresseEmploye(this.getAdresseEmploye());
            employe.setCinEmploye(this.getCinEmploye());
            employe.setLogin(this.getLogin());
            employe.setNomEmploye(this.getNomEmploye());
            employe.setPassword(this.getPassword());
            employe.setPrenomEmploye(this.getPrenomEmploye());
            this.setRole(this.roleDAO.findById(this.getIdRole()));
            employe.setRole(this.getRole());
            this.employeDAO.save(employe);  
            this.employeDAO.getHibernateTemplate().clear();
            this.employeDAO.getHibernateTemplate().flush();
            this.setAdresseEmploye("");
            this.setCinEmploye("");
            this.setLogin("");
            this.setNomEmploye("");
            this.setPassword("");
            this.setPrenomEmploye("");
            this.setIdRole(0);
        } catch (Exception e) {
            sendMessageJSF("employe_addEmployeError", LEVEL_ERROR);
        }
       
        return "AddEmployeSuccess";

    }
    
    /**
     * Update Employes.
     */
    public void updateEmploye() {
        try {
        	
        	 this.employeDAO.getHibernateTemplate().getSessionFactory().openSession().beginTransaction();
        	  this.employeSelected.setRole(this.roleDAO.findById(this.getIdRoleSelected()));
        	  this.employeDAO.merge(this.employeSelected);   
         	 this.employeDAO.getHibernateTemplate().clear();
             this.employeDAO.getHibernateTemplate().flush();
         	 
        } catch (Exception e) {
            sendMessageJSF("employe_updateEmployeError", LEVEL_ERROR);
        }
    }

    /**
     * Delete Employes.
     */
    public void deleteEmploye() {
        try {
        	 this.employeDAO.delete(this.getEmployeSelected());   
        	 this.employeDAO.getHibernateTemplate().clear();
             this.employeDAO.getHibernateTemplate().flush();
        } catch (Exception e) {
           sendMessageJSF("employe_deleteEmployeError", LEVEL_ERROR);
        }
    }

	public Integer getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(Integer idEmploye) {
		this.idEmploye = idEmploye;
	}

	public String getNomEmploye() {
		return nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public String getPrenomEmploye() {
		return prenomEmploye;
	}

	public void setPrenomEmploye(String prenomEmploye) {
		this.prenomEmploye = prenomEmploye;
	}

	public String getCinEmploye() {
		return cinEmploye;
	}

	public void setCinEmploye(String cinEmploye) {
		this.cinEmploye = cinEmploye;
	}

	public String getAdresseEmploye() {
		return adresseEmploye;
	}

	public void setAdresseEmploye(String adresseEmploye) {
		this.adresseEmploye = adresseEmploye;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setEmployeDAO(EmployeDAO employeDAO) {
		this.employeDAO = employeDAO;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public Employe getEmployeSelected() {
		return employeSelected;
	}

	public void setEmployeSelected(Employe employeSelected) {
		this.employeSelected = employeSelected;
		this.setIdRoleSelected(employeSelected.getRole().getIdRole());
	}

	
	public Integer getIdRoleSelected() {
		return idRoleSelected;
	}

	public void setIdRoleSelected(Integer idRoleSelected) {
		this.idRoleSelected = idRoleSelected;
	}
	
	

}
