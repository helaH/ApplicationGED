package fr.ged.bean.document;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import fr.ged.bean.DateTool;
import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Activites;
import fr.ged.data.Document;
import fr.ged.data.Employe;
import fr.ged.data.InstanceProcess;
import fr.ged.data.InstanceProcessId;
import fr.ged.data.Processus;
import fr.ged.data.TypeDocument;
import fr.ged.data.dao.ActivitesDAO;
import fr.ged.data.dao.DocumentDAO;
import fr.ged.data.dao.InstanceProcessDAO;
import fr.ged.data.dao.ProcessusDAO;
import fr.ged.data.dao.TypeDocumentDAO;


public class Documents extends GeneralBean {
	 private Integer idDoc;
     private String nomDoc;
     private String pathDoc;
     private String doc;
     private Date dateCreation;
     private Date dateDerniereModif;
     private Integer versionDoc;
     private Integer idTypeDoc;
     private Integer idEmployeCreat;
     private  DocumentDAO documentDAO;
     private TypeDocument typeDocument;
     private TypeDocumentDAO typeDocumentDAO;
     private Employe employe;
     private Integer idTypeDocSelected ;
     private Integer idEmployeSelected ;   
     private Document documentSelected;
	 private FileUpload fileUpload;
	 private int idProcess;
	 private  ProcessusDAO processusDAO;
	 private InstanceProcessDAO instanceProcessDAO;
	 private ActivitesDAO activitesDAO;
	  /**
	     * Gets the documents.
	     * 
	     * @return the documents
	     * @author SHA
	     */
	    public List getDocumentsForUser() {
	    	List<Document> l = null;
	    	if(this.documentDAO!= null ){
	    		 Employe employe = (Employe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("employe");
	    		 l = this.documentDAO.getHibernateTemplate().find("From Document where employe.idEmploye = "+employe.getIdEmploye()+" ORDER BY idDoc DESC", null);
	    	}	      
	        return l;
	    }
	    /**
	     * Gets the documents.
	     * 
	     * @return the documents
	     * @author SHA
	     */
	    public List getDocuments() {
	    	List<Document> l = null;
	    	if(this.documentDAO!= null ){
	    		   l = this.documentDAO.getHibernateTemplate().find("From Document ORDER BY idDoc DESC", null);
	    	}	      
	        return l;
	    }
	    
	    
	    /**
	     * Gets the documents.
	     * 
	     * @return the documents
	     * @author SHA
	     */
	    public List getDocumentsNonPartages() {
	    	List<Document> l = null;
	    	if(this.documentDAO!= null ){
	    		   l = this.documentDAO.getHibernateTemplate().find("From Document WHERE idDoc NOT IN (Select id.document.idDoc From PartageDoc ) ORDER BY idDoc DESC", null);
	    	}	      
	        return l;
	    }
	    /**
	     * Gets the documents as items.
	     * 
	     * @return the documents as items
	     * @author SHA
	     */
	    public List getDocumentsAsItems() {
	        List l = this.getDocuments();
	        List documentsItems = new LinkedList();
	        if (l != null && !l.isEmpty()) {
	            for (int i = 0; i < l.size(); i++) {
	                // TODO
	                SelectItem SelectItem = new SelectItem(((Document) l.get(i)), ((Document) l.get(i)).getNomDoc(), "", false);
	                documentsItems.add(SelectItem);
	            }
	        }
	        return documentsItems;
	    }

	    /**
	     * Adds the employe.
	     * 
	     * @return the string
	     * @author SHA
	     */

	    public String addDocument() {
			FileOutputStream fop = null;
			File file = null;
	        try {
	            Document document = new Document();
	            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
	            .getExternalContext().getContext();
	            file = new File(ctx.getRealPath("/")+"docs/"+this.getFileUpload().getName());
				fop = new FileOutputStream(file);
				if (!file.exists()) {
					file.createNewFile();
				}
				fop.write(this.getFileUpload().getData());
				fop.flush();
				fop.close();
	            document.setNomDoc(this.getFileUpload().getName());
	            document.setPathDoc(file.getAbsolutePath());
	            document.setDoc(this.getFileUpload().getMime());
	            document.setDateCreation(DateTool.getSqlDate());
	           // document.setDateDerniereModif(this.getDateDerniereModif());
	            document.setVersionDoc(1);
	            this.setTypeDocument(this.typeDocumentDAO.findById(this.getIdTypeDoc()));
	            document.setTypeDocument(this.getTypeDocument());
	            Employe employe = (Employe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("employe");
	            document.setEmploye(employe);
	            
	            this.documentDAO.save(document);  
	            this.documentDAO.getHibernateTemplate().clear();
	            this.documentDAO.getHibernateTemplate().flush();
	            //Enregistrement du process (Première Activité)
	            InstanceProcess instanceProcess = new InstanceProcess();
	            InstanceProcessId instanceProcessId = new InstanceProcessId();
	            instanceProcessId.setDocument(document);
	            Processus processus = this.processusDAO.findById(this.getIdProcess());
	            instanceProcessId.setProcessus(processus);
	            instanceProcess.setEtat("EnCours");
	            
	          //recuperation d'activité
	            List<Activites> activites = this.activitesDAO.findByIdProcess(idProcess);
	            instanceProcessId.setActivites(activites.size()>0?activites.get(0):null);
	            instanceProcess.setId(instanceProcessId);
	            this.instanceProcessDAO.save(instanceProcess);  
	            this.instanceProcessDAO.getHibernateTemplate().clear();
	            this.instanceProcessDAO.getHibernateTemplate().flush();
	            this.setIdDoc(0);
	            this.setIdProcess(0);
	             
	            
	        } catch (Exception e) {
	            sendMessageJSF("document_addDocumentError", LEVEL_ERROR);
	        }
	       
	        return "AddDocumentSuccess";
	    }
	    
	    /**
	     * Update document.
	     */
	    public void updateDocument() {
	        try {
	        	
	        	 this.documentDAO.getHibernateTemplate().getSessionFactory().openSession().beginTransaction();
	        	  this.documentSelected.setTypeDocument(this.typeDocumentDAO.findById(this.getIdTypeDocSelected()));
	        	  this.documentDAO.merge(this.documentSelected);   
	         	 this.documentDAO.getHibernateTemplate().clear();
	             this.documentDAO.getHibernateTemplate().flush();
	         	 
	        } catch (Exception e) {
	            sendMessageJSF("document_updateDocumentError", LEVEL_ERROR);
	        }
	    }

	    /**
	     * Delete document.
	     */
	    public void deleteDocument() {
	        try {
	        	 this.documentDAO.delete(this.getDocumentSelected());   
	        	 this.documentDAO.getHibernateTemplate().clear();
	             this.documentDAO.getHibernateTemplate().flush();
	        } catch (Exception e) {
	           sendMessageJSF("document_deleteDocumentError", LEVEL_ERROR);
	        }
	    }
	    
		public Integer getIdDoc() {
			return idDoc;
		}
		public void setIdDoc(Integer idDoc) {
			this.idDoc = idDoc;
		}
		public String getNomDoc() {
			return nomDoc;
		}
		public void setNomDoc(String nomDoc) {
			this.nomDoc = nomDoc;
		}
		public String getPathDoc() {
			return pathDoc;
		}
		public void setPathDoc(String pathDoc) {
			this.pathDoc = pathDoc;
		}
		public String getDoc() {
			return doc;
		}
		public void setDoc(String doc) {
			this.doc = doc;
		}
		public Date getDateCreation() {
			return dateCreation;
		}
		public void setDateCreation(Date dateCreation) {
			this.dateCreation = dateCreation;
		}
		public Date getDateDerniereModif() {
			return dateDerniereModif;
		}
		public void setDateDerniereModif(Date dateDerniereModif) {
			this.dateDerniereModif = dateDerniereModif;
		}
		public Integer getVersionDoc() {
			return versionDoc;
		}
		public void setVersionDoc(Integer versionDoc) {
			this.versionDoc = versionDoc;
		}
		public Integer getIdTypeDoc() {
			return idTypeDoc;
		}
		public void setIdTypeDoc(Integer idTypeDoc) {
			this.idTypeDoc = idTypeDoc;
		}
		public Integer getIdEmployeCreat() {
			return idEmployeCreat;
		}
		public void setIdEmployeCreat(Integer idEmployeCreat) {
			this.idEmployeCreat = idEmployeCreat;
		}
		public void setDocumentDAO(DocumentDAO documentDAO) {
			this.documentDAO = documentDAO;
		}
		public TypeDocument getTypeDocument() {
			return typeDocument;
		}
		public void setTypeDocument(TypeDocument typeDocument) {
			this.typeDocument = typeDocument;
		}
		public void setTypeDocumentDAO(TypeDocumentDAO typeDocumentDAO) {
			this.typeDocumentDAO = typeDocumentDAO;
		}
		public Employe getEmploye() {
			return employe;
		}
		public void setEmploye(Employe employe) {
			this.employe = employe;
		}
		public Integer getIdTypeDocSelected() {
			return idTypeDocSelected;
		}
		public void setIdTypeDocSelected(Integer idTypeDocSelected) {
			this.idTypeDocSelected = idTypeDocSelected;
		}
		public Integer getIdEmployeSelected() {
			return idEmployeSelected;
		}
		public void setIdEmployeSelected(Integer idEmployeSelected) {
			this.idEmployeSelected = idEmployeSelected;
		}
		public Document getDocumentSelected() {
			return documentSelected;
		}
		public void setDocumentSelected(Document documentSelected) {
			this.documentSelected = documentSelected;
		}
		public void listener(UploadEvent event) throws Exception {
			UploadItem item = event.getUploadItem();
			this.fileUpload = new FileUpload();
			this.fileUpload.setLength(item.getData().length);
			this.fileUpload.setName(FilenameUtils.getName(item.getFileName()));
			this.fileUpload.setData(item.getData());
		}
		public FileUpload getFileUpload() {
			return fileUpload;
		}
		public void setFileUpload(FileUpload fileUpload) {
			this.fileUpload = fileUpload;
		}


		public int getIdProcess() {
			return idProcess;
		}


		public void setIdProcess(int idProcess) {
			this.idProcess = idProcess;
		}


		public void setProcessusDAO(ProcessusDAO processusDAO) {
			this.processusDAO = processusDAO;
		}


		public void setInstanceProcessDAO(InstanceProcessDAO instanceProcessDAO) {
			this.instanceProcessDAO = instanceProcessDAO;
		}
		public void setActivitesDAO(ActivitesDAO activitesDAO) {
			this.activitesDAO = activitesDAO;
		} 
		
	    
	    
	
}
