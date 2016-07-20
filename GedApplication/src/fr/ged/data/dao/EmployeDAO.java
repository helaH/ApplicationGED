package fr.ged.data.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.ged.data.Employe;

/**
 * Data access object (DAO) for domain model class Employe.
 * @see fr.ged.data.Employe
 */
public class EmployeDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(EmployeDAO.class);

	//property constants
	public static final String NOM_EMPLOYE = "nomEmploye";
	public static final String PRENOM_EMPLOYE = "prenomEmploye";
	public static final String CIN_EMPLOYE = "cinEmploye";
	public static final String ADRESSE_EMPLOYE = "adresseEmploye";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";

	protected void initDao() {
		//do nothing
	}
    
    public void save(Employe transientInstance) {
        log.debug("saving Employe instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Employe persistentInstance) {
        log.debug("deleting Employe instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Employe findById( java.lang.Integer id) {
        log.debug("getting Employe instance with id: " + id);
        try {
            Employe instance = (Employe) getHibernateTemplate()
                    .get("fr.ged.data.Employe", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Employe instance) {
        log.debug("finding Employe instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Employe instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Employe as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNomEmploye(Object nomEmploye) {
		return findByProperty(NOM_EMPLOYE, nomEmploye);
	}
	
	public List findByPrenomEmploye(Object prenomEmploye) {
		return findByProperty(PRENOM_EMPLOYE, prenomEmploye);
	}
	
	public List findByCinEmploye(Object cinEmploye) {
		return findByProperty(CIN_EMPLOYE, cinEmploye);
	}
	
	public List findByAdresseEmploye(Object adresseEmploye) {
		return findByProperty(ADRESSE_EMPLOYE, adresseEmploye);
	}
	
	public List findByLogin(Object login) {
		return findByProperty(LOGIN, login);
	}
	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}
	
    public Employe merge(Employe detachedInstance) {
        log.debug("merging Employe instance");
        try {
            Employe result = (Employe) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Employe instance) {
        log.debug("attaching dirty Employe instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Employe instance) {
        log.debug("attaching clean Employe instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static EmployeDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (EmployeDAO) ctx.getBean("EmployeDAO");
	}
}