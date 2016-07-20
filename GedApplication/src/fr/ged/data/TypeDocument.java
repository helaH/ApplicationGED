package fr.ged.data;


import java.util.Set;

import fr.ged.data.base.AbstractTypeDocument;


/**
 * TypeDocument 
 */
public class TypeDocument extends AbstractTypeDocument implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public TypeDocument() {
    }

	/** minimal constructor */
    public TypeDocument(Integer idTypeDocument) {
        super(idTypeDocument);        
    }
    
    /** full constructor */
    public TypeDocument(Integer idTypeDocument, String nomType, String formatDoc, String extensionDoc, Set processuses) {
        super(idTypeDocument, nomType, formatDoc, extensionDoc, processuses);        
    }
   
}
