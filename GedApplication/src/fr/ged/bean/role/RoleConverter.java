/** 
 * Managed Bean for JSF
 * Domain class = fr.ged.bean.role
 * @author SHA
 */

package fr.ged.bean.role;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import fr.ged.data.Role;
import fr.ged.data.configuration.ContextApplicationFactory;
import fr.ged.data.dao.RoleDAO;

/**
 * The class RoleConverter.
 */
public class RoleConverter implements Converter {
	RoleDAO roleDAO;
    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     * @author SHA
     */
	 @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
    	Role role = null;
    	if(arg2 != null && arg2.length()>0){
    		this.roleDAO = RoleDAO.getFromApplicationContext(ContextApplicationFactory.context);    		
			role = (Role) this.roleDAO.findById(Integer.valueOf(arg2));
    	}
        return role;
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
    	Role role= null;
    	if(arg2!= null && arg2 instanceof Role)
    		role = (Role)arg2;
        return role != null ?role.getIdRole().toString():"";
    }

    

}
