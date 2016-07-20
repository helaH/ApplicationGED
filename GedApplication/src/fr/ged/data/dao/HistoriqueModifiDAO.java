package fr.ged.data.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.ged.data.HistoriqueModifi;

/**
 * Data access object (DAO) for domain model class HistoriqueModifi.
 * @see fr.ged.data.HistoriqueModifi
 */
public class HistoriqueModifiDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(HistoriqueModifiDAO.class);

	//property constants

	protected void initDao() {
		//do nothing
	}
    
    public void save(HistoriqueModifi transientInstance) {
        log.debug("saving HistoriqueModifi instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(HistoriqueModifi persistentInstance) {
        log.debug("deleting HistoriqueModifi instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public HistoriqueModifi findById( fr.ged.data.HistoriqueModifiId id) {
        log.debug("getting HistoriqueModifi instance with id: " + id);
        try {
            HistoriqueModifi instance = (HistoriqueModifi) getHibernateTemplate()
                    .get("fr.ged.data.HistoriqueModifi", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(HistoriqueModifi instance) {
        log.debug("finding HistoriqueModifi instance by example");
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
      log.debug("finding HistoriqueModifi instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from HistoriqueModifi as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public HistoriqueModifi merge(HistoriqueModifi detachedInstance) {
        log.debug("merging HistoriqueModifi instance");
        try {
            HistoriqueModifi result = (HistoriqueModifi) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(HistoriqueModifi instance) {
        log.debug("attaching dirty HistoriqueModifi instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(HistoriqueModifi instance) {
        log.debug("attaching clean HistoriqueModifi instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static HistoriqueModifiDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (HistoriqueModifiDAO) ctx.getBean("HistoriqueModifiDAO");
	}
}