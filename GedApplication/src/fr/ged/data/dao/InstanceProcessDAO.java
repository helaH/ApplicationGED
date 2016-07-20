package fr.ged.data.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.ged.data.InstanceProcess;

/**
 * Data access object (DAO) for domain model class InstanceProcess.
 * @see fr.ged.data.InstanceProcess
 */
public class InstanceProcessDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(InstanceProcessDAO.class);

	//property constants
	public static final String ETAT = "etat";

	protected void initDao() {
		//do nothing
	}
    
    public void save(InstanceProcess transientInstance) {
        log.debug("saving InstanceProcess instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(InstanceProcess persistentInstance) {
        log.debug("deleting InstanceProcess instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public InstanceProcess findById( fr.ged.data.InstanceProcessId id) {
        log.debug("getting InstanceProcess instance with id: " + id);
        try {
            InstanceProcess instance = (InstanceProcess) getHibernateTemplate()
                    .get("fr.ged.data.InstanceProcess", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
  
    
    public List findByExample(InstanceProcess instance) {
        log.debug("finding InstanceProcess instance by example");
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
      log.debug("finding InstanceProcess instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from InstanceProcess as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByEtat(Object etat) {
		return findByProperty(ETAT, etat);
	}
	
    public InstanceProcess merge(InstanceProcess detachedInstance) {
        log.debug("merging InstanceProcess instance");
        try {
            InstanceProcess result = (InstanceProcess) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(InstanceProcess instance) {
        log.debug("attaching dirty InstanceProcess instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(InstanceProcess instance) {
        log.debug("attaching clean InstanceProcess instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static InstanceProcessDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (InstanceProcessDAO) ctx.getBean("InstanceProcessDAO");
	}
}