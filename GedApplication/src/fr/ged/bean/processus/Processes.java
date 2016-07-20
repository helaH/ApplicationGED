package fr.ged.bean.processus;


import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Employe;
import fr.ged.data.PartageDoc;
import fr.ged.data.Processus;
import fr.ged.data.Role;
import fr.ged.data.TypeDocument;
import fr.ged.data.dao.EmployeDAO;
import fr.ged.data.dao.InstanceProcessDAO;
import fr.ged.data.dao.ProcessusDAO;
import fr.ged.data.dao.RoleDAO;
import fr.ged.data.dao.TypeDocumentDAO;

public class Processes extends GeneralBean {
	   private int idProcess;
	   private String nomProcess;
	   private ProcessusDAO processusDAO;
       private Processus processusSelected;
       private TypeDocument typeDocument;
       private TypeDocumentDAO typeDocumentDAO;
       private int idTypeDocument;
       private int idTypeDocumentSelected;
       
       /**
        * Gets the processes.
        * 
        * @return the processes
        * @author SHA
        */
       public List getProcesses() {
       	List<Processus> l = null;
       	if(this.processusDAO!= null ){
       		   l = this.processusDAO.getHibernateTemplate().find("From Processus ORDER BY idProcess DESC", null);
       	}

       	
           return l;
       }
       
       /**
        * Gets the processes.
        * 
        * @return the processes
        * @author SHA
        */
       public List getProcessesFromTypeDocument() {
       	List<Processus> l = null;
       	if(this.processusDAO!= null ){
       		if(this.getIdTypeDocumentSelected()!=0){
       		   l = this.processusDAO.getHibernateTemplate().find("From Processus where typeDocument.idTypeDocument="+this.getIdTypeDocumentSelected()+" ORDER BY idProcess DESC", null);
       		}
       	
       	}

       	
           return l;
       }
       /**
        * Gets the processus as items.
        * 
        * @return the processus as items
        * @author SHA
        */
       public List getProcessesFromTypeDocumentAsItems() {
           List l = this.getProcessesFromTypeDocument();
           List processesItems = new LinkedList();
           if (l != null && !l.isEmpty()) {
               for (int i = 0; i < l.size(); i++) {
                   // TODO
                   SelectItem SelectItem = new SelectItem(((Processus) l.get(i)).getIdProcess(), ((Processus) l.get(i)).getNomProcess(), "", false);
                   processesItems.add(SelectItem);
               }
           }
           return processesItems;
       }
       /**
        * Gets the processus as items.
        * 
        * @return the employes as items
        * @author SHA
        */
       public List getProcessesAsItems() {
           List l = this.getProcesses();
           List processesItems = new LinkedList();
           if (l != null && !l.isEmpty()) {
               for (int i = 0; i < l.size(); i++) {
                   // TODO
                   SelectItem SelectItem = new SelectItem(((Processus) l.get(i)), ((Processus) l.get(i)).getNomProcess(), "", false);
                   processesItems.add(SelectItem);
               }
           }
           return processesItems;
       }
       public void init(){
    	   this.setIdTypeDocument(0);
    	   this.setNomProcess("");
    	   
       }
       /**
        * Adds the processes.
        * 
        * @return the string
        * @author SHA
        */
       public void addProcessus() {
           try {
               Processus processus = new Processus();
               processus.setNomProcess(this.getNomProcess());
               processus.setTypeDocument(this.typeDocumentDAO.findById(this.getIdTypeDocument()));
               this.processusDAO.save(processus);  
               this.processusDAO.getHibernateTemplate().clear();
               this.processusDAO.getHibernateTemplate().flush();
               this.init();
           } catch (Exception e) {
               sendMessageJSF("processus_addProcessusError", LEVEL_ERROR);
           }
       }
       
       /**
        * Update processes.
        */
       public void updateProcessus() {
           try {
           	
           	 this.processusDAO.getHibernateTemplate().getSessionFactory().openSession().beginTransaction();
           	  this.processusSelected.setTypeDocument(this.typeDocumentDAO.findById(this.getIdTypeDocumentSelected()));
           	  this.processusDAO.merge(this.processusSelected);   
            	 this.processusDAO.getHibernateTemplate().clear();
                this.processusDAO.getHibernateTemplate().flush();
            	 
           } catch (Exception e) {
               sendMessageJSF("processus_updateProcessusError", LEVEL_ERROR);
           }
       }

       /**
        * Delete processes.
        */
       public void deleteProcessus() {
           try {
           	 this.processusDAO.delete(this.getProcessusSelected());   
           	 this.processusDAO.getHibernateTemplate().clear();
                this.processusDAO.getHibernateTemplate().flush();
           } catch (Exception e) {
              sendMessageJSF("processus_deleteProcessusError", LEVEL_ERROR);
           }
       }

	public int getIdProcess() {
		return idProcess;
	}

	public void setIdProcess(int idProcess) {
		this.idProcess = idProcess;
	}

	public String getNomProcess() {
		return nomProcess;
	}

	public void setNomProcess(String nomProcess) {
		this.nomProcess = nomProcess;
	}

	public ProcessusDAO getProcessusDAO() {
		return processusDAO;
	}

	public void setProcessusDAO(ProcessusDAO processusDAO) {
		this.processusDAO = processusDAO;
	}

	public Processus getProcessusSelected() {
		return processusSelected;
	}

	public void setProcessusSelected(Processus processusSelected) {
		this.processusSelected = processusSelected;
	}

	public TypeDocument getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(TypeDocument typeDocument) {
		this.typeDocument = typeDocument;
	}

	public int getIdTypeDocument() {
		return idTypeDocument;
	}

	public void setIdTypeDocument(int idTypeDocument) {
		this.idTypeDocument = idTypeDocument;
	}

	public int getIdTypeDocumentSelected() {
		return idTypeDocumentSelected;
	}

	public void setIdTypeDocumentSelected(int idTypeDocumentSelected) {
		this.idTypeDocumentSelected = idTypeDocumentSelected;
	}

	public void setTypeDocumentDAO(TypeDocumentDAO typeDocumentDAO) {
		this.typeDocumentDAO = typeDocumentDAO;
	}

   
   	

   }

