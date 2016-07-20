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
														value="#{employes.employes}" var="employeVal" 
														rendered="true">
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Id Employe" />
												</f:facet>
												<h:outputText value="#{employeVal.idEmploye}" />
											</rich:column>
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Nom" />
												</f:facet>
												<h:outputText value="#{employeVal.nomEmploye}" />
											</rich:column>
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Prenom" />
												</f:facet>
												<h:outputText value="#{employeVal.prenomEmploye}" />
											</rich:column>
											<rich:column>
												<f:facet name="header">
													<h:outputText value="CIN" />
												</f:facet>
												<h:outputText value="#{employeVal.cinEmploye}" />
											</rich:column>
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Login" />
												</f:facet>
												<h:outputText value="#{employeVal.login}" />
											</rich:column>											
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Adresse" />
												</f:facet>
												<h:outputText value="#{employeVal.adresseEmploye}" />
											</rich:column>
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Role" />
												</f:facet>
												<h:outputText value="#{employeVal.role.nomRole}" />
											</rich:column>	
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Actions" />
												</f:facet>
												<a4j:form>
													<a4j:commandLink ajaxSingle="true"
														id="editEmployeLink"
														oncomplete="#{rich:component('editEmploye')}.show()">
														<h:graphicImage
															value="/images/css/style001/images/edit.gif"
															style="border:0" />
														<f:setPropertyActionListener value="#{employeVal}"
															target="#{employes.employeSelected}" />
													</a4j:commandLink>
													<rich:toolTip for="editEmployeLink"
														value="Modifier Employe" />

													<a4j:commandLink ajaxSingle="true"
														id="deleteEmployeLink"
														oncomplete="#{rich:component('deleteEmploye')}.show()">
														<h:graphicImage
															value="/images/css/style001/images/delete.gif"
															style="border:0" />
														<f:setPropertyActionListener value="#{employeVal}"
															target="#{employes.employeSelected}" />
													</a4j:commandLink>
													<rich:toolTip for="deleteEmployeLink"
														value="Supprimer Employe" />
												</a4j:form>	
											</rich:column>
										</rich:dataTable>	
										<br/>	
										<br/>								
									<rich:panel style="width:800px">
										<f:facet name="header">
												<h:outputText value="Ajout Employe" />
											</f:facet>
											
									<h:form>
										<h:panelGroup>
											<h:panelGrid columns="2">
												<h:outputText value="Nom" />
												<h:inputText value="#{employes.nomEmploye}" />
												<h:outputText value="Prenom" />
												<h:inputText value="#{employes.prenomEmploye}" />
												<h:outputText value="CIN" />
												<h:inputText value="#{employes.cinEmploye}" />
												<h:outputText value="Login" />
												<h:inputText value="#{employes.login}" />
												<h:outputText value="Password" />
												<h:inputText value="#{employes.password}" />
												<h:outputText value="Adresse" />
												<h:inputText value="#{employes.adresseEmploye}" />

												<h:outputText value="Role" />
												<h:selectOneListbox 
													value="#{employes.idRole}" size="1"
													>
													<f:selectItem itemLabel=""  itemValue=""/>
													<f:selectItems value="#{roles.rolesAsItems}" />
												</h:selectOneListbox>

												<h:commandButton value="Ajouter Employe"
													action="#{employes.addEmploye}" />
											</h:panelGrid>
										</h:panelGroup>
									</h:form>
								</rich:panel>
								<!-- Delete Employe -->
									<rich:modalPanel id="deleteEmploye" moveable="true"
										resizeable="true" minWidth="350" minHeight="100">
										<f:facet name="header">
											<h:outputText value="Supprimer Employe ?"
												style="padding-right:15px;" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													styleClass="hidelink" id="hidelink4" />
												<rich:componentControl for="deleteEmploye"
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
																action="#{employes.deleteEmploye}"
																onclick="#{rich:component('deleteEmploye')}.hide();"
																reRender="table" />
														</td>
														<td align="center" width="50%"><a4j:commandButton
																value="Cancel"
																onclick="#{rich:component('deleteEmploye')}.hide();"
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
											<h:outputText value="Modification Employe:" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hideEmployelink" styleClass="hidelink" />
												<rich:componentControl for="editEmploye"
													attachTo="hideEmployelink" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">
														<h:outputText value="Nom" />
														<h:inputText value="#{employes.employeSelected.nomEmploye}" />
														<h:outputText value="Prenom" />
														<h:inputText value="#{employes.employeSelected.prenomEmploye}" />
														<h:outputText value="CIN" />
														<h:inputText value="#{employes.employeSelected.cinEmploye}" />
														<h:outputText value="Login" />
														<h:inputText value="#{employes.employeSelected.login}" />
														<h:outputText value="Password" />
														<h:inputText value="#{employes.employeSelected.password}" />
														<h:outputText value="Adresse" />
														<h:inputText value="#{employes.employeSelected.adresseEmploye}" />
		
														<h:outputText value="Role" />
														<h:selectOneListbox  id="roleSelect"
															value="#{employes.idRoleSelected}" size="1"
															>
															<f:selectItem itemLabel=""  itemValue=""/>
															<f:selectItems value="#{roles.rolesAsItems}" />
														</h:selectOneListbox>
													</h:panelGrid>															
													<rich:message showSummary="true" showDetail="false"
														for="roleSelect" />
												</a4j:outputPanel>
												<a4j:commandButton value="Save"
													action="#{employes.updateEmploye}"
													reRender="table"
													onclick="#{rich:component('editEmploye')}.hide();" />
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
