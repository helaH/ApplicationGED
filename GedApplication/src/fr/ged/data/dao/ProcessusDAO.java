package fr.ged.data.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.ged.data.Processus;

/**
 * Data access object (DAO) for domain model class Processus.
 * @see fr.ged.data.Processus
 */
public class ProcessusDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(ProcessusDAO.class);

	//property constants
	public static final String NOM_PROCESS = "nomProcess";

	protected void initDao() {
		//do nothing
	}
    
    public void save(Processus transientInstance) {
        log.debug("saving Processus instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Processus persistentInstance) {
        log.debug("deleting Processus instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Processus findById( java.lang.Integer id) {
        log.debug("getting Processus instance with id: " + id);
        try {
            Processus instance = (Processus) getHibernateTemplate()
                    .get("fr.ged.data.Processus", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Processus instance) {
        log.debug("finding Processus instance by example");
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
      log.debug("finding Processus instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Processus as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNomProcess(Object nomProcess) {
		return findByProperty(NOM_PROCESS, nomProcess);
	}
	
    public Processus merge(Processus detachedInstance) {
        log.debug("merging Processus instance");
        try {
            Processus result = (Processus) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Processus instance) {
        log.debug("attaching dirty Processus instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Processus instance) {
        log.debug("attaching clean Processus instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ProcessusDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ProcessusDAO) ctx.getBean("ProcessusDAO");
	}
}