/**
 * Managed Bean for JSF
 * Domain class =  fr.ged.bean.role;
 * @author SHA
 */
package fr.ged.bean.role;

import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Employe;
import fr.ged.data.Role;
import fr.ged.data.configuration.ContextApplicationFactory;
import fr.ged.data.dao.RoleDAO;

/**
 * The class Roles.
 */
public class Roles extends GeneralBean {

    /** The id role. */
    private Long idRole;



    /** The nom role. */
    private String nomRole;
    
    private RoleDAO roleDAO ;
    
    
    private Role roleSelected;
     
    public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	/**
     * Gets the roles.
     * 
     * @return the roles
     * @author SHA
     */
    public List getRoles() {
        List<Role> l = this.roleDAO.getHibernateTemplate().find("From Role ORDER BY idRole DESC", null);
        return l;
    }

    /**
     * Gets the roles as items.
     * 
     * @return the roles as items
     * @author SHA
     */
    public List getRolesAsItems() {
        List l = this.getRoles();
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

    /**
     * Adds the role.
     * 
     * @return the string
     * @author SHA
     */
    public String addRole() {
        try {
            Role role = new Role();
            role.setNomRole(nomRole);
            this.roleDAO.save(role);
            this.setNomRole("");
        } catch (Exception e) {
            sendMessageJSF("role_addRoleError", LEVEL_ERROR);
        }
        return "AddRoleSuccess";

    }
    /**
     * Update role.
     */
    public void updateRole() {
        try {
    		this.roleDAO.merge(this.getRoleSelected());
    		this.roleDAO.getHibernateTemplate().flush();
    		this.roleDAO.getHibernateTemplate().clear();
        } catch (Exception e) {
            sendMessageJSF("role_updateRoleError", LEVEL_ERROR);
        }
    }

    /**
     * Delete role.
     */
    public void deleteRole() {
        try {
        	this.roleDAO.delete(this.getRoleSelected());
        	this.roleDAO.getHibernateTemplate().flush();
        	this.roleDAO.getHibernateTemplate().clear();
        } catch (Exception e) {
           sendMessageJSF("role_deleteRoleError", LEVEL_ERROR);
        }
    }

    /**
     * Gets the id role.
     * 
     * @return the idRole
     */
    public Long getIdRole() {
        return idRole;
    }

    /**
     * Sets the id role.
     * 
     * @param idRole the idRole to set
     */
    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

   
    

    /**
     * Gets the nom role.
     * 
     * @return the nomRole
     */
    public String getNomRole() {
        return nomRole;
    }

    /**
     * Sets the nom role.
     * 
     * @param nomRole the nomRole to set
     */
    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

	public Role getRoleSelected() {
		return roleSelected;
	}

	public void setRoleSelected(Role roleSelected) {
		this.roleSelected = roleSelected;
	}

    
}
