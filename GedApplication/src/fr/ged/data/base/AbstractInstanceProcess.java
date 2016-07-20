package fr.ged.data.base;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Activites;
import fr.ged.data.Employe;
import fr.ged.data.InstanceProcessId;



/**
 * AbstractInstanceProcess 
 */

public abstract class AbstractInstanceProcess extends GeneralBean  implements java.io.Serializable {


    // Fields    

     private InstanceProcessId id;
     private Employe employe;

     private String etat;


    // Constructors

    /** default constructor */
    public AbstractInstanceProcess() {
    }

	/** minimal constructor */
    public AbstractInstanceProcess(InstanceProcessId id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractInstanceProcess(InstanceProcessId id, Employe employe, String etat) {
        this.id = id;
        this.employe = employe;
      
        this.etat = etat;
    }

   
    // Property accessors

    public InstanceProcessId getId() {
        return this.id;
    }
    
    public void setId(InstanceProcessId id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return this.employe;
    }
    
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }



    public String getEtat() {
        return this.etat;
    }
    
    public void setEtat(String etat) {
        this.etat = etat;
    }
   








}