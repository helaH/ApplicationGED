package fr.ged.data.base;

import java.util.HashSet;
import java.util.Set;

import fr.ged.bean.generalbean.GeneralBean;


/**
 * AbstractRole 
 */

public abstract class AbstractRole extends GeneralBean implements java.io.Serializable {


    // Fields    

     private Integer idRole;
     private String nomRole;
     private Set partageDocs = new HashSet(0);
     private Set employes = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractRole() {
    }

	/** minimal constructor */
    public AbstractRole(Integer idRole) {
        this.idRole = idRole;
    }
    
    /** full constructor */
    public AbstractRole(Integer idRole, String nomRole, Set partageDocs, Set employes) {
        this.idRole = idRole;
        this.nomRole = nomRole;
        this.partageDocs = partageDocs;
        this.employes=employes;
    }

   
    // Property accessors

    public Integer getIdRole() {
        return this.idRole;
    }
    
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getNomRole() {
        return this.nomRole;
    }
    
    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public Set getPartageDocs() {
        return this.partageDocs;
    }
    
    public void setPartageDocs(Set partageDocs) {
        this.partageDocs = partageDocs;
    }

	public Set getEmployes() {
		return employes;
	}

	public void setEmployes(Set employes) {
		this.employes = employes;
	}
   








}