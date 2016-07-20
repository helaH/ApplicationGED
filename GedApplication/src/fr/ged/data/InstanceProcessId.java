package fr.ged.data;

import fr.ged.data.base.AbstractInstanceProcessId;



/**
 * InstanceProcessId 
 */
public class InstanceProcessId extends AbstractInstanceProcessId implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public InstanceProcessId() {
    }

    
    /** full constructor */
    public InstanceProcessId(Processus processus, Document document, Activites activites) {
        super(processus, document,activites);        
    }
   
}
