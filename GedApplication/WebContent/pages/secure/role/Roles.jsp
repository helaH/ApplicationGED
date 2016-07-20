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
														value="#{roles.roles}" var="roleVar"
														rendered="true">										
										<rich:column>
											<f:facet name="header">
												<h:outputText value="Numéro Role" />
											</f:facet>
											<h:outputText value="#{roleVar.idRole}" />
										</rich:column>
										<rich:column>
											<f:facet name="header">
												<h:outputText value="Nom Role" />
											</f:facet>
											<h:outputText value="#{roleVar.nomRole}" />
										</rich:column>
										<rich:column>
											<f:facet name="header">
												<h:outputText value="Actions" />
											</f:facet>
											<a4j:form>
												<a4j:commandLink ajaxSingle="true"
													id="editRoleLink"
													oncomplete="#{rich:component('editRole')}.show()">
													<h:graphicImage
														value="/images/css/style001/images/edit.gif"
														style="border:0" />
													<f:setPropertyActionListener value="#{roleVar}"
														target="#{roles.roleSelected}" />
												</a4j:commandLink>
												<rich:toolTip for="editRoleLink"
													value="Modifier Role" />

												<a4j:commandLink ajaxSingle="true"
													id="deleteRoleLink"
													oncomplete="#{rich:component('deleteRole')}.show()">
													<h:graphicImage
														value="/images/css/style001/images/delete.gif"
														style="border:0" />
													<f:setPropertyActionListener value="#{roleVar}"
														target="#{roles.roleSelected}" />
												</a4j:commandLink>
												<rich:toolTip for="deleteRoleLink"
													value="Supprimer Role" />
											</a4j:form>	
										</rich:column>
									</rich:dataTable>
										<br/>	
										<br/>
									<rich:panel style="width:800px">
										<f:facet name="header">
												<h:outputText value="Ajout Role" />
											</f:facet>
									<h:form>
										<h:panelGroup>
											<h:panelGrid columns="2">
												<h:outputText value="Nom Role" />
												<h:inputText value="#{roles.nomRole}" />
												<h:commandButton value="Ajouter role"
													action="#{roles.addRole}" />
											</h:panelGrid>
										</h:panelGroup>
									</h:form>
									</rich:panel>
																			
											<!-- Delete Role -->
									<rich:modalPanel id="deleteRole" moveable="true"
										resizeable="true" minWidth="350" minHeight="100">
										<f:facet name="header">
											<h:outputText value="Supprimer Role ?"
												style="padding-right:15px;" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													styleClass="hidelink" id="hidelink4" />
												<rich:componentControl for="deleteRole"
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
																action="#{roles.deleteRole}"
																onclick="#{rich:component('deleteRole')}.hide();"
																reRender="table" />
														</td>
														<td align="center" width="50%"><a4j:commandButton
																value="Cancel"
																onclick="#{rich:component('deleteRole')}.hide();"
																reRender="table" >
																</a4j:commandButton>
														</td>
													</tr>
												</tbody>
											</table>
										</a4j:form>
									</rich:modalPanel>
									<!-- Edit  Role -->
									<rich:modalPanel id="editRole" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Modification Role:" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hideRolelink" styleClass="hidelink" />
												<rich:componentControl for="editRole"
													attachTo="hideRolelink" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">
														<h:outputText value="Nom Role" />
														<h:inputText id="roleSelect" value="#{roles.roleSelected.nomRole}" />
													</h:panelGrid>															
													<rich:message showSummary="true" showDetail="false"
														for="roleSelect" />
												</a4j:outputPanel>
												<a4j:commandButton value="Save"
													action="#{roles.updateRole}"
													reRender="table"
													onclick="#{rich:component('editRole')}.hide();" />
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
