<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
	<f:loadBundle basename="MessageResources_fr" var="msg" />
    <t:div id="subnavigation_outer">
        <t:div id="subnavigation">
            <h:form>
		        <t:panelNavigation2 id="nav1" layout="list" itemClass="mypage" activeItemClass="selected" openItemClass="selected" >
		            <t:commandNavigation2  value="#{msg['panelnav_suivi_doc']}" action="suiviDoc" />
		            <t:commandNavigation2 value="#{msg['panelnav_partage_doc']}" action="partageDoc"  />
		            <t:commandNavigation2 value="#{msg['panelnav_ajout_process']}" action="ajoutProcessus"  />
		            <t:commandNavigation2 value="#{msg['panelnav_ajout_document']}" action="ajoutDocument" />
		            <t:commandNavigation2 value="#{msg['panelnav_ajout_activite']}" action="valideEtape" />
		            <t:commandNavigation2 value="#{msg['panelnav_ajout_employe']}" action="ajoutEmploye" rendered="#{loginGed.role eq 'Admin'}" />
	               	<t:commandNavigation2 value="#{msg['panelnav_ajout_role']}" action="ajoutRole" rendered="#{loginGed.role eq 'Admin'}"/>
	               	<t:commandNavigation2 value="#{msg['panelnav_ajout_typeDocument']}" action="ajoutTypeDocument"  />
	               	    <t:commandNavigation2 value="#{msg['panelnav_logout']}" action="#{loginGed.logOut}"  />
		           	
		        </t:panelNavigation2>
            </h:form>
        </t:div>
    </t:div>

