package fr.ged.data;

import fr.ged.data.base.AbstractInstanceProcess;



/**
 * InstanceProcess 
 */
public class InstanceProcess extends AbstractInstanceProcess implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public InstanceProcess() {
    }

	/** minimal constructor */
    public InstanceProcess(InstanceProcessId id) {
        super(id);        
    }
    
    /** full constructor */
    public InstanceProcess(InstanceProcessId id, Employe employe, String etat) {
        super(id, employe,  etat);        
    }
   
}
