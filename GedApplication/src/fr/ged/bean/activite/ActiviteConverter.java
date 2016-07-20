package fr.ged.bean.activite;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import fr.ged.data.Activites;
import fr.ged.data.configuration.ContextApplicationFactory;
import fr.ged.data.dao.ActivitesDAO;


public class ActiviteConverter implements Converter{
	ActivitesDAO activitesDAO;
	/**
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     * @author SHA
     */
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		Activites activite = null;
    	if(arg2 != null && arg2.length()>0){
    		this.activitesDAO = activitesDAO.getFromApplicationContext(ContextApplicationFactory.context);  		
    		activite = (Activites) this.activitesDAO.findById(Integer.valueOf(arg2));
    	}
        return activite;
        
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Activites activite = null;
        if(arg2!= null && arg2 instanceof Activites)
        	activite = (Activites) arg2;
        return activite != null ?activite.getIdActivite().toString():"";
    }
	}


