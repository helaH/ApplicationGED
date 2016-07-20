package fr.ged.data.base;

import java.util.HashSet;
import java.util.Set;

import fr.ged.bean.generalbean.GeneralBean;


/**
 * AbstractTypeDocument 
 */

public abstract class AbstractTypeDocument extends GeneralBean  implements java.io.Serializable {


    // Fields    

     private Integer idTypeDocument;
     private String nomType;
     private String formatDoc;
     private String extensionDoc;
     private Set processuses = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractTypeDocument() {
    }

	/** minimal constructor */
    public AbstractTypeDocument(Integer idTypeDocument) {
        this.idTypeDocument = idTypeDocument;
    }
    
    /** full constructor */
    public AbstractTypeDocument(Integer idTypeDocument, String nomType, String formatDoc, String extensionDoc, Set processuses) {
        this.idTypeDocument = idTypeDocument;
        this.nomType = nomType;
        this.formatDoc = formatDoc;
        this.extensionDoc = extensionDoc;
        this.processuses = processuses;
    }

   
    // Property accessors

    public Integer getIdTypeDocument() {
        return this.idTypeDocument;
    }
    
    public void setIdTypeDocument(Integer idTypeDocument) {
        this.idTypeDocument = idTypeDocument;
    }

    public String getNomType() {
        return this.nomType;
    }
    
    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getFormatDoc() {
        return this.formatDoc;
    }
    
    public void setFormatDoc(String formatDoc) {
        this.formatDoc = formatDoc;
    }

    public String getExtensionDoc() {
        return this.extensionDoc;
    }
    
    public void setExtensionDoc(String extensionDoc) {
        this.extensionDoc = extensionDoc;
    }

    public Set getProcessuses() {
        return this.processuses;
    }
    
    public void setProcessuses(Set processuses) {
        this.processuses = processuses;
    }
   








}