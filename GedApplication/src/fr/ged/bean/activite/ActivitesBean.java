package fr.ged.bean.activite;

import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import fr.ged.bean.generalbean.GeneralBean;
import fr.ged.data.Activites;
import fr.ged.data.Processus;
import fr.ged.data.Role;
import fr.ged.data.dao.ActivitesDAO;
import fr.ged.data.dao.ProcessusDAO;
import fr.ged.data.dao.RoleDAO;

public class ActivitesBean extends GeneralBean {
	private int idActivite;
	private int activitesByNextActiviteOk;
	private int activitesByNextActiviteKo;
	private String nomActivite;
	private String typeActivite;
	private ActivitesDAO activitesDAO;
	private Activites activiteSelected;
	private int idProcess;
	private int idProcessSelected;
	private Processus processus;
	private ProcessusDAO processusDAO;
	private int idRole;
	private int idRoleSelected;
	private Role role;
	private RoleDAO roleDAO;
	private int idProcessusSelected;

	/**
	 * Gets the activites.
	 * 
	 * @return the activites
	 * @author SHA
	 */
	public List getActivite() {
		List<Activites> l = null;
		if (this.activitesDAO != null) {
			l = this.activitesDAO.getHibernateTemplate().find(
					"From Activites ORDER BY  DESC", null);
		}

		return l;
	}

	/**
	 * Gets the activites.
	 * 
	 * @return the activites
	 * @author SHA
	 */
	public List getActiviteForProcess() {
		List<Activites> l = null;
		if (this.activitesDAO != null) {
			if (this.getIdProcessusSelected() != 0)
				l = this.activitesDAO.getHibernateTemplate().find(
						"From Activites where processus.idProcess = "
								+ this.getIdProcessusSelected()
								+ "  ORDER BY idActivite DESC", null);
		}

		return l;
	}

	/**
	 * Gets the activites.
	 * 
	 * @return the activites
	 * @author SHA
	 */
	public List getActiviteForConfigProcess() {
		List<Activites> l = null;
		if (this.activitesDAO != null) {
			if (this.getIdProcessusSelected() != 0 && this.getIdActivite() != 0)
				l = this.activitesDAO.getHibernateTemplate().find(
						"From Activites where processus.idProcess = "
								+ this.getIdProcessusSelected() + "  AND id !="
								+ this.getIdActivite()
								+ " ORDER BY idActivite DESC", null);
		}

		return l;
	}

	/**
	 * Gets the activites as items.
	 * 
	 * @return the activites as items
	 * @author SHA
	 */
	public List getActivitesForConfigAsItems() {
		List l = this.getActiviteForConfigProcess();
		List activitesItems = new LinkedList();
		if (l != null && !l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				// TODO
				SelectItem SelectItem = new SelectItem(
						((Activites) l.get(i)).getIdActivite(),
						((Activites) l.get(i)).getNomActivite(), " ", false);
				activitesItems.add(SelectItem);
			}
		}
		return activitesItems;
	}

	private void init() {
		this.setNomActivite("");
		this.setIdRole(0);
		this.setTypeActivite("");

	}

	/**
	 * Gets the activites as items.
	 * 
	 * @return the activites as items
	 * @author SHA
	 */
	public List getActivitesAsItems() {
		List l = this.getActivite();
		List activitesItems = new LinkedList();
		if (l != null && !l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				// TODO
				SelectItem SelectItem = new SelectItem(((Activites) l.get(i)),
						((Activites) l.get(i)).getNomActivite(), " ", false);
				activitesItems.add(SelectItem);
			}
		}
		return activitesItems;
	}

	/**
	 * Adds the Activite.
	 * 
	 * @return the string
	 * @author SHA
	 */
	public String addActivite() {
		try {
			Activites activite = new Activites();
			activite.setNomActivite(this.getNomActivite());
			activite.setTypeActivite(this.getTypeActivite());
			activite.setRole(this.roleDAO.findById(this.getIdRole()));
			activite.setProcessus(this.processusDAO.findById(this
					.getIdProcessusSelected()));
			this.activitesDAO.save(activite);
			this.activitesDAO.getHibernateTemplate().clear();
			this.activitesDAO.getHibernateTemplate().flush();
			init();
		} catch (Exception e) {
			sendMessageJSF("activite_addActiviteError", LEVEL_ERROR);
		}
		return "AddActiviteSuccess";

	}

	/**
	 * Adds the Activite.
	 * 
	 * @return the string
	 * @author SHA
	 */
	public void addActiviteForProcess() {
		try {
			Activites activite = new Activites();
			activite.setNomActivite(this.getNomActivite());
			activite.setTypeActivite(this.getTypeActivite());
			activite.setRole(this.roleDAO.findById(this.getIdRole()));
			activite.setProcessus(this.processusDAO.findById(this
					.getIdProcessusSelected()));
			this.activitesDAO.save(activite);
			this.activitesDAO.getHibernateTemplate().clear();
			this.activitesDAO.getHibernateTemplate().flush();
			init();
		} catch (Exception e) {
			sendMessageJSF("activite_addActiviteError", LEVEL_ERROR);
		}

	}

	/**
	 * Update Activite.
	 */
	public void updateActivite() {
		try {

			this.activitesDAO.getHibernateTemplate().getSessionFactory()
					.openSession().beginTransaction();
			this.activiteSelected.setRole(this.roleDAO.findById(this
					.getIdRoleSelected()));
			this.activitesDAO.merge(this.activiteSelected);
			this.activitesDAO.getHibernateTemplate().clear();
			this.activitesDAO.getHibernateTemplate().flush();

		} catch (Exception e) {
			sendMessageJSF("activite_updateActiviteError", LEVEL_ERROR);
		}
	}

	/**
	 * Update Activite For Config.
	 */
	public void updateActiviteForConfig() {
		try {

			this.activitesDAO.getHibernateTemplate().getSessionFactory()
					.openSession().beginTransaction();
			if (activitesByNextActiviteKo != 0)
				this.activiteSelected
						.setActivitesByNextActiviteKo(this.activitesDAO
								.findById(activitesByNextActiviteKo));
			if (activitesByNextActiviteOk != 0)
				this.activiteSelected
						.setActivitesByNextActiviteOk(this.activitesDAO
								.findById(activitesByNextActiviteOk));
			this.activitesDAO.merge(this.activiteSelected);
			this.activitesDAO.getHibernateTemplate().clear();
			this.activitesDAO.getHibernateTemplate().flush();

		} catch (Exception e) {
			sendMessageJSF("activite_updateActiviteError", LEVEL_ERROR);
		}
	}

	/**
	 * Delete Activite.
	 */
	public void deleteActivite() {
		try {
			this.activitesDAO.delete(this.getActiviteSelected());
			this.activitesDAO.getHibernateTemplate().clear();
			this.activitesDAO.getHibernateTemplate().flush();
		} catch (Exception e) {
			sendMessageJSF("activites_deleteActiviteError", LEVEL_ERROR);
		}
	}

	public int getIdActivite() {
		return idActivite;
	}

	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}

	public int getActivitesByNextActiviteOk() {
		return activitesByNextActiviteOk;
	}

	public void setActivitesByNextActiviteOk(int activitesByNextActiviteOk) {
		this.activitesByNextActiviteOk = activitesByNextActiviteOk;
	}

	public int getActivitesByNextActiviteKo() {
		return activitesByNextActiviteKo;
	}

	public void setActivitesByNextActiviteKo(int activitesByNextActiviteKo) {
		this.activitesByNextActiviteKo = activitesByNextActiviteKo;
	}

	public String getNomActivite() {
		return nomActivite;
	}

	public void setNomActivite(String nomActivite) {
		this.nomActivite = nomActivite;
	}

	public String getTypeActivite() {
		return typeActivite;
	}

	public void setTypeActivite(String typeActivite) {
		this.typeActivite = typeActivite;
	}

	public ActivitesDAO getActivitesDAO() {
		return activitesDAO;
	}

	public void setActivitesDAO(ActivitesDAO activitesDAO) {
		this.activitesDAO = activitesDAO;
	}

	public Activites getActiviteSelected() {
		return activiteSelected;
	}

	public void setActiviteSelected(Activites activiteSelected) {
		this.activiteSelected = activiteSelected;
	}

	public int getIdProcess() {
		return idProcess;
	}

	public void setIdProcess(int idProcess) {
		this.idProcess = idProcess;
	}

	public int getIdProcessSelected() {
		return idProcessSelected;
	}

	public void setIdProcessSelected(int idProcessSelected) {
		this.idProcessSelected = idProcessSelected;
	}

	public Processus getProcessus() {
		return processus;
	}

	public void setProcessus(Processus processus) {
		this.processus = processus;
		if (processus != null) {
			this.setIdProcessSelected(processus.getIdProcess());
		}
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public int getIdRoleSelected() {
		return idRoleSelected;
	}

	public void setIdRoleSelected(int idRoleSelected) {
		this.idRoleSelected = idRoleSelected;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setProcessusDAO(ProcessusDAO processusDAO) {
		this.processusDAO = processusDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public int getIdProcessusSelected() {
		return idProcessusSelected;
	}

	public void setIdProcessusSelected(int idProcessusSelected) {
		this.idProcessusSelected = idProcessusSelected;
	}

}
