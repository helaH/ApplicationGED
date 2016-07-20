package fr.ged.data.base;

import java.util.HashSet;
import java.util.Set;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Activites;
import fr.ged.data.Processus;
import fr.ged.data.Role;


/**
 * AbstractActivites 
 */

public abstract class AbstractActivites extends GeneralBean  implements java.io.Serializable {


    // Fields    

     private Integer idActivite;
     private Activites activitesByNextActiviteOk;
     private Activites activitesByNextActiviteKo;
     private String nomActivite;
     private String typeActivite;
     private Role role;
     private Processus processus;
     private Set activitesesForNextActiviteOk = new HashSet(0);
     private Set activitesesForNextActiviteKo = new HashSet(0);
     private Set instanceProcesses = new HashSet(0);
     

    // Constructors

    /** default constructor */
    public AbstractActivites() {
    }

	/** minimal constructor */
    public AbstractActivites(Integer idActivite, Processus processus ) {
        this.idActivite = idActivite;
        this.processus = processus;
    }
    
    /** full constructor */
    public AbstractActivites(Integer idActivite, Activites activitesByNextActiviteOk, Activites activitesByNextActiviteKo, String nomActivite,  Processus processus , Role role, String typeActivite, Set activitesesForNextActiviteOk, Set activitesesForNextActiviteKo, Set instanceProcesses) {
        this.idActivite = idActivite;
        this.activitesByNextActiviteOk = activitesByNextActiviteOk;
        this.activitesByNextActiviteKo = activitesByNextActiviteKo;
        this.nomActivite = nomActivite;
        this.typeActivite = typeActivite;
        this.activitesesForNextActiviteOk = activitesesForNextActiviteOk;
        this.activitesesForNextActiviteKo = activitesesForNextActiviteKo;
        this.instanceProcesses = instanceProcesses;
        this.processus = processus;
        this.role = role;
    }

   
    // Property accessors

    public Integer getIdActivite() {
        return this.idActivite;
    }
    
    public void setIdActivite(Integer idActivite) {
        this.idActivite = idActivite;
    }

    public Activites getActivitesByNextActiviteOk() {
        return this.activitesByNextActiviteOk;
    }
    
    public void setActivitesByNextActiviteOk(Activites activitesByNextActiviteOk) {
        this.activitesByNextActiviteOk = activitesByNextActiviteOk;
    }

    public Activites getActivitesByNextActiviteKo() {
        return this.activitesByNextActiviteKo;
    }
    
    public void setActivitesByNextActiviteKo(Activites activitesByNextActiviteKo) {
        this.activitesByNextActiviteKo = activitesByNextActiviteKo;
    }

    public String getNomActivite() {
        return this.nomActivite;
    }
    
    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }



    public String getTypeActivite() {
        return this.typeActivite;
    }
    
    public void setTypeActivite(String typeActivite) {
        this.typeActivite = typeActivite;
    }

    public Set getActivitesesForNextActiviteOk() {
        return this.activitesesForNextActiviteOk;
    }
    
    public void setActivitesesForNextActiviteOk(Set activitesesForNextActiviteOk) {
        this.activitesesForNextActiviteOk = activitesesForNextActiviteOk;
    }

    public Set getActivitesesForNextActiviteKo() {
        return this.activitesesForNextActiviteKo;
    }
    
    public void setActivitesesForNextActiviteKo(Set activitesesForNextActiviteKo) {
        this.activitesesForNextActiviteKo = activitesesForNextActiviteKo;
    }

    public Set getInstanceProcesses() {
        return this.instanceProcesses;
    }
    
    public void setInstanceProcesses(Set instanceProcesses) {
        this.instanceProcesses = instanceProcesses;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Processus getProcessus() {
		return processus;
	}

	public void setProcessus(Processus processus) {
		this.processus = processus;
	}
   








}