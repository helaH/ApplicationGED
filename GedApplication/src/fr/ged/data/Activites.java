package fr.ged.data;


import java.util.Set;

import fr.ged.data.base.AbstractActivites;


/**
 * Activites 
 */
public class Activites extends AbstractActivites implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Activites() {
    }

	/** minimal constructor */
    public Activites(Integer idActivite, Processus processus) {
        super(idActivite, processus);        
    }
    
    /** full constructor */
    public Activites(Integer idActivite, Activites activitesByNextActiviteOk, Activites activitesByNextActiviteKo, String nomActivite, Processus processus, Role role, String typeActivite, Set activitesesForNextActiviteOk, Set activitesesForNextActiviteKo, Set instanceProcesses) {
        super(idActivite, activitesByNextActiviteOk, activitesByNextActiviteKo, nomActivite, processus, role, typeActivite, activitesesForNextActiviteOk, activitesesForNextActiviteKo, instanceProcesses);        
    }
   
}
