package fr.ged.data.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.ged.data.Activites;

/**
 * Data access object (DAO) for domain model class Activites.
 * @see fr.ged.data.Activites
 */
public class ActivitesDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(ActivitesDAO.class);

	//property constants
	public static final String NOM_ACTIVITE = "nomActivite";
	public static final String ID_PROCESS = "idProcess";
	public static final String ID_ROLE = "idRole";
	public static final String TYPE_ACTIVITE = "typeActivite";

	protected void initDao() {
		//do nothing
	}
    
    public void save(Activites transientInstance) {
        log.debug("saving Activites instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Activites persistentInstance) {
        log.debug("deleting Activites instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Activites findById( java.lang.Integer id) {
        log.debug("getting Activites instance with id: " + id);
        try {
            Activites instance = (Activites) getHibernateTemplate()
                    .get("fr.ged.data.Activites", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Activites instance) {
        log.debug("finding Activites instance by example");
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
      log.debug("finding Activites instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Activites as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNomActivite(Object nomActivite) {
		return findByProperty(NOM_ACTIVITE, nomActivite);
	}
	
	public List findByIdProcess(Object idProcess) {
		Object[] processIds= {327913};
		String query = "From Activites where processus.idProcess = ? ORDER BY idActivite  ASC";
		List afterHql = getHibernateTemplate().find(query, idProcess);
		return afterHql;
	}
	
	public List findByIdRole(Object idRole) {
		return findByProperty(ID_ROLE, idRole);
	}
	
	public List findByTypeActivite(Object typeActivite) {
		return findByProperty(TYPE_ACTIVITE, typeActivite);
	}
	
    public Activites merge(Activites detachedInstance) {
        log.debug("merging Activites instance");
        try {
            Activites result = (Activites) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Activites instance) {
        log.debug("attaching dirty Activites instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Activites instance) {
        log.debug("attaching clean Activites instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ActivitesDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ActivitesDAO) ctx.getBean("ActivitesDAO");
	}
}