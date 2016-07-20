package fr.ged.bean.document;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import fr.ged.data.Document;
import fr.ged.data.configuration.ContextApplicationFactory;
import fr.ged.data.dao.DocumentDAO;

public class DocumentConverter implements Converter{
DocumentDAO documentDAO;
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Document document = null;
    	if(arg2 != null && arg2.length()>0){
    		this.documentDAO = DocumentDAO.getFromApplicationContext(ContextApplicationFactory.context);    		
    		document = (Document) this.documentDAO.findById(Integer.valueOf(arg2));
    	}
        return document;
    }

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		 Document document = null;
	        if(arg2!= null && arg2 instanceof Document)
	        	document = (Document) arg2;
	        return document != null ?document.getIdDoc().toString():"";
	    }

}
