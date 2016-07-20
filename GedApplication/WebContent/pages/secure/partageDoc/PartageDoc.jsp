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
				<td colspan="2" height="20%"><tiles:insert
						definition="site.header" flush="false" /></td>
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
											
									<rich:tabPanel switchType="client" width="900px" >
										<rich:tab label="Modifier Partage">
											<a4j:form>
												<rich:dataTable id="table1"
													onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
													onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
													cellpadding="0" cellspacing="0" border="1" width="800px"
													value="#{partageDocs.partageDocs}" var="partageVal"
													rendered="true">
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Nom document" />
														</f:facet>
														<h:outputText value="#{partageVal.id.document.nomDoc}" />
													</rich:column>	
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Nom Role" />
														</f:facet>
														<h:outputText value="#{partageVal.id.role.nomRole}" />
													</rich:column>	
														<rich:column>
														<f:facet name="header">
															<h:outputText value="Date de partage" />
														</f:facet>
														<h:outputText value="#{partageVal.datePartage}" />
													</rich:column>												
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Action" />
														</f:facet>
														<a4j:commandButton value="Modifier Partage" 
															oncomplete="#{rich:component('modifPartageDoc')}.show()">
																<f:setPropertyActionListener value="#{partageVal.id.document.idDoc}"
																		target="#{partageDocs.idDocumentSelected}" />														
														</a4j:commandButton>
													
														<a4j:commandButton value="supprimer Partage" action="#{partageDocs.deletePartageDoc}"
															 reRender="table1,table" >
																<f:setPropertyActionListener value="#{partageVal}"
																		target="#{partageDocs.partageDocSelected}" />														
														</a4j:commandButton>															
													</rich:column>
												</rich:dataTable>
											</a4j:form>
										</rich:tab>
										<rich:tab label="Ajouter Partage(Documents non partagés)">
											<a4j:form>
												<rich:dataTable id="table"
													onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
													onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
													cellpadding="0" cellspacing="0" border="1" width="800px"
													value="#{documents.documentsNonPartages}" var="docmentVal"
													rendered="true">
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Nom document" />
														</f:facet>
														<h:outputText value="#{docmentVal.nomDoc}" />
													</rich:column>												
													<rich:column>
														<f:facet name="header">
														
															<h:outputText value="Partager?" />
														</f:facet>
													
														<a4j:commandButton value="Partager" 
															oncomplete="#{rich:component('partageDoc')}.show()">
																<f:setPropertyActionListener value="#{docmentVal.idDoc}"
																		target="#{partageDocs.idDocumentSelected}" />
														
														</a4j:commandButton>													
													</rich:column>
												</rich:dataTable>
											</a4j:form>
										</rich:tab>
									</rich:tabPanel>
									<rich:modalPanel id="partageDoc" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Partage Document" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hidepartageDoclink" styleClass="hidelink" />
												<rich:componentControl for="partageDoc"
													attachTo="hidepartageDoclink" operation="hide"
													event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">
														<f:facet name="header">
															<h:outputText value="Role" />
														</f:facet>
														<rich:pickList id="roles"
																value="#{partageDocs.rolesList}" 
																>
																<f:selectItems value="#{partageDocs.rolesNonPartagesAsItems}" />
															</rich:pickList>
														
														</h:panelGrid>
														<rich:message showSummary="true" showDetail="false"
															for="roles" />
												</a4j:outputPanel>
												<a4j:commandButton value="Confirmer le partage"
													action="#{partageDocs.partager}" reRender="table1,table"
													oncomplete="if (#{facesContext.maximumSeverity==null}) #{rich:component('partageDoc')}.hide();" />
											</h:panelGrid>
										</a4j:form>
									</rich:modalPanel>
									<rich:modalPanel id="modifPartageDoc" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Modifier Partage Document" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hidemodifPartageDoclink" styleClass="hidelink" />
												<rich:componentControl for="modifPartageDoc"
													attachTo="hidepartageDoclink" operation="hide"
													event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">
														<f:facet name="header">
															<h:outputText value="Role" />
														</f:facet>
														<rich:pickList id="roles"
																value="#{partageDocs.rolesList}" 
																><f:selectItems value="#{partageDocs.rolesNonPartagesAsItems}" />
															</rich:pickList>
														
														</h:panelGrid>
														<rich:message showSummary="true" showDetail="false"
															for="roles" />
												</a4j:outputPanel>
												<a4j:commandButton value="Confirmer la modification"
													action="#{partageDocs.partager}" reRender="table1,table"
													oncomplete="if (#{facesContext.maximumSeverity==null}) #{rich:component('modifPartageDoc')}.hide();" />
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
