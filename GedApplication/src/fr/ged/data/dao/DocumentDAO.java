package fr.ged.data.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.ged.data.Document;

/**
 * Data access object (DAO) for domain model class Document.
 * @see fr.ged.data.Document
 */
public class DocumentDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(DocumentDAO.class);

	//property constants
	public static final String NOM_DOC = "nomDoc";
	public static final String PATH_DOC = "pathDoc";
	public static final String DOC = "doc";
	public static final String ID_EMPLOYE_CREAT = "idEmployeCreat";
	public static final String VERSION_DOC = "versionDoc";
	
	 

	protected void initDao() {
		//do nothing
	}
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(Document transientInstance) {
        log.debug("saving Document instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Document persistentInstance) {
        log.debug("deleting Document instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Document findById( java.lang.Integer id) {
        log.debug("getting Document instance with id: " + id);
        try {
            Document instance = (Document) getHibernateTemplate()
                    .get("fr.ged.data.Document", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Document instance) {
        log.debug("finding Document instance by example");
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
      log.debug("finding Document instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Document as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNomDoc(Object nomDoc) {
		return findByProperty(NOM_DOC, nomDoc);
	}
	
	public List findByPathDoc(Object pathDoc) {
		return findByProperty(PATH_DOC, pathDoc);
	}
	

	
	public List findByDoc(Object doc) {
		return findByProperty(DOC, doc);
	}
	
	public List findByIdEmployeCreat(Object idEmployeCreat) {
		return findByProperty(ID_EMPLOYE_CREAT, idEmployeCreat);
	}
	
	public List findByVersionDoc(Object versionDoc) {
		return findByProperty(VERSION_DOC, versionDoc);
	}
	
    public Document merge(Document detachedInstance) {
        log.debug("merging Document instance");
        try {
            Document result = (Document) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Document instance) {
        log.debug("attaching dirty Document instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Document instance) {
        log.debug("attaching clean Document instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static DocumentDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (DocumentDAO) ctx.getBean("DocumentDAO");
	}
}