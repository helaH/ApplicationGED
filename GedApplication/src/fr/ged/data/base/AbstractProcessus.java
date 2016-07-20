package fr.ged.data.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Activites;
import fr.ged.data.TypeDocument;


/**
 * AbstractProcessus 
 */

public abstract class AbstractProcessus extends GeneralBean  implements java.io.Serializable {


    // Fields    

     private Integer idProcess;
     private TypeDocument typeDocument;
     private String nomProcess;
     private Set instanceProcesses = new HashSet(0);
     private Set activities = new HashSet(0);
     private ArrayList<Activites> ListActivites ;

    // Constructors

    /** default constructor */
    public AbstractProcessus() {
    }

	/** minimal constructor */
    public AbstractProcessus(Integer idProcess) {
        this.idProcess = idProcess;
    }
    
    /** full constructor */
    public AbstractProcessus(Integer idProcess, TypeDocument typeDocument, String nomProcess, Set instanceProcesses, Set activities ) {
        this.idProcess = idProcess;
        this.typeDocument = typeDocument;
        this.nomProcess = nomProcess;
        this.instanceProcesses = instanceProcesses;
        this.activities = activities;
    }

   
    // Property accessors

    public Integer getIdProcess() {
        return this.idProcess;
    }
    
    public void setIdProcess(Integer idProcess) {
        this.idProcess = idProcess;
    }

    public TypeDocument getTypeDocument() {
        return this.typeDocument;
    }
    
    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getNomProcess() {
        return this.nomProcess;
    }
    
    public void setNomProcess(String nomProcess) {
        this.nomProcess = nomProcess;
    }

    public Set getInstanceProcesses() {
        return this.instanceProcesses;
    }
    
    public void setInstanceProcesses(Set instanceProcesses) {
        this.instanceProcesses = instanceProcesses;
    }

	public Set getActivities() {
		return activities;
	}

	public void setActivities(Set activities) {
		this.activities = activities;
	}
   

    public ArrayList<Activites> getListActivites() {
    	return 	new ArrayList<Activites>(this.getActivities());
	}

	public void setListActivites(ArrayList<Activites> listActivites) {
		ListActivites = listActivites;
	}









}