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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
														value="#{instanceProcesses.instanceProcess}" var="instanceProcessVal" 
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
											<rich:column>
												<f:facet name="header">
													<h:outputText value="Actions" />
												</f:facet>
												<a4j:form>
													<a4j:commandLink ajaxSingle="true"
														id="editInstanceProcessLink"
														oncomplete="#{rich:component('editInstanceProcess')}.show()">
														<h:graphicImage
															value="/images/css/style001/images/edit.gif"
															style="border:0" />
														<f:setPropertyActionListener value="#{instanceProcessVal}"
															target="#{instanceProcesses.instanceProcessSelected}" />
													</a4j:commandLink>
													<rich:toolTip for="editInstanceProcessLink"
														value="Valider Etape" />												
												</a4j:form>	
											</rich:column>
										</rich:dataTable>										
									
								
									<!-- Validate  Etape -->
									<rich:modalPanel id="editInstanceProcess" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Valider Etape" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hideInstanceProcesslink" styleClass="hidelink" />
												<rich:componentControl for="editInstanceProcess"
													attachTo="hideInstanceProcesslink" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="3">
														<h:outputText value="Etat" />														
														<h:selectOneListbox  id="etatSel"
															value="#{instanceProcesses.instanceProcessSelected.etat}" size="1"
															required="true" requiredMessage="Veuillez choisir une valeur">
															<f:selectItem itemLabel=""  itemValue=""/>
															<f:selectItem itemLabel="En Cours"  itemValue="EnCours"/>
															<f:selectItem itemLabel="OK"  itemValue="OK"/>
															<f:selectItem itemLabel="KO"  itemValue="KO"/>	
															<f:selectItem itemLabel="Terminer Process"  itemValue="Fin"/>	
															<rich:ajaxValidator event="onblur" />														
														</h:selectOneListbox>														
														<rich:message for="etatSel" />	
													</h:panelGrid>															
												
												</a4j:outputPanel>
												<a4j:commandButton value="Save"
													action="#{instanceProcesses.updateInstanceProcess}"
													reRender="table"
													oncomplete="if (#{facesContext.maximumSeverity==null})#{rich:component('editInstanceProcess')}.hide();" />
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
