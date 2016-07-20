package fr.ged.data.base;

import fr.ged.data.Document;
import fr.ged.data.Role;



/**
 * AbstractPartageDocId 
 */

public abstract class AbstractPartageDocId  implements java.io.Serializable {


    // Fields    

     private Document document;
     private Role role;


    // Constructors

    /** default constructor */
    public AbstractPartageDocId() {
    }

    
    /** full constructor */
    public AbstractPartageDocId(Document document, Role role) {
        this.document = document;
        this.role = role;
    }

   
    // Property accessors

    public Document getDocument() {
        return this.document;
    }
    
    public void setDocument(Document document) {
        this.document = document;
    }

    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AbstractPartageDocId) ) return false;
		 AbstractPartageDocId castOther = ( AbstractPartageDocId ) other; 
         
		 return ( (this.getDocument()==castOther.getDocument()) || ( this.getDocument()!=null && castOther.getDocument()!=null && this.getDocument().equals(castOther.getDocument()) ) )
 && ( (this.getRole()==castOther.getRole()) || ( this.getRole()!=null && castOther.getRole()!=null && this.getRole().equals(castOther.getRole()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDocument() == null ? 0 : this.getDocument().hashCode() );
         result = 37 * result + ( getRole() == null ? 0 : this.getRole().hashCode() );
         return result;
   }   





}