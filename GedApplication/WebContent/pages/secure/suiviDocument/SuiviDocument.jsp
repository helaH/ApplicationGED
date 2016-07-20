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
									<a4j:form id="form1">
										<rich:panel style="width: 900px;" >
											<f:facet name="header">
													<h:outputText value="Recherche Documents" />
											</f:facet>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="4">
														<h:outputText value="Id Document" />
														<h:inputText value="#{suiviDoc.idDocument}" />
														<h:outputText value="Nom Document" />
														<h:inputText id="nomDocument" value="#{suiviDoc.nomDocument}" />
														<h:outputText value="Process" />
														<h:selectOneListbox  
															value="#{suiviDoc.idProcessSelected}" size="1"
															>
															<f:selectItem itemLabel=""  itemValue=""/>
															<f:selectItems value="#{processes.processesAsItems}" />
														</h:selectOneListbox>														
														<h:outputText value="Date Creation" />
														<rich:calendar value="#{suiviDoc.dateCreation}"
															datePattern="dd-MM-yyyy" cellWidth="24px" cellHeight="22px"
															style="width:200px" />	
															<h:outputText value="Type Document" />
															<h:selectOneListbox 
																value="#{suiviDoc.idTypeDoc}" size="1"
																>
																<f:selectItem itemLabel=""  itemValue=""/>
																<f:selectItems value="#{typeDocuments.typeDocumenstAsItems}" />
															</h:selectOneListbox>																																					
													</h:panelGrid>
												</a4j:outputPanel>
												<h:panelGrid  columns="2">
													<a4j:commandButton value="Chercher document">
															<a4j:support event="onclick" 
																oncomplete="$('form2').removeClassName('hideOnLoad');$('form3').addClassName('hideOnLoad');"
																reRender="form2"></a4j:support>
													</a4j:commandButton>
													<a4j:commandButton value="initialiser" action="#{suiviDoc.init}" reRender="form2" oncomplete="$('form2').addClassName('hideOnLoad');$('form3').addClassName('hideOnLoad');"></a4j:commandButton>
												</h:panelGrid>
											</h:panelGrid>
										</rich:panel>
										
									</a4j:form>
									<rich:panel id="form2" style="width: 900px;border: none;background: transparent;"
									styleClass="hideOnLoad" >											
										<rich:dataTable id="table"
															onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
															onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
															cellpadding="0" cellspacing="0" border="1" width="800px"
															value="#{suiviDoc.documentRecherche}" var="documentVal" 
															rendered="true">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Id Document" />
													</f:facet>
													<a4j:form>
														<a4j:commandLink value="#{documentVal.idDoc}">
																<a4j:support event="onclick" reRender="form3"
																	oncomplete="$('form3').removeClassName('hideOnLoad');">
																	<f:setPropertyActionListener
																		target="#{instanceProcesses.idDocumentSelected}"
																		value="#{documentVal.idDoc}" />	
																																		
																</a4j:support>
														</a4j:commandLink>
													</a4j:form>									
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Nom Document" />
													</f:facet>
													<h:outputText value="#{documentVal.nomDoc}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="path Document" />
													</f:facet>
													<h:outputText value="#{documentVal.pathDoc}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Type document" />
													</f:facet>
													<h:outputText value="#{documentVal.typeDocument.nomType}" />
												</rich:column>
											</rich:dataTable>	
										</rich:panel>
										<rich:panel id="form3" style="width: 900px;"
									styleClass="hideOnLoad" >
											<f:facet name="header">
													<h:outputText value="Detail Document" />
											</f:facet>
											<rich:dataTable id="table3"
															onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
															onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
															cellpadding="0" cellspacing="0" border="1" width="800px"
															value="#{instanceProcesses.instanceProcessForDocument}" var="instanceProcessVal" 
															rendered="true">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Nom Document" />
													</f:facet>
													<h:outputText value="#{instanceProcessVal.id.document.nomDoc}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Nom Process" />
													</f:facet>
													<h:outputText value="#{instanceProcessVal.id.processus.nomProcess}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Nom Activites" />
													</f:facet>
													<h:outputText value="#{instanceProcessVal.id.activites.nomActivite}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Etat" />
													</f:facet>
													<h:outputText value="#{instanceProcessVal.etat}" />
												</rich:column>												
											</rich:dataTable>
										</rich:panel>
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
