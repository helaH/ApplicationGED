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
														value="#{historiqueModifis.historiqueModifis}" var="historiqueModifiVal" 
														rendered="true">
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Id Historique de Modification" />
												</f:facet>
												<h:outputText value="#{historiqueModifiVal.id}" />
											</rich:column>
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Date modification" />
												</f:facet>
												<h:outputText value="#{historiqueModifiVal.dateModif}" />
											</rich:column>
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Actions" />
												</f:facet>
												<a4j:form>
													<a4j:commandLink ajaxSingle="true"
														id="editHistoriqueModifiLink"
														oncomplete="#{rich:component('editHistoriqueModifi')}.show()">
														<h:graphicImage
															value="/images/css/style001/images/edit.gif"
															style="border:0" />
														<f:setPropertyActionListener value="#{historiqueModifiVal}"
															target="#{historiqueModifis.historiqueModifiSelected}" />
													</a4j:commandLink>
													<rich:toolTip for="editHistoriqueModifiLink"
														value="Modifier Historique de modification " />

													<a4j:commandLink ajaxSingle="true"
														id="deleteHistoriqueModifiLink"
														oncomplete="#{rich:component('deleteHistoriqueModifiLink')}.show()">
														<h:graphicImage
															value="/images/css/style001/images/delete.gif"
															style="border:0" />
														<f:setPropertyActionListener value="#{historiqueModifiVal}"
															target="#{historiqueModifis.historiqueModifiSelected}" />
													</a4j:commandLink>
													<rich:toolTip for="deleteHistoriqueModifiLink"
														value="Supprimer Historique de modification" />
												</a4j:form>	
											</rich:column>
										</rich:dataTable>										
									<fieldset >
											<legend>Ajout Historique</legend>
									<h:form>
										<h:panelGroup>
											<h:panelGrid columns="2">
												<h:outputText value="Date de modification" />
												<h:inputText value="#{historiqueModifis.dateModif}" />
												
													
												<h:commandButton value="Ajouter Historique"
													action="#{historiqueModifis.addHistoriqueModifis}" />
											</h:panelGrid>
										</h:panelGroup>
									</h:form>
								</fieldset>
								<!-- Delete HistoriqueModif -->
									<rich:modalPanel id="deleteHistoriqueModifi" moveable="true"
										resizeable="true" minWidth="350" minHeight="100">
										<f:facet name="header">
											<h:outputText value="Supprimer Historique ?"
												style="padding-right:15px;" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													styleClass="hidelink" id="hidelink4" />
												<rich:componentControl for="deleteHistoriqueModifi"
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
																action="#{historiqueModifi.deleteHistoriqueModifi}"
																onclick="#{rich:component('deleteHistoriqueModifi')}.hide();"
																reRender="table" />
														</td>
														<td align="center" width="50%"><a4j:commandButton
																value="Cancel"
																onclick="#{rich:component('deleteHistoriqueModifi')}.hide();"
																reRender="table" >
																</a4j:commandButton>
														</td>
													</tr>
												</tbody>
											</table>
										</a4j:form>
									</rich:modalPanel>
									<!-- Edit  Bloc -->
									<rich:modalPanel id="editHistoriqueModifi" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Modification Historique:" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hideHistoriqueModifilink" styleClass="hidelink" />
												<rich:componentControl for="editHistoriqueModifi"
													attachTo="hideHistoriqueModifilink" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">
														<h:outputText value="Date de modification:" />
														<h:inputText value="#{historiqueModifis.historiqueModifiSelected.dateModifi}" />
														
														</h:panelGrid>															
													
												</a4j:outputPanel>
												<a4j:commandButton value="Save"
													action="#{historiqueModifis.updateHistoriqueModifi}"
													reRender="table"
													onclick="#{rich:component('editHistoriqueModifi')}.hide();" />
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
