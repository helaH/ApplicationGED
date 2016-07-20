package fr.ged.data;


import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.ged.data.base.AbstractEmploye;
import fr.ged.data.configuration.HibernateSessionFactory;


/**
 * Employe 
 */
public class Employe extends AbstractEmploye implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Employe() {
    }

	/** minimal constructor */
    public Employe(Integer idEmploye) {
        super(idEmploye);        
    }
    
    /** full constructor */
    public Employe(Integer idEmploye, String nomEmploye, String prenomEmploye, String cinEmploye, String adresseEmploye, String login, String password, Role role, Set instanceProcesses) {
        super(idEmploye, nomEmploye, prenomEmploye, cinEmploye, adresseEmploye, login, password, role, instanceProcesses);        
    }

}
