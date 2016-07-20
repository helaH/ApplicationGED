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
										
										<rich:dataTable id="table"
														onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
														onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
														cellpadding="0" cellspacing="0" border="1" width="800px"
														value="#{activites.activites}" var="activiteVal" 
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
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Next activite ok" />
												</f:facet>
												<h:outputText value="#{activiteVal.activitesByNextActiviteOk}" />
											</rich:column>
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Next activite ko" />
												</f:facet>
												<h:outputText value="#{activiteVal.activitesByNextActiviteKO}" />
											</rich:column>											
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Processus" />
												</f:facet>
												<h:outputText value="#{activiteVal.processus.nomProcess}" />
											</rich:column>
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
									<fieldset >
											<legend>Ajout Activite</legend>
									<h:form>
										<h:panelGroup>
											<h:panelGrid columns="2">
												<h:outputText value="Nom d'activite" />
												<h:inputText value="#{activites.nomActivite}" />
												<h:outputText value="Type d'activite" />
												<h:inputText value="#{activites.typeActivite}" />
												<h:outputText value="Next Activite ok" />
												<h:inputText value="#{activites.activitesByNextActiviteOk}" />
												<h:outputText value="Next Activite ko" />
												<h:inputText value="#{activites.activitesByNextActiviteKO}" />
												<h:outputText value="Role" />
												<h:selectOneListbox 
													value="#{activites.idRole}" size="1"
													>
													<f:selectItem itemLabel=""  itemValue=""/>
													<f:selectItems value="#{roles.rolesAsItems}" />
												</h:selectOneListbox>
												<h:outputText value="Processus" />
												<h:selectOneListbox 
													value="#{activites.idProcess}" size="1"
													>
													<f:selectItem itemLabel=""  itemValue=""/>
													<f:selectItems value="#{Processes.processesAsItems}" />
												</h:selectOneListbox>

												<h:commandButton value="Ajouter Employe"
													action="#{activites.addActivite}" />
											</h:panelGrid>
										</h:panelGroup>
									</h:form>
								</fieldset>
								<!-- Delete Activities -->
									<rich:modalPanel id="deleteActivite" moveable="true"
										resizeable="true" minWidth="350" minHeight="100">
										<f:facet name="header">
											<h:outputText value="Supprimer Activte ?"
												style="padding-right:15px;" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													styleClass="hidelink" id="hidelink4" />
												<rich:componentControl for="deleteActivite"
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
																action="#{activites.deleteActivte}"
																onclick="#{rich:component('deleteActivte')}.hide();"
																reRender="table" />
														</td>
														<td align="center" width="50%"><a4j:commandButton
																value="Cancel"
																onclick="#{rich:component('deleteActivte')}.hide();"
																reRender="table" >
																</a4j:commandButton>
														</td>
													</tr>
												</tbody>
											</table>
										</a4j:form>
									</rich:modalPanel>
									<!-- Edit  Bloc -->
									<rich:modalPanel id="editEmploye" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Modification Activte:" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hideActivitelink" styleClass="hidelink" />
												<rich:componentControl for="editActivite"
													attachTo="hideActivitelink" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">
														<h:outputText value="Nom d'activite" />
														<h:inputText value="#{activites.activiteSelected.nomActivite}" />
														<h:outputText value="Type d'activite" />
														<h:inputText value="#{activites.activiteSelected.typeActivite}" />
														<h:outputText value="Next activite ok" />
														<h:inputText value="#{activites.activiteSelected.activitesByNextActiviteOk}" />
														<h:outputText value="Next activite ko" />
														<h:inputText value="#{activites.activiteSelected.activitesByNextActiviteKO}" />
																
														<h:outputText value="Role" />
														<h:selectOneListbox  id="roleSelect"
															value="#{activites.idRoleSelected}" size="1"
															>
															<f:selectItem itemLabel=""  itemValue=""/>
															<f:selectItems value="#{roles.rolesAsItems}" />
														</h:selectOneListbox>
														
														<h:outputText value="Processus" />
														<h:selectOneListbox  id="processusSelect"
															value="#{activites.idProcessSelected}" size="1"
															>
															<f:selectItem itemLabel=""  itemValue=""/>
															<f:selectItems value="#{processes.processesAsItems}" />
														</h:selectOneListbox>
													</h:panelGrid>															
													<rich:message showSummary="true" showDetail="false"
														for="roleSelect" />
													<rich:message showSummary="true" showDetail="false"
														for="processusSelect" />	
												</a4j:outputPanel>
												<a4j:commandButton value="Save"
													action="#{activites.updateActivite}"
													reRender="table"
													onclick="#{rich:component('editActivite')}.hide();" />
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
