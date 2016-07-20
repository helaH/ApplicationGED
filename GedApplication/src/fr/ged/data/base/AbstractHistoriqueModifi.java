package fr.ged.data.base;

import java.util.Date;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.HistoriqueModifiId;


/**
 * AbstractHistoriqueModifi 
 */

public abstract class AbstractHistoriqueModifi extends GeneralBean  implements java.io.Serializable {


    // Fields    

     private HistoriqueModifiId id;
     private Date dateModif;


    // Constructors

    /** default constructor */
    public AbstractHistoriqueModifi() {
    }

	/** minimal constructor */
    public AbstractHistoriqueModifi(HistoriqueModifiId id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractHistoriqueModifi(HistoriqueModifiId id, Date dateModif) {
        this.id = id;
        this.dateModif = dateModif;
    }

   
    // Property accessors

    public HistoriqueModifiId getId() {
        return this.id;
    }
    
    public void setId(HistoriqueModifiId id) {
        this.id = id;
    }

    public Date getDateModif() {
        return this.dateModif;
    }
    
    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }
   








}