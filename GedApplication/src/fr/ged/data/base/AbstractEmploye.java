package fr.ged.data.base;

import java.util.HashSet;
import java.util.Set;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Role;


/**
 * AbstractEmploye 
 */

public abstract class AbstractEmploye extends GeneralBean   implements java.io.Serializable {


    // Fields    

     private Integer idEmploye;
     private String nomEmploye;
     private String prenomEmploye;
     private String cinEmploye;
     private String adresseEmploye;
     private String login;
     private String password;     
     private Role role;
     private Set instanceProcesses = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractEmploye() {
    }

	/** minimal constructor */
    public AbstractEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }
    
    /** full constructor */
    public AbstractEmploye(Integer idEmploye, String nomEmploye, String prenomEmploye, String cinEmploye, String adresseEmploye, String login, String password, Role role, Set instanceProcesses) {
        this.idEmploye = idEmploye;
        this.nomEmploye = nomEmploye;
        this.prenomEmploye = prenomEmploye;
        this.cinEmploye = cinEmploye;
        this.adresseEmploye = adresseEmploye;
        this.role = role;
        this.login = login;
        this.password = password;
        this.instanceProcesses = instanceProcesses;
    }

   
    // Property accessors

    public Integer getIdEmploye() {
        return this.idEmploye;
    }
    
    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNomEmploye() {
        return this.nomEmploye;
    }
    
    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getPrenomEmploye() {
        return this.prenomEmploye;
    }
    
    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public String getCinEmploye() {
        return this.cinEmploye;
    }
    
    public void setCinEmploye(String cinEmploye) {
        this.cinEmploye = cinEmploye;
    }

    public String getAdresseEmploye() {
        return this.adresseEmploye;
    }
    
    public void setAdresseEmploye(String adresseEmploye) {
        this.adresseEmploye = adresseEmploye;
    }

    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }

    public Set getInstanceProcesses() {
        return this.instanceProcesses;
    }
    
    public void setInstanceProcesses(Set instanceProcesses) {
        this.instanceProcesses = instanceProcesses;
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
   
    







}