package fr.ged.data;


import java.util.Date;

import fr.ged.data.base.AbstractPartageDoc;


/**
 * PartageDoc 
 */
public class PartageDoc extends AbstractPartageDoc implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PartageDoc() {
    }

	/** minimal constructor */
    public PartageDoc(PartageDocId id) {
        super(id);        
    }
    
    /** full constructor */
    public PartageDoc(PartageDocId id, Date datePartage) {
        super(id, datePartage);        
    }
   
}
