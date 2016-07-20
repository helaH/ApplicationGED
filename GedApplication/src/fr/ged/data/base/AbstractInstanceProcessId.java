package fr.ged.data.base;

import fr.ged.data.Activites;
import fr.ged.data.Document;
import fr.ged.data.Processus;



/**
 * AbstractInstanceProcessId 
 */

public abstract class AbstractInstanceProcessId  implements java.io.Serializable {


    // Fields    

     private Processus processus;
     private Document document;   
     private Activites activites;


    // Constructors

    /** default constructor */
    public AbstractInstanceProcessId() {
    }

    
    /** full constructor */
    public AbstractInstanceProcessId(Processus processus, Document document, Activites activites) {
        this.processus = processus;
        this.document = document;
        this.activites = activites;
    }

   
    // Property accessors

    public Processus getProcessus() {
        return this.processus;
    }
    
    public void setProcessus(Processus processus) {
        this.processus = processus;
    }

  



   public Document getDocument() {
		return document;
	}


	public void setDocument(Document document) {
		this.document = document;
	}


public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AbstractInstanceProcessId) ) return false;
		 AbstractInstanceProcessId castOther = ( AbstractInstanceProcessId ) other; 
         
		 return ( (this.getProcessus()==castOther.getProcessus()) || ( this.getProcessus()!=null && castOther.getProcessus()!=null && this.getProcessus().equals(castOther.getProcessus()) ) )
 && ( (this.getDocument()==castOther.getDocument()) || ( this.getDocument()!=null && castOther.getDocument()!=null && this.getDocument().equals(castOther.getDocument()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProcessus() == null ? 0 : this.getProcessus().hashCode() );
         result = 37 * result + ( getDocument() == null ? 0 : this.getDocument().hashCode() );
         return result;
   }   

   public Activites getActivites() {
       return this.activites;
   }
   
   public void setActivites(Activites activites) {
       this.activites = activites;
   }



}