package fr.ged.data.base;



/**
 * AbstractHistoriqueModifiId 
 */

public abstract class AbstractHistoriqueModifiId  implements java.io.Serializable {


    // Fields    

     private Integer idDoc;
     private Integer idEmploye;
     private Integer version;


    // Constructors

    /** default constructor */
    public AbstractHistoriqueModifiId() {
    }

    
    /** full constructor */
    public AbstractHistoriqueModifiId(Integer idDoc, Integer idEmploye, Integer version) {
        this.idDoc = idDoc;
        this.idEmploye = idEmploye;
        this.version = version;
    }

   
    // Property accessors

    public Integer getIdDoc() {
        return this.idDoc;
    }
    
    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }

    public Integer getIdEmploye() {
        return this.idEmploye;
    }
    
    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }

    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AbstractHistoriqueModifiId) ) return false;
		 AbstractHistoriqueModifiId castOther = ( AbstractHistoriqueModifiId ) other; 
         
		 return ( (this.getIdDoc()==castOther.getIdDoc()) || ( this.getIdDoc()!=null && castOther.getIdDoc()!=null && this.getIdDoc().equals(castOther.getIdDoc()) ) )
 && ( (this.getIdEmploye()==castOther.getIdEmploye()) || ( this.getIdEmploye()!=null && castOther.getIdEmploye()!=null && this.getIdEmploye().equals(castOther.getIdEmploye()) ) )
 && ( (this.getVersion()==castOther.getVersion()) || ( this.getVersion()!=null && castOther.getVersion()!=null && this.getVersion().equals(castOther.getVersion()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdDoc() == null ? 0 : this.getIdDoc().hashCode() );
         result = 37 * result + ( getIdEmploye() == null ? 0 : this.getIdEmploye().hashCode() );
         result = 37 * result + ( getVersion() == null ? 0 : this.getVersion().hashCode() );
         return result;
   }   





}