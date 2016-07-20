<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>GED</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<f:view>
		<table border="0" cellspacing="5" style="height: 90%; width: 100%;">
			<tr>
				<td colspan="2" height="20%"><tiles:insert definition="site.header"
						flush="false" /></td>
			</tr>
			<tr valign="top">
				<td valign="top">
					<table border="0" width="100%">
						<tr>
							<td width="15%" valign="top"><tiles:insert
									definition="site.menu" flush="false" /></td>
							<td align="center" valign="top">
								<!---------------------------> <!-- insert here your body --> <!--------------------------->
								<div id="contentBody">
									<h:messages styleClass="errorMessages" showSummary="true"
										showDetail="true" />
									<rich:tabPanel switchType="client" width="900px" id="form1" >
										<rich:tab label="Process" >
											<rich:dataTable id="table"
															onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
															onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
															cellpadding="0" cellspacing="0" border="1" width="800px"
															value="#{processes.processes}" var="processusVal" 
															rendered="true">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Id Processus" />
													</f:facet>
													<a4j:form>
														<a4j:commandLink value="#{processusVal.idProcess}">
															<a4j:support event="onclick" reRender="form3"
																oncomplete="$('form3').removeClassName('hideOnLoad');">
																<f:setPropertyActionListener
																	target="#{activites.idProcessusSelected}"
																	value="#{processusVal.idProcess}" />	
																		<f:setPropertyActionListener value="#{processusVal}"
																	target="#{activites.processus}" />															
															</a4j:support>
														</a4j:commandLink>
													</a4j:form>													
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Nom Processus" />
													</f:facet>
													<h:outputText value="#{processusVal.nomProcess}" />
												</rich:column>
												
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Type document" />
													</f:facet>
													<h:outputText value="#{processusVal.typeDocument.nomType}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Actions" />
													</f:facet>
													<a4j:form>
														<a4j:commandLink ajaxSingle="true"
															id="editProcessusLink"
															oncomplete="#{rich:component('editProcessus')}.show()">
															<h:graphicImage
																value="/images/css/style001/images/edit.gif"
																style="border:0" />
															<f:setPropertyActionListener value="#{processusVal}"
																target="#{processes.processusSelected}" />
														</a4j:commandLink>
														<rich:toolTip for="editProcessusLink"
															value="Modifier Processus" />
	
														<a4j:commandLink ajaxSingle="true"
															id="deleteProcessusLink"
															oncomplete="#{rich:component('deleteProcessus')}.show()">
															<h:graphicImage
																value="/images/css/style001/images/delete.gif"
																style="border:0" />
															<f:setPropertyActionListener value="#{processusVal}"
																target="#{processes.processusSelected}" />
														</a4j:commandLink>
														<rich:toolTip for="deleteProcessusLink"
															value="Supprimer Processus" />
													</a4j:form>	
												</rich:column>
											</rich:dataTable>	
											</rich:tab>
											<rich:tab label="Ajouter Processus">									
												<fieldset >
														<legend>Ajout Processus</legend>
												<a4j:form id="form4">
													<h:panelGroup>
														<h:panelGrid columns="1">
															<h:panelGrid columns="3">
																<h:outputText value="Nom" />
																<h:inputText value="#{processes.nomProcess}"  id="nomProcess" required="true" requiredMessage="Nom Process  est obligatoire.">
																	
	                  												<rich:ajaxValidator event="onblur" />
	                   											</h:inputText>
	                   											<rich:message for="nomProcess" />
																<h:outputText value="Type Document" />
																<h:selectOneListbox 
																	value="#{processes.idTypeDocument}" size="1" id="idTypeDocument" 
																	required="true" requiredMessage="type document est obligatoire.">
																	<f:selectItem itemLabel=""  itemValue=""/>
																	<f:selectItems value="#{typeDocuments.typeDocumenstAsItems}" />
																	<rich:ajaxValidator event="onblur" />
																</h:selectOneListbox>		
																<rich:message for="idTypeDocument" />											
															</h:panelGrid>
															<a4j:commandButton value="Ajouter Processus" reRender="table,form4"
																action="#{processes.addProcessus}" onclick="if (#{facesContext.maximumSeverity==null})$('form3').addClassName('hideOnLoad');"/>
														</h:panelGrid>
													</h:panelGroup>
												</a4j:form>
											</fieldset>
										</rich:tab>
									</rich:tabPanel>
									<br/>
									<br/>
<!-- 									////////////////////////// DEBUT Activité -->
									
									<rich:tabPanel id="form3" switchType="client" width="900px" style="border: none;background: transparent;"
										styleClass="hideOnLoad" >
										<rich:tab label="Modifier Activité" >
											<rich:dataTable id="table2"
															onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
															onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
															cellpadding="0" cellspacing="0" border="1" width="800px"
															value="#{activites.activiteForProcess}" var="activiteVal" 
															rendered="true">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Id Activite" />
													</f:facet>
													<h:outputText value="#{activiteVal.idActivite}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Nom Activite" />
													</f:facet>
													<h:outputText value="#{activiteVal.nomActivite}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Type d'activite" />
													</f:facet>
													<h:outputText value="#{activiteVal.typeActivite}" />
												</rich:column>										
<%-- 												<rich:column> --%>
<%-- 													<f:facet name="header"> --%>
<%-- 														<h:outputText value="Processus" /> --%>
<%-- 													</f:facet> --%>
<%-- 													<h:outputText value="#{activiteVal.processus.nomProcess}" /> --%>
<%-- 												</rich:column> --%>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Role" />
													</f:facet>
													<h:outputText value="#{activiteVal.role.nomRole}" />
												</rich:column>	
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Actions" />
													</f:facet>		
													<a4j:form>
														<a4j:commandLink ajaxSingle="true"
															id="editActiviteLink"
															oncomplete="#{rich:component('editActivite')}.show()">
															<h:graphicImage
																value="/images/css/style001/images/edit.gif"
																style="border:0" />
															<f:setPropertyActionListener value="#{activiteVal}"
																target="#{activites.activiteSelected}" />
															<f:setPropertyActionListener value="#{activiteVal.role.idRole}"
																target="#{activites.idRoleSelected}" />															
														</a4j:commandLink>
														<rich:toolTip for="editActiviteLink"
															value="Modifier Activite" />
	
														<a4j:commandLink ajaxSingle="true"
															id="deleteActiviteLink"
															oncomplete="#{rich:component('deleteActivite')}.show()">
															<h:graphicImage
																value="/images/css/style001/images/delete.gif"
																style="border:0" />
															<f:setPropertyActionListener value="#{activiteVal}"
																target="#{activites.activiteSelected}" />														
														</a4j:commandLink>
														<rich:toolTip for="deleteActiviteLink"
															value="Supprimer Activite" />
													</a4j:form>											
												</rich:column>
											</rich:dataTable>
										</rich:tab>
										<rich:tab label="Ajouter Activité" >
											<h:form>
												<h:panelGroup>
													<h:panelGrid columns="2">
														<h:outputText value="Processus" />
														<h:inputText value="#{activites.processus.nomProcess}" readonly="true" />												
														<h:outputText value="Nom d'activite" />
														<h:inputText value="#{activites.nomActivite}" />
														<h:outputText value="Type d'activite" />
														<h:inputText value="#{activites.typeActivite}" />
														<h:outputText value="Role" />
														<h:selectOneListbox 
															value="#{activites.idRole}" size="1"
															>
															<f:selectItem itemLabel=""  itemValue=""/>
															<f:selectItems value="#{roles.rolesAsItems}" />
														</h:selectOneListbox>
														
														
		
														<a4j:commandButton value="Ajouter Activité"
															action="#{activites.addActiviteForProcess}" reRender="form3" oncomplete="$('form3').removeClassName('hideOnLoad');">
															<f:setPropertyActionListener
																	target="#{activites.idProcessusSelected}"
																	value="#{activites.processus.idProcess}" />	
														</a4j:commandButton>
													</h:panelGrid>
												</h:panelGroup>
											</h:form>
										
										</rich:tab>
										<rich:tab label="Configuration Process" >
											<rich:dataTable id="table3"
													onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
													onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
													cellpadding="0" cellspacing="0" border="1" width="800px"
													value="#{activites.activiteForProcess}" var="activiteVal" 
													rendered="true">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Nom Activite" />
													</f:facet>
													<h:outputText value="#{activiteVal.nomActivite}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Next activite ok" />
													</f:facet>
													<h:outputText value="#{activiteVal.activitesByNextActiviteOk.nomActivite}" rendered="#{activiteVal.activitesByNextActiviteOk != null}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Next activite ko" />
													</f:facet>
													<h:outputText value="#{activiteVal.activitesByNextActiviteKo.nomActivite}" rendered="#{activiteVal.activitesByNextActiviteKo!= null}" />
												</rich:column>											
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Actions" />
													</f:facet>		
													<a4j:form>
														<a4j:commandLink ajaxSingle="true"
															id="configActiviteLink"
															oncomplete="#{rich:component('configActivite')}.show()">
															<h:graphicImage
																value="/images/css/style001/images/edit.gif"
																style="border:0" />
															<f:setPropertyActionListener value="#{activiteVal}"
																target="#{activites.activiteSelected}" />		
															<f:setPropertyActionListener value="#{activiteVal.idActivite}"
																target="#{activites.idActivite}" />												
														</a4j:commandLink>
														<rich:toolTip for="configActiviteLink"
															value="Configurer Activite" />
													</a4j:form>											
												</rich:column>
											</rich:dataTable>
										
										</rich:tab>
									</rich:tabPanel>
									
									
									<!-- Delete Activities -->
									<rich:modalPanel id="deleteActivite" moveable="true"
										resizeable="true" minWidth="350" minHeight="100">
										<f:facet name="header">
											<h:outputText value="Supprimer Activtie ?"
												style="padding-right:15px;" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													styleClass="hidelink" id="hidelink5" />
												<rich:componentControl for="deleteActivite"
													attachTo="hidelink5" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<table width="100%">
												<tbody>
													<tr>
														<td align="center" width="50%"><a4j:commandButton
																value="Supprimer" ajaxSingle="true"
																action="#{activites.deleteActivite}"
																onclick="#{rich:component('deleteActivite')}.hide();"
																reRender="table2" />
														</td>
														<td align="center" width="50%"><a4j:commandButton
																value="Fermer"
																oncomplete="if (#{facesContext.maximumSeverity==null})#{rich:component('deleteActivite')}.hide();"
																reRender="table2" >
																</a4j:commandButton>
														</td>
													</tr>
												</tbody>
											</table>
										</a4j:form>
									</rich:modalPanel>
									<!-- Edit  Activite -->
									<rich:modalPanel id="editActivite" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Modification Activite:" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hideActivitelink1" styleClass="hidelink" />
												<rich:componentControl for="editActivite"
													attachTo="hideActivitelink1" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">															
														<h:outputText value="Processus" />
														<h:inputText value="#{activites.activiteSelected.processus.nomProcess}" readonly="true" />	
														<h:outputText value="Nom d'activite" />
														<h:inputText value="#{activites.activiteSelected.nomActivite}" />
														<h:outputText value="Type d'activite" />
														<h:inputText value="#{activites.activiteSelected.typeActivite}" />
														<h:outputText value="Role" />
														<h:selectOneListbox  id="roleSelect"
															value="#{activites.idRoleSelected}" size="1"
															>
															<f:selectItem itemLabel=""  itemValue=""/>
															<f:selectItems value="#{roles.rolesAsItems}" />
														</h:selectOneListbox>													
													</h:panelGrid>															
													<rich:message showSummary="true" showDetail="false"
														for="roleSelect" />													
												</a4j:outputPanel>
												<a4j:commandButton value="Modifier"
													action="#{activites.updateActivite}"
													reRender="table2"
													oncomplete="if (#{facesContext.maximumSeverity==null})#{rich:component('editActivite')}.hide();" />
											</h:panelGrid>
										</a4j:form>
									</rich:modalPanel>
									
										<!-- Config  Activite -->
									<rich:modalPanel id="configActivite" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Modification Activite:" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hideActivitelink6" styleClass="hidelink" />
												<rich:componentControl for="configActivite"
													attachTo="hideActivitelink6" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">															
														<h:outputText value="Processus" />
														<h:inputText value="#{activites.activiteSelected.processus.nomProcess}" readonly="true" />	
														<h:outputText value="Nom d'activite" />
														<h:inputText value="#{activites.activiteSelected.nomActivite}" readonly="true" />	
														<h:outputText value="Next Activite ok" />
														<h:selectOneListbox  
															value="#{activites.activitesByNextActiviteOk}" size="1">
															<f:selectItem itemLabel=""  itemValue=""/>
															<f:selectItems value="#{activites.activitesForConfigAsItems}" />
														</h:selectOneListbox>	
														<h:outputText value="Next Activite ko" />
														<h:selectOneListbox 
															value="#{activites.activitesByNextActiviteKo}" size="1" >
															<f:selectItem itemLabel=""  itemValue=""/>
															<f:selectItems value="#{activites.activitesForConfigAsItems}" />
														</h:selectOneListbox>																										
													</h:panelGrid>															
												</a4j:outputPanel>
												<a4j:commandButton value="Sauvegarder"
													action="#{activites.updateActiviteForConfig}"
													reRender="table3"
													oncomplete="if (#{facesContext.maximumSeverity==null})#{rich:component('configActivite')}.hide();" />
											</h:panelGrid>
										</a4j:form>
									</rich:modalPanel>
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
	<!-- 									//////////////////////////FIN Activité -->								
									
									
								<!-- Delete Processus -->
									<rich:modalPanel id="deleteProcessus" moveable="true"
										resizeable="true" minWidth="350" minHeight="100">
										<f:facet name="header">
											<h:outputText value="Supprimer Processus ?"
												style="padding-right:15px;" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													styleClass="hidelink" id="hidelink4" />
												<rich:componentControl for="deleteProcessus"
													attachTo="hidelink4" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<table width="100%">
												<tbody>
													<tr>
														<td align="center" width="50%"><a4j:commandButton
																value="Yes" ajaxSingle="true"
																action="#{processes.deleteProcessus}"
																oncomplete="if (#{facesContext.maximumSeverity==null})#{rich:component('deleteProcessus')}.hide();"
																reRender="table" />
														</td>
														<td align="center" width="50%"><a4j:commandButton
																value="Cancel"
																onclick="#{rich:component('deleteProcessus')}.hide();"
																reRender="table" >
																</a4j:commandButton>
														</td>
													</tr>
												</tbody>
											</table>
										</a4j:form>
									</rich:modalPanel>
									<!-- Edit  Processus -->
									<rich:modalPanel id="editProcessus" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Modification Processus:" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hideProcessuslink" styleClass="hidelink" />
												<rich:componentControl for="editProcessus"
													attachTo="hideProcessuslink" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">
														<h:outputText value="Nom" />
														<h:inputText value="#{processes.processusSelected.nomProcess}" />
														
														<h:outputText value="TypeDocument" />
														<h:selectOneListbox  id="typeDocumentSelect"
															value="#{processes.idTypeDocumentSelected}" size="1"
															>
															<f:selectItem itemLabel=""  itemValue=""/>
															<f:selectItems value="#{typeDocuments.typeDocumenstAsItems}" />
														</h:selectOneListbox>
														
														</h:panelGrid>															
													<rich:message showSummary="true" showDetail="false"
														for="typeDocumentSelect" />
														
												</a4j:outputPanel>
												<a4j:commandButton value="Modifier"
													action="#{processes.updateProcessus}"
													reRender="table"
													oncomplete="if (#{facesContext.maximumSeverity==null}) #{rich:component('editProcessus')}.hide();" />
											</h:panelGrid>
										</a4j:form>
									</rich:modalPanel>
								</div> <!---------------------------> <!-- End body 			   --> <!--------------------------->
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</f:view>
	<div id="footer">
		<tiles:insert definition="site.footer" flush="false" />
	</div>
</body>
</html>
