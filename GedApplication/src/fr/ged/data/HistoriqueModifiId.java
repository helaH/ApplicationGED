package fr.ged.data;

import fr.ged.data.base.AbstractHistoriqueModifiId;



/**
 * HistoriqueModifiId 
 */
public class HistoriqueModifiId extends AbstractHistoriqueModifiId implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public HistoriqueModifiId() {
    }

    
    /** full constructor */
    public HistoriqueModifiId(Integer idDoc, Integer idEmploye, Integer version) {
        super(idDoc, idEmploye, version);        
    }
   
}
