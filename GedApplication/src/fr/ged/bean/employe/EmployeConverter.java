package fr.ged.bean.employe;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import fr.ged.data.Employe;
import fr.ged.data.Role;
import fr.ged.data.configuration.ContextApplicationFactory;
import fr.ged.data.dao.EmployeDAO;
import fr.ged.data.dao.RoleDAO;

/**
 * The class EmployerConverter.
 */
public class EmployeConverter implements Converter {
	EmployeDAO employeDAO;
    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     * @author SHA
     */
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
    	Employe employer = null;
    	if(arg2 != null && arg2.length()>0){
    		this.employeDAO = EmployeDAO.getFromApplicationContext(ContextApplicationFactory.context);    		
    		employer = (Employe) this.employeDAO.findById(Integer.valueOf(arg2));
    	}
        return employer;
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     * @author SHA
     */
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Employe employer = null;
        if(arg2!= null && arg2 instanceof Employe)
        	employer = (Employe) arg2;
        return employer != null ?employer.getIdEmploye().toString():"";
    }

    

}
