package fr.ged.data.base;

import java.util.Date;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.PartageDocId;


/**
 * AbstractPartageDoc 
 */

public abstract class AbstractPartageDoc extends GeneralBean  implements java.io.Serializable {


    // Fields    

     private PartageDocId id;
     private Date datePartage;


    // Constructors

    /** default constructor */
    public AbstractPartageDoc() {
    }

	/** minimal constructor */
    public AbstractPartageDoc(PartageDocId id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractPartageDoc(PartageDocId id, Date datePartage) {
        this.id = id;
        this.datePartage = datePartage;
    }

   
    // Property accessors

    public PartageDocId getId() {
        return this.id;
    }
    
    public void setId(PartageDocId id) {
        this.id = id;
    }

    public Date getDatePartage() {
        return this.datePartage;
    }
    
    public void setDatePartage(Date datePartage) {
        this.datePartage = datePartage;
    }
   








}