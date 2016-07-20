package fr.ged.data;


import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.ged.data.base.AbstractRole;
import fr.ged.data.configuration.ContextApplicationFactory;
import fr.ged.data.dao.RoleDAO;


/**
 * Role 
 */
public class Role extends AbstractRole implements java.io.Serializable {
    // Constructors

    /** default constructor */
    public Role() {
    }

	/** minimal constructor */
    public Role(Integer idRole) {
        super(idRole);        
    }
    
    /** full constructor */
    public Role(Integer idRole, String nomRole, Set partageDocs, Set employes) {
        super(idRole, nomRole, partageDocs, employes);        
    }
   

    
}
