package fr.ged.data.dao;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.ged.data.TypeDocument;


/**
 * Data access object (DAO) for domain model class TypeDocument.
 * @see fr.ged.data.TypeDocument
 */
public class TypeDocumentDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(TypeDocumentDAO.class);

	//property constants
	public static final String NOM_TYPE = "nomTypeDoc";
	public static final String FORMAT_DOC = "formatDoc";
	public static final String EXTENSION_DOC = "extensionDoc";

	protected void initDao() {
		//do nothing
	}
  @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
   public void save(TypeDocument typeDocument) {
        log.debug("saving TypeDocument instance");
        try {
            getHibernateTemplate().save(typeDocument);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TypeDocument persistentInstance) {
        log.debug("deleting TypeDocument instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TypeDocument findById( java.lang.Integer id) {
        log.debug("getting TypeDocument instance with id: " + id);
        try {
            TypeDocument instance = (TypeDocument) getHibernateTemplate()
                    .get("fr.ged.data.TypeDocument", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TypeDocument instance) {
        log.debug("finding TypeDocument instance by example");
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
      log.debug("finding TypeDocument instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TypeDocument as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNomTypeDocument(Object nomTypeDoc) {
		return findByProperty(NOM_TYPE,nomTypeDoc);
	}
	
    public TypeDocument merge(TypeDocument detachedInstance) {
        log.debug("merging TypeDocument instance");
        try {
            TypeDocument result = (TypeDocument) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TypeDocument instance) {
        log.debug("attaching dirty TypeDocument instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TypeDocument instance) {
        log.debug("attaching clean TypeDocument instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TypeDocumentDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TypeDocumentDAO) ctx.getBean("TypeDocumentDAO");
	}
}