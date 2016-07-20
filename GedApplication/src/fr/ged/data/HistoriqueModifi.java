package fr.ged.data;


import java.util.Date;

import fr.ged.data.base.AbstractHistoriqueModifi;


/**
 * HistoriqueModifi 
 */
public class HistoriqueModifi extends AbstractHistoriqueModifi implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public HistoriqueModifi() {
    }

	/** minimal constructor */
    public HistoriqueModifi(HistoriqueModifiId id) {
        super(id);        
    }
    
    /** full constructor */
    public HistoriqueModifi(HistoriqueModifiId id, Date dateModif) {
        super(id, dateModif);        
    }
   
}
