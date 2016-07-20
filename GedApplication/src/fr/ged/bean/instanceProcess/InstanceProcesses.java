package fr.ged.bean.instanceProcess;

import java.util.LinkedList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Activites;
import fr.ged.data.Employe;
import fr.ged.data.InstanceProcess;
import fr.ged.data.InstanceProcessId;
import fr.ged.data.Processus;
import fr.ged.data.dao.ActivitesDAO;
import fr.ged.data.dao.EmployeDAO;
import fr.ged.data.dao.InstanceProcessDAO;

public class InstanceProcesses extends GeneralBean {
	 private InstanceProcessId id;
     private Employe employe;
     private Activites activites;
     private String etat;
     
     private InstanceProcess instanceProcessSelected;
     
     private InstanceProcessDAO instanceProcessDAO;
     private EmployeDAO employeDAO;
     private ActivitesDAO activitesDAO;
     
     private Integer idEmploye;
     private Integer idActivite;
     private Integer idEmployeSelected;
     private Integer idActiviteSelected;
     private Integer idDocumentSelected;
     
     /**
      * Gets the InstanceProcesses.
      * 
      * @return the InstanceProcesses
      * @author SHA
      */
     public List getInstanceProcessForDocument() {
     	List<InstanceProcess> l = null;
     	if(this.instanceProcessDAO!= null ){
     		 l = this.instanceProcessDAO.getHibernateTemplate().find("From InstanceProcess where id.document.idDoc="+this.getIdDocumentSelected()+" ORDER BY id DESC", null);
     	}
       
         return l;
     }

     /**
      * Gets the InstanceProcesses.
      * 
      * @return the InstanceProcesses
      * @author SHA
      */
     public List getInstanceProcess() {
     	List<InstanceProcess> l = null;
     	if(this.instanceProcessDAO!= null ){
     		 Employe employe = (Employe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("employe");
     		   l = this.instanceProcessDAO.getHibernateTemplate().find("From InstanceProcess where etat='EnCours' and id.activites.role.idRole="+employe.getRole().getIdRole()+" ORDER BY id DESC", null);
     	}
       
         return l;
     }
   
     /**
      * Adds the insatnceProcesses.
      * 
      * @return the string
      * @author SHA
      */
     public String addInsatnceProcess() {
         try {
        	InstanceProcess instanceProcess = new InstanceProcess();
        	instanceProcess.setEtat(this.getEtat());
            // this.setActivites(this.activitesDAO.findById(this.getIdActivite()));
          //   instanceProcess.setActivites(this.getActivites());
             this.setEmploye(this.employeDAO.findById(this.getIdEmploye()));
             instanceProcess.setEmploye(this.getEmploye());
             this.instanceProcessDAO.save(instanceProcess);  
             this.instanceProcessDAO.getHibernateTemplate().clear();
             this.instanceProcessDAO.getHibernateTemplate().flush();
         } catch (Exception e) {
             sendMessageJSF("instanceProcess_addInstanceProcessError", LEVEL_ERROR);
         }
        
         return "AddInstanceProcessSuccess";

     }
     /**
      * Update insatnceProcesses.
      */
     public void updateInstanceProcess() {
         try {
         	
         	 this.instanceProcessDAO.getHibernateTemplate().getSessionFactory().openSession().beginTransaction();
     		 Employe employe = (Employe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("employe");
         	  this.instanceProcessSelected.setEmploye(employe);
         	  this.instanceProcessDAO.merge(this.instanceProcessSelected);   
      
          	 //passe à l'etape suivant si OK ou KO selon l'existance sinon fin processus
              if (this.instanceProcessSelected.getEtat().equalsIgnoreCase("OK")){
            	 if(this.instanceProcessSelected.getId().getActivites().getActivitesByNextActiviteOk()!= null){
            	       //Enregistrement du process (Première Activité)
     	            InstanceProcess instanceProcess = new InstanceProcess();
     	            InstanceProcessId instanceProcessId = new InstanceProcessId();
     	            instanceProcessId.setDocument(this.instanceProcessSelected.getId().getDocument());
     	            instanceProcessId.setProcessus(this.instanceProcessSelected.getId().getProcessus());
     	           //recuperation d'activité
       	            instanceProcessId.setActivites(this.instanceProcessSelected.getId().getActivites().getActivitesByNextActiviteOk());     
     	            instanceProcess.setEtat("EnCours");
     	            instanceProcess.setId(instanceProcessId);
     	       	            
     	            this.instanceProcessDAO.save(instanceProcess); 
            		 
            	 }else{
            		 sendDirectMessageJSF("Impossible de choisir un Etat OK, Vérifier le Process du document ou Choisir Terminer Process", LEVEL_ERROR,null);
            	 }
              }else  if (this.instanceProcessSelected.getEtat().equalsIgnoreCase("KO")){
             	 if(this.instanceProcessSelected.getId().getActivites().getActivitesByNextActiviteKo()!= null){
          	       //Enregistrement du process (Première Activité)
	   	            InstanceProcess instanceProcess = new InstanceProcess();
	   	            InstanceProcessId instanceProcessId = new InstanceProcessId();
	   	            instanceProcessId.setDocument(this.instanceProcessSelected.getId().getDocument());
	   	            instanceProcessId.setProcessus(this.instanceProcessSelected.getId().getProcessus());
	   	         //recuperation d'activité
     	            instanceProcessId.setActivites(this.instanceProcessSelected.getId().getActivites().getActivitesByNextActiviteKo());    
	   	            instanceProcess.setEtat("EnCours");
	   	            instanceProcess.setId(instanceProcessId);
	   	          	            
	   	            this.instanceProcessDAO.save(instanceProcess); 
          		 
            	 }else{
            		 sendDirectMessageJSF("Impossible de choisir un Etat KO, Vérifier le Process du document  ou Choisir Terminer Process", LEVEL_ERROR,null);
            	 }
              }
            		  
            		  
            this.instanceProcessDAO.getHibernateTemplate().clear();
            this.instanceProcessDAO.getHibernateTemplate().flush();
         } catch (Exception e) {
             sendMessageJSF("instanceProcess_updateInstanceProcessError", LEVEL_ERROR);
         }
     }

     /**
      * Delete insatnceProcesses.
      */
     
     public void deleteInsatnceProcess() {
         try {
         	 this.instanceProcessDAO.delete(this.getInstanceProcessSelected());   
         	 this.instanceProcessDAO.getHibernateTemplate().clear();
              this.instanceProcessDAO.getHibernateTemplate().flush();
         } catch (Exception e) {
            sendMessageJSF("instanceProcess_deleteInstanceProcessError", LEVEL_ERROR);
         }
     }
     
	public InstanceProcessId getId() {
		return id;
	}
	public void setId(InstanceProcessId id) {
		this.id = id;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public Activites getActivites() {
		return activites;
	}
	public void setActivites(Activites activites) {
		this.activites = activites;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public InstanceProcess getInstanceProcessSelected() {
		return instanceProcessSelected;
	}
	public void setInstanceProcessSelected(InstanceProcess instanceProcessSelected) {
		this.instanceProcessSelected = instanceProcessSelected;
	}
	public InstanceProcessDAO getInstanceProcessDAO() {
		return instanceProcessDAO;
	}
	public void setInstanceProcessDAO(InstanceProcessDAO instanceProcessDAO) {
		this.instanceProcessDAO = instanceProcessDAO;
	}
	public Integer getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(Integer idEmploye) {
		this.idEmploye = idEmploye;
	}
	public Integer getIdActivite() {
		return idActivite;
	}
	public void setIdActivite(Integer idActivite) {
		this.idActivite = idActivite;
	}
	public Integer getIdEmployeSelected() {
		return idEmployeSelected;
	}
	public void setIdEmployeSelected(Integer idEmployeSelected) {
		this.idEmployeSelected = idEmployeSelected;
	}
	public Integer getIdActiviteSelected() {
		return idActiviteSelected;
	}
	public void setIdActiviteSelected(Integer idActiviteSelected) {
		this.idActiviteSelected = idActiviteSelected;
	}
	public void setEmployeDAO(EmployeDAO employeDAO) {
		this.employeDAO = employeDAO;
	}
	public void setActivitesDAO(ActivitesDAO activitesDAO) {
		this.activitesDAO = activitesDAO;
	}

	public Integer getIdDocumentSelected() {
		return idDocumentSelected;
	}

	public void setIdDocumentSelected(Integer idDocumentSelected) {
		this.idDocumentSelected = idDocumentSelected;
	}
     
     
     

}
