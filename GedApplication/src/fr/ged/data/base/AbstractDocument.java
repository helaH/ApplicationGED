package fr.ged.data.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Employe;
import fr.ged.data.Role;
import fr.ged.data.TypeDocument;


/**
 * AbstractDocument 
 */

public abstract class AbstractDocument extends GeneralBean implements java.io.Serializable {


    // Fields    

     private Integer idDoc;
     private String nomDoc;
     private String pathDoc;
     private String doc;
     private Date dateCreation;
     private Date dateDerniereModif;
     private Employe employe;
     private Integer versionDoc;
     private TypeDocument typeDocument;
     private Set partageDocs = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractDocument() {
    }

	/** minimal constructor */
    public AbstractDocument(Integer idDoc) {
        this.idDoc = idDoc;
    }
    
    /** full constructor */
    public AbstractDocument(Integer idDoc, String nomDoc, String pathDoc, TypeDocument typeDocument, String doc, Date dateCreation, Date dateDerniereModif, Employe employe, Integer versionDoc, Set partageDocs) {
        this.idDoc = idDoc;
        this.nomDoc = nomDoc;
        this.pathDoc = pathDoc;
        this.typeDocument = typeDocument;
        this.doc = doc;
        this.dateCreation = dateCreation;
        this.dateDerniereModif = dateDerniereModif;
        this.employe = employe;
        this.versionDoc = versionDoc;
        this.partageDocs = partageDocs;
    }

   
    // Property accessors

    public Integer getIdDoc() {
        return this.idDoc;
    }
    
    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }

    public String getNomDoc() {
        return this.nomDoc;
    }
    
    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public String getPathDoc() {
        return this.pathDoc;
    }
    
    public void setPathDoc(String pathDoc) {
        this.pathDoc = pathDoc;
    }


    public String getDoc() {
        return this.doc;
    }
    
    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Date getDateCreation() {
        return this.dateCreation;
    }
    
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateDerniereModif() {
        return this.dateDerniereModif;
    }
    
    public void setDateDerniereModif(Date dateDerniereModif) {
        this.dateDerniereModif = dateDerniereModif;
    }



    public Integer getVersionDoc() {
        return this.versionDoc;
    }
    
    public void setVersionDoc(Integer versionDoc) {
        this.versionDoc = versionDoc;
    }

    public Set getPartageDocs() {
        return this.partageDocs;
    }
    
    public void setPartageDocs(Set partageDocs) {
        this.partageDocs = partageDocs;
    }

	public TypeDocument getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(TypeDocument typeDocument) {
		this.typeDocument = typeDocument;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
   








}