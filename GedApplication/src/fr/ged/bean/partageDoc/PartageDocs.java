package fr.ged.bean.partageDoc;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import fr.ged.bean.DateTool;
import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Document;
import fr.ged.data.HistoriqueModifi;
import fr.ged.data.PartageDoc;
import fr.ged.data.PartageDocId;
import fr.ged.data.Role;
import fr.ged.data.dao.DocumentDAO;
import fr.ged.data.dao.PartageDocDAO;
import fr.ged.data.dao.RoleDAO;

public class PartageDocs extends GeneralBean {
	private PartageDocId id;
	private Date datePartage;
	private PartageDoc partageDocSelected;
	private PartageDocDAO partageDocDAO;
	private List rolesList;
	private int idDocumentSelected;
	private DocumentDAO documentDAO;
	private RoleDAO roleDAO;

	/**
	 * Gets the HistoriqueModifis
	 * 
	 * @return the HistoriqueModifis
	 * @author SHA
	 */
	public List getPartageDocs() {
		List<PartageDoc> l = null;
		if (this.partageDocDAO != null) {
			l = this.partageDocDAO.getHibernateTemplate().find(
					"From PartageDoc ORDER BY id DESC", null);
		}
		
		
		
		
		return l;
	}

	/**
	 * Gets the PartageDocs as items.
	 * 
	 * @return the PartageDocs as items
	 * @author SHA
	 */
	public List getPartageDocsAsItems() {
		List l = this.getPartageDocs();
		List PartageDocsItems = new LinkedList();
		if (l != null && !l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				// TODO
				SelectItem SelectItem = new SelectItem(
						((PartageDocs) l.get(i)),
						((PartageDocs) l.get(i)).getDatePartage() + " ", "",
						false);
				PartageDocsItems.add(SelectItem);
			}
		}
		return PartageDocsItems;
	}

	/**
	 * Adds the PartageDocs.
	 * 
	 * @return the string
	 * @author SHA
	 */
	public String addPartageDocs() {
		try {
			PartageDoc partageDoc = new PartageDoc();
			partageDoc.setDatePartage(this.getDatePartage());

			this.partageDocDAO.save(partageDoc);
			this.partageDocDAO.getHibernateTemplate().clear();
			this.partageDocDAO.getHibernateTemplate().flush();
		} catch (Exception e) {
			sendMessageJSF("partageDoc_addPartageDocError", LEVEL_ERROR);
		}

		return "AddPartageSuccess";

	}

	/**
	 * Update PartageDocs.
	 */
	public void updatePartageDoc() {
		try {

			this.partageDocDAO.getHibernateTemplate().getSessionFactory()
					.openSession().beginTransaction();

			this.partageDocDAO.merge(this.partageDocSelected);
			this.partageDocDAO.getHibernateTemplate().clear();
			this.partageDocDAO.getHibernateTemplate().flush();

		} catch (Exception e) {
			sendMessageJSF("partageDoc_updatePartageDocError", LEVEL_ERROR);
		}
	}

	/**
	 * Delete PartageDocs.
	 */
	public void deletePartageDoc() {
		try {
			this.partageDocDAO.delete(this.getPartageDocSelected());
			this.partageDocDAO.getHibernateTemplate().clear();
			this.partageDocDAO.getHibernateTemplate().flush();
		} catch (Exception e) {
			sendMessageJSF("partageDoc_deletePartageDocError", LEVEL_ERROR);
		}
	}

	public PartageDocId getId() {
		return id;
	}

	public void setId(PartageDocId id) {
		this.id = id;
	}

	public Date getDatePartage() {
		return datePartage;
	}

	public void setDatePartage(Date datePartage) {
		this.datePartage = datePartage;
	}

	public PartageDoc getPartageDocSelected() {
		return partageDocSelected;
	}

	public void setPartageDocSelected(PartageDoc partageDocSelected) {
		this.partageDocSelected = partageDocSelected;
	}

	public PartageDocDAO getPartageDocDAO() {
		return partageDocDAO;
	}

	public void setPartageDocDAO(PartageDocDAO partageDocDAO) {
		this.partageDocDAO = partageDocDAO;
	}

	public List getRolesList() {
		return rolesList;
	}

	public int getIdDocumentSelected() {
		return idDocumentSelected;
	}

	public void setIdDocumentSelected(int idDocumentSelected) {
		this.idDocumentSelected = idDocumentSelected;
	}

	public void setRolesList(List rolesList) {
		this.rolesList = rolesList;
	}
	

	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void partager() {
		try {
			//pour chaque Role dans le picklist on va ajouter une ligne dans la table partage document
			if(rolesList!= null){
				Document document = this.documentDAO.findById(this.getIdDocumentSelected());
				for (int i=0;  i < rolesList.size(); i++) {
					Role role = this.roleDAO.findById(Integer.valueOf((String) rolesList.get(i)));
					PartageDoc partDoc = new PartageDoc();
					PartageDocId partageDocID = new PartageDocId(document,role);
					partDoc.setId(partageDocID);
					partDoc.setDatePartage(DateTool.getSqlDate());
					this.getPartageDocDAO().save(partDoc);
				}
			}
		} catch (Exception e) {
			sendMessageJSF("partageDoc_addPartageDocError", LEVEL_ERROR);
		}
	
		
	}
	
	
	/**
     * Gets the roles.
     * 
     * @return the roles
     * @author SHA
     */
    public List getRolesNonPartagesForDoc() {
        List<Role> l = this.roleDAO.getHibernateTemplate().find("From Role  r where r.idRole not in (select pd.id.role.idRole from PartageDoc pd  where pd.id.document.idDoc ="+this.getIdDocumentSelected()+"  ) ORDER BY r.idRole DESC", null);
        return l;
    }

    /**
     * Gets the roles as items.
     * 
     * @return the roles as items
     * @author SHA
     */
    public List getRolesNonPartagesAsItems() {
        List l = this.getRolesNonPartagesForDoc();
        List rolesItems = new LinkedList();
        if (l != null && !l.isEmpty()) {
            for (int i = 0; i < l.size(); i++) {
                // TODO
                SelectItem SelectItem = new SelectItem(((Role) l.get(i)).getIdRole(), ((Role) l.get(i)).getNomRole(), "", false);
                rolesItems.add(SelectItem);
            }
        }
        return rolesItems;
    }
}
