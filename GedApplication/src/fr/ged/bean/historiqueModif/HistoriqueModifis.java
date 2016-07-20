package fr.ged.bean.historiqueModif;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Employe;
import fr.ged.data.HistoriqueModifi;
import fr.ged.data.HistoriqueModifiId;
import fr.ged.data.dao.HistoriqueModifiDAO;

public class HistoriqueModifis extends GeneralBean{
	 private HistoriqueModifiId id;
     private Date dateModif;
     private HistoriqueModifiDAO historiqueModifiDAO;
     private HistoriqueModifi historiqueModifiSelected;
     
     
     /**
      * Gets the HistoriqueModifis
      * 
      * @return the HistoriqueModifis
      * @author SHA
      */
     public List getHistoriqueModifis() {
     	List<HistoriqueModifi> l = null;
     	if(this.historiqueModifiDAO!= null ){
     		   l = this.historiqueModifiDAO.getHibernateTemplate().find("From HistoriqueModifi ORDER BY id DESC", null);
     	}
       
         return l;
     }

     /**
      * Gets the HistoriqueModifis as items.
      * 
      * @return the HistoriqueModifis as items
      * @author SHA
      */
     public List getHistoriqueModifisAsItems() {
         List l = this.getHistoriqueModifis();
         List HistoriqueModifisItems = new LinkedList();
         if (l != null && !l.isEmpty()) {
             for (int i = 0; i < l.size(); i++) {
                 // TODO
                 SelectItem SelectItem = new SelectItem(((HistoriqueModifi) l.get(i)), ((HistoriqueModifi) l.get(i)).getDateModif()+" ", "", false);
                 HistoriqueModifisItems.add(SelectItem);
             }
         }
         return HistoriqueModifisItems;
     }

     /**
      * Adds the HistoriqueModifis.
      * 
      * @return the string
      * @author SHA
      */
     public String addHistoriqueModifis() {
         try {
        	 HistoriqueModifi historiqueModifi = new HistoriqueModifi();
        	 historiqueModifi.setDateModif(this.getDateModif());
             
             this.historiqueModifiDAO.save(historiqueModifi);  
             this.historiqueModifiDAO.getHibernateTemplate().clear();
             this.historiqueModifiDAO.getHibernateTemplate().flush();
         } catch (Exception e) {
             sendMessageJSF("historiqueModifi_addHistoriqueModifiError", LEVEL_ERROR);
         }
        
         return "AddHistoriqueModifiSuccess";

     }
     
     /**
      * Update HistoriqueModifis.
      */
     public void updateHistoriqueModifi() {
         try {
         	
         	 this.historiqueModifiDAO.getHibernateTemplate().getSessionFactory().openSession().beginTransaction();
         	 
         	  this.historiqueModifiDAO.merge(this.historiqueModifiSelected);   
          	 this.historiqueModifiDAO.getHibernateTemplate().clear();
              this.historiqueModifiDAO.getHibernateTemplate().flush();
          	 
         } catch (Exception e) {
             sendMessageJSF("historiqueModifi_updateHistoriqueModifiError", LEVEL_ERROR);
         }
     }

     /**
      * Delete historiqueModifis.
      */
     public void deleteHistoriqueModifi() {
         try {
         	 this.historiqueModifiDAO.delete(this.getHistoriqueModifiSelected());   
         	 this.historiqueModifiDAO.getHibernateTemplate().clear();
              this.historiqueModifiDAO.getHibernateTemplate().flush();
         } catch (Exception e) {
            sendMessageJSF("historiqueModifi_deleteHistoriqueModifiError", LEVEL_ERROR);
         }
     }
     
	public HistoriqueModifiId getId() {
		return id;
	}
	public void setId(HistoriqueModifiId id) {
		this.id = id;
	}
	public Date getDateModif() {
		return dateModif;
	}
	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}

	public void setHistoriqueModifiDAO(HistoriqueModifiDAO historiqueModifiDAO) {
		this.historiqueModifiDAO = historiqueModifiDAO;
	}
	public HistoriqueModifi getHistoriqueModifiSelected() {
		return historiqueModifiSelected;
	}
	public void setHistoriqueModifiSelected(
			HistoriqueModifi historiqueModifiSelected) {
		this.historiqueModifiSelected = historiqueModifiSelected;
	}
     
     
     

}
