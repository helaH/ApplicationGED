package fr.ged.data.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.ged.data.PartageDoc;

/**
 * Data access object (DAO) for domain model class PartageDoc.
 * @see fr.ged.data.PartageDoc
 */
public class PartageDocDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(PartageDocDAO.class);

	//property constants

	protected void initDao() {
		//do nothing
	}
    
    public void save(PartageDoc transientInstance) {
        log.debug("saving PartageDoc instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PartageDoc persistentInstance) {
        log.debug("deleting PartageDoc instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PartageDoc findById( fr.ged.data.PartageDocId id) {
        log.debug("getting PartageDoc instance with id: " + id);
        try {
            PartageDoc instance = (PartageDoc) getHibernateTemplate()
                    .get("fr.ged.data.PartageDoc", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PartageDoc instance) {
        log.debug("finding PartageDoc instance by example");
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
      log.debug("finding PartageDoc instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PartageDoc as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public PartageDoc merge(PartageDoc detachedInstance) {
        log.debug("merging PartageDoc instance");
        try {
            PartageDoc result = (PartageDoc) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PartageDoc instance) {
        log.debug("attaching dirty PartageDoc instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PartageDoc instance) {
        log.debug("attaching clean PartageDoc instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static PartageDocDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (PartageDocDAO) ctx.getBean("PartageDocDAO");
	}
}