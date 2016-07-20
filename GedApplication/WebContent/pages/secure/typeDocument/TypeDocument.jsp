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
														value="#{typeDocuments.typeDocuments}" var="typeDocumentVar"
														rendered="true">										
										<rich:column>
											<f:facet name="header">
												<h:outputText value="Numéro Type de document" />
											</f:facet>
											<h:outputText value="#{typeDocumentVar.idTypeDocument}" /> <!-- abstract -->
										</rich:column>
										<rich:column>
											<f:facet name="header">
												<h:outputText value="Nom Type de document" />
											</f:facet>
											<h:outputText value="#{typeDocumentVar.nomType}" />
										</rich:column>
										<rich:column>
											<f:facet name="header">
												<h:outputText value="Format de document" />
											</f:facet>
											<h:outputText value="#{typeDocumentVar.formatDoc}" />
										</rich:column>
										<rich:column>
											<f:facet name="header">
												<h:outputText value="Extension de document" />
											</f:facet>
											<h:outputText value="#{typeDocumentVar.extensionDoc}" />
										</rich:column>
										<rich:column>
											<f:facet name="header">
												<h:outputText value="Actions" />
											</f:facet>
											<a4j:form>
												<a4j:commandLink ajaxSingle="true"
													id="editTypeDocumentLink"
													oncomplete="#{rich:component('editTypeDocument')}.show()">
													<h:graphicImage
														value="/images/css/style001/images/edit.gif"
														style="border:0" />
													<f:setPropertyActionListener value="#{typeDocumentVar}"
														target="#{typeDocuments.typeDocumentSelected}" />
												</a4j:commandLink>
												<rich:toolTip for="editTypeDocumentLink"
													value="Modifier Type de document" />

												<a4j:commandLink ajaxSingle="true"
													id="deleteTypeDocumentLink"
													oncomplete="#{rich:component('deleteTypeDocument')}.show()">
													<h:graphicImage
														value="/images/css/style001/images/delete.gif"
														style="border:0" />
													<f:setPropertyActionListener value="#{typeDocumentVar}"
														target="#{typeDocuments.typeDocumentSelected}" />
												</a4j:commandLink>
												<rich:toolTip for="deleteTypeDocumentLink"
													value="Supprimer Type de document" />
											</a4j:form>	
										</rich:column>
									</rich:dataTable>
									<br/>	
									<br/>
									<rich:panel style="width:800px">
										<f:facet name="header">
												<h:outputText value="Ajout Type Document" />
											</f:facet>
									<h:form>
										<h:panelGroup>
											<h:panelGrid columns="2">
												<h:outputText value="Nom Type de document" />
												<h:inputText value="#{typeDocuments.nomTypeDoc}" /><!-- TypeDocuments -->
												<h:outputText value="Format de document" />
												<h:inputText value="#{typeDocuments.formatDoc}" />
												<h:outputText value="Extension de document" />
												<h:inputText value="#{typeDocuments.extensionDoc}" />
																								
												<h:commandButton value="Ajouter Type de document"
													action="#{typeDocuments.addTypeDocument}" />
											</h:panelGrid>
										</h:panelGroup>
									</h:form>
									</rich:panel>
																			
											<!-- Delete Type Document -->
									<rich:modalPanel id="deleteTypeDocument" moveable="true"
										resizeable="true" minWidth="350" minHeight="100">
										<f:facet name="header">
											<h:outputText value="Supprimer type de document?"
												style="padding-right:15px;" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													styleClass="hidelink" id="hidelink4" />
												<rich:componentControl for="deleteTypeDocument"
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
																action="#{typeDocuments.deleteTypeDocument}"
																onclick="#{rich:component('deleteTypeDocument')}.hide();"
																reRender="table" />
														</td>
														<td align="center" width="50%"><a4j:commandButton
																value="Cancel"
																onclick="#{rich:component('deleteTypeDocument')}.hide();"
																reRender="table" >
																</a4j:commandButton>
														</td>
													</tr>
												</tbody>
											</table>
										</a4j:form>
									</rich:modalPanel>
									<!-- Edit  Type document -->
									<rich:modalPanel id="editTypeDocument" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Modification Type de document:" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hideTypeDocumentlink" styleClass="hidelink" />
												<rich:componentControl for="editTypeDocument"
													attachTo="hideTypeDocumentlink" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">
														<h:outputText value="Nom Type de document" />
														<h:inputText value="#{typeDocuments.typeDocumentSelected.nomType}" />
														<h:outputText value="Format de document" />
														<h:inputText  value="#{typeDocuments.typeDocumentSelected.formatDoc}" />
														<h:outputText value="Extension de document" />
														<h:inputText id="typeDocumentSelect" value="#{typeDocuments.typeDocumentSelected.extensionDoc}" />
													</h:panelGrid>															
													<rich:message showSummary="true" showDetail="false"
														for="typeDocumentSelect" />
												</a4j:outputPanel>
												<a4j:commandButton value="Save"
													action="#{typeDocuments.updateTypeDocument}"
													reRender="table"
													onclick="#{rich:component('editTypeDocument')}.hide();" />
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
