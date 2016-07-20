package fr.ged.data;

import fr.ged.data.base.AbstractPartageDocId;



/**
 * PartageDocId 
 */
public class PartageDocId extends AbstractPartageDocId implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PartageDocId() {
    }

    
    /** full constructor */
    public PartageDocId(Document document, Role role) {
        super(document, role);        
    }
   
}
