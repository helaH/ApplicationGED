package fr.ged.data;


import java.util.Date;
import java.util.Set;

import fr.ged.data.base.AbstractDocument;


/**
 * Document 
 */
public class Document extends AbstractDocument implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Document() {
    }

	/** minimal constructor */
    public Document(Integer idDoc) {
        super(idDoc);        
    }
    
    /** full constructor */
    public Document(Integer idDoc, String nomDoc, String pathDoc, TypeDocument typeDocument, String doc, Date dateCreation, Date dateDerniereModif, Employe employe, Integer versionDoc, Set partageDocs) {
        super(idDoc, nomDoc, pathDoc, typeDocument, doc, dateCreation, dateDerniereModif, employe, versionDoc, partageDocs);        
    }
   
}
