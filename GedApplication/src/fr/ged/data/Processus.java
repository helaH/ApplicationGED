package fr.ged.data;


import java.util.Set;

import fr.ged.data.base.AbstractProcessus;


/**
 * Processus 
 */
public class Processus extends AbstractProcessus implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Processus() {
    }

	/** minimal constructor */
    public Processus(Integer idProcess) {
        super(idProcess);        
    }
    
    /** full constructor */
    public Processus(Integer idProcess, TypeDocument typeDocument, String nomProcess, Set instanceProcesses, Set activities) {
        super(idProcess, typeDocument, nomProcess, instanceProcesses, activities);        
    }
   
}
