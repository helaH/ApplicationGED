package fr.ged.bean.processus;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import fr.ged.data.Employe;
import fr.ged.data.Processus;
import fr.ged.data.configuration.ContextApplicationFactory;
import fr.ged.data.dao.EmployeDAO;
import fr.ged.data.dao.ProcessusDAO;

public class ProcessusConverter implements Converter  {
	ProcessusDAO processusDAO;
	/**
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     * @author SHA
     */
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		Processus processus = null;
    	if(arg2 != null && arg2.length()>0){
    		this.processusDAO = ProcessusDAO.getFromApplicationContext(ContextApplicationFactory.context);    		
    		processus = (Processus) this.processusDAO.findById(Integer.valueOf(arg2));
    	}
        return processus;
        
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		Processus processus = null;
	        if(arg2!= null && arg2 instanceof Employe)
	        	processus = (Processus) arg2;
	        return processus != null ?processus.getIdProcess().toString():"";
	    }


}
