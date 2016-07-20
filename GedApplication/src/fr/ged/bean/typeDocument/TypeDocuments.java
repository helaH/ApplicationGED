package fr.ged.bean.typeDocument;


import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.TypeDocument;
import fr.ged.data.dao.TypeDocumentDAO;

/**
 * The class TypeDocument.
 */

public class TypeDocuments extends GeneralBean {
	private Long idTypeDoc;
	private String nomTypeDoc;
	private String formatDoc;
	private String extensionDoc;
	
	private TypeDocumentDAO typeDocumentDAO;

	private TypeDocument typeDocumentSelected;
	   
	
	public void setTypeDocumentDAO(TypeDocumentDAO typedocumentDAO) {
		this.typeDocumentDAO = typedocumentDAO;
	}

	/**
     * Gets the typeDocuments.
     * 
     * @return the typeDocuments
     * @author SHA
     */
    public List getTypeDocuments() {
        List<TypeDocument> l = this.typeDocumentDAO.getHibernateTemplate().find("From TypeDocument ORDER BY idTypeDocument DESC", null);
        return l;
    }

    /**
     * Gets the typeDocument as items.
     * 
     * @return the typeDocument as items
     * @author SHA
     */
    public List getTypeDocumenstAsItems() {
        List l = this.getTypeDocuments();
        List typeDocumentsItems = new LinkedList();
        if (l != null && !l.isEmpty()) {
            for (int i = 0; i < l.size(); i++) {
                // TODO
                SelectItem SelectItem = new SelectItem(((TypeDocument) l.get(i)).getIdTypeDocument(),((TypeDocument) l.get(i)).getNomType()+" "+((TypeDocument) l.get(i)).getFormatDoc()+" "+ ((TypeDocument) l.get(i)).getExtensionDoc(),"", false);
                typeDocumentsItems.add(SelectItem);
            }
        }
        return typeDocumentsItems;
    }

    /**
     * Adds the role.
     * 
     * @return the string
     * @author SHA
     */
    public String addTypeDocument() {
    	 try {
             TypeDocument typeDocument = new TypeDocument();
             typeDocument.setNomType(nomTypeDoc);
             typeDocument.setFormatDoc(formatDoc);
             typeDocument.setExtensionDoc(extensionDoc);
             
             this.typeDocumentDAO.save(typeDocument);
             this.setNomTypeDoc("");
             this.setExtensionDoc("");
             this.setExtensionDoc("");

         } catch (Exception e) {
             sendMessageJSF("typeDocument_addTypeDocumentError", LEVEL_ERROR);
         }
         return "AddTypeDocumentSuccess";

    }
    /**
     * Update role.
     */
    public void updateTypeDocument() {
        try {
    		this.typeDocumentDAO.merge(this.getTypeDocumentSelected());
    		this.typeDocumentDAO.getHibernateTemplate().flush();
    		this.typeDocumentDAO.getHibernateTemplate().clear();
        } catch (Exception e) {
            sendMessageJSF("typeDocument_updateTypeDocumentError", LEVEL_ERROR);
        }
    }

    /**
     * Delete role.
     */
    public void deleteTypeDocument() {
        try {
        	this.typeDocumentDAO.delete(this.getTypeDocumentSelected());
        	this.typeDocumentDAO.getHibernateTemplate().flush();
        	this.typeDocumentDAO.getHibernateTemplate().clear();
        } catch (Exception e) {
           sendMessageJSF("typeDocument_deleteTypeDocumentError", LEVEL_ERROR);
        }
    }

    /**
     * Gets the id role.
     * 
     * @return the idRole
     */
  



	public Long getIdTypeDoc() {
		return idTypeDoc;
	}

	public void setIdTypeDoc(Long idTypeDoc) {
		this.idTypeDoc = idTypeDoc;
	}

	public String getNomTypeDoc() {
		return nomTypeDoc;
	}
	public void setNomTypeDoc(String nomTypeDoc) {
		this.nomTypeDoc = nomTypeDoc;
	}

	public TypeDocument getTypeDocumentSelected() {
		return typeDocumentSelected;
	}

	public void setTypeDocumentSelected(TypeDocument typedocumentSelected) {
		this.typeDocumentSelected = typedocumentSelected;
	}

	public String getFormatDoc() {
		return formatDoc;
	}

	public void setFormatDoc(String formatDoc) {
		this.formatDoc = formatDoc;
	}

	public String getExtensionDoc() {
		return extensionDoc;
	}

	public void setExtensionDoc(String extensionDoc) {
		this.extensionDoc = extensionDoc;
	}
	
	

}
