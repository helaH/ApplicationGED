
package fr.ged.bean.typeDocument;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import fr.ged.data.TypeDocument;
import fr.ged.data.configuration.ContextApplicationFactory;
import fr.ged.data.dao.TypeDocumentDAO;
public class TypeDocumentConverter implements Converter{
	
	TypeDocumentDAO typeDocumentDAO;
    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     * @author SHA
     */
	 @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
    	TypeDocument typeDocument = null;
    	if(arg2 != null && arg2.length()>0){
    		this.typeDocumentDAO = TypeDocumentDAO.getFromApplicationContext(ContextApplicationFactory.context);    		
			typeDocument = (TypeDocument) this.typeDocumentDAO.findById(Integer.valueOf(arg2));
    	}
        return typeDocument;
    }
    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     * @author SHA
     */
    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
    	TypeDocument typeDocument= null;
    	if(arg2!= null && arg2 instanceof TypeDocument)
    		typeDocument = (TypeDocument)arg2;
        return typeDocument != null ?typeDocument.getIdTypeDocument().toString():"";
    }

    

}



