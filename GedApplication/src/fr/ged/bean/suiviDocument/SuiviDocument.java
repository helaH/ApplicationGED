/**
 * Managed Bean for JSF
 * Domain class =  fr.ged.bean.role;
 * @author SHA
 */
package fr.ged.bean.suiviDocument;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import fr.ged.bean.DateTool;
import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Employe;
import fr.ged.data.dao.DocumentDAO;

/**
 * The class SuiviDocument.
 */
public class SuiviDocument extends GeneralBean {
	private int idDocument;
	private String nomDocument;
	private int idProcessSelected;
	private Date dateCreation;
	private int idTypeDoc;
	private DocumentDAO documentDAO;

	public int getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(int idDocument) {
		this.idDocument = idDocument;
	}

	public String getNomDocument() {
		return nomDocument;
	}

	public void setNomDocument(String nomDocument) {
		this.nomDocument = nomDocument;
	}

	public int getIdProcessSelected() {
		return idProcessSelected;
	}

	public void setIdProcessSelected(int idProcessSelected) {
		this.idProcessSelected = idProcessSelected;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getIdTypeDoc() {
		return idTypeDoc;
	}

	public void setIdTypeDoc(int idTypeDoc) {
		this.idTypeDoc = idTypeDoc;
	}

	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}

	/**
	 * Gets the Results.
	 * 
	 * @return the Results
	 * @author SHA
	 */
	public List getDocumentRecherche() {
		List l = null;
		try{
			Employe employe = (Employe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("employe");
			String req = "SELECT distinct d FROM  InstanceProcess as ip inner join ip.id.processus as p inner join ip.id.activites.role as r   "
					+ "inner join ip.id.document as  d  where ip.etat != null and r.idRole="+employe.getRole().getIdRole();
			
			if (this.getIdDocument() != 0) {
				req += " and d.idDoc = " + this.getIdDocument();
			}
			if (this.getIdTypeDoc() != 0) {
				req += " and d.typeDocument.idTypeDocument = " + this.getIdTypeDoc();
			}
			if (this.getDateCreation()!=null) {
				req += " and d.dateCreation = '" +DateTool.getSqlDate(this.getDateCreation())+"' " ;
			}
			if (this.getIdProcessSelected()!=0) {
				req += " and p.idProcess = "+ this.getIdProcessSelected();
			}
			if (this.getNomDocument()!=null && !this.getNomDocument().isEmpty() ) {
				req += " and d.nomDoc like '%"+ this.getNomDocument()+"%' ";
			}
		
			req += "  ORDER BY d.idDoc DESC";
			l = this.documentDAO.getHibernateTemplate().find(req, null);
		}catch (Exception e) {
			sendDirectMessageJSF("Réessayer avec autres critères, pas des documents trouvés", LEVEL_ERROR,null);
        }
		return l;
	}
	public void init(){
		this.setIdDocument(0);
		this.setIdTypeDoc(0);
		this.setDateCreation(null);
		this.setIdProcessSelected(0);
		this.setNomDocument("");
		
	}
}
