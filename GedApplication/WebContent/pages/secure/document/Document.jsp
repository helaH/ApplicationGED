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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
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
										<rich:tab label="Document Ajoutés" >
											<rich:dataTable id="table"
															onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
															onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
															cellpadding="0" cellspacing="0" border="1" width="800px"
															value="#{documents.documentsForUser}" var="documentVal" 
															rendered="true">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Id Document" />
													</f:facet>
													<h:outputText value="#{documentVal.idDoc}" />									
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
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Actions" />
													</f:facet>
													<a4j:form>
														<a4j:commandLink ajaxSingle="true"
															id="editDocumentLink"
															oncomplete="#{rich:component('editDocument')}.show()">
															<h:graphicImage
																value="/images/css/style001/images/edit.gif"
																style="border:0" />
															<f:setPropertyActionListener value="#{documentVal}"
																target="#{documents.documentSelected}" />														
														</a4j:commandLink>
														<rich:toolTip for="editDocumentLink"
															value="Modifier Document" />
													</a4j:form>
												</rich:column>
											</rich:dataTable>	
										</rich:tab>
										<rich:tab label="Ajout Document" >
											<fieldset >
												<h:form>
													<h:panelGroup>
														<h:panelGrid columns="2">
															<h:outputText value="Document" />
															<rich:fileUpload  fileUploadListener="#{documents.listener}" id="upload" acceptedTypes="jpg, gif, png, bmp,doc,docx,pdf,txt,xml" immediateUpload="true"
												                ontyperejected="alert('Only JPG, GIF, PNG, BMP, DOC, DOCX, PFD, TXT et XML files are accepted');" maxFilesQuantity="1">
												                <a4j:support event="onuploadcomplete" reRender="info" />
												            </rich:fileUpload>
															<h:outputText value="Type Document" />
															<h:selectOneListbox 
																value="#{documents.idTypeDoc}" size="1"
																>
																<f:selectItem itemLabel=""  itemValue=""/>
																<f:selectItems value="#{typeDocuments.typeDocumenstAsItems}" />
																<a4j:support event="onchange" reRender="selectProcess" >
																	<f:setPropertyActionListener value="#{documents.idTypeDoc}"
																			target="#{processes.idTypeDocumentSelected}" />	
																</a4j:support>
															</h:selectOneListbox>
															<h:outputText value="Process"  />
															<h:selectOneListbox id="selectProcess"
																value="#{documents.idProcess}" size="1"
																>
																<f:selectItem itemLabel=""  itemValue=""/>
																<f:selectItems value="#{processes.processesFromTypeDocumentAsItems}" />													
															</h:selectOneListbox>
			
															<h:commandButton value="Ajouter Document"
																action="#{documents.addDocument}" />
														</h:panelGrid>
													</h:panelGroup>
												</h:form>
											</fieldset>
									</rich:tab>
								</rich:tabPanel>
								
								<!-- Edit  Document -->
									<rich:modalPanel id="editDocument" autosized="true"
										moveable="true" width="450">
										<f:facet name="header">
											<h:outputText value="Modification Document:" />
										</f:facet>
										<f:facet name="controls">
											<h:panelGroup>
												<h:graphicImage
													value="/images/css/style001/images/close.png"
													id="hideDocumentlink" styleClass="hidelink" />
												<rich:componentControl for="editDocument"
													attachTo="hideDocumentlink" operation="hide" event="onclick" />
											</h:panelGroup>
										</f:facet>
										<a4j:form>
											<rich:messages style="color:red;"></rich:messages>
											<h:panelGrid columns="1">
												<a4j:outputPanel ajaxRendered="true">
													<h:panelGrid columns="2">
														<h:outputText value="Nom Document" />
														<h:inputText value="#{documents.documentSelected.nomDoc}" />
													</h:panelGrid>													
												</a4j:outputPanel>
												<a4j:commandButton value="Modifier"
													action="#{documents.updateDocument}"
													reRender="table"
													oncomplete="if (#{facesContext.maximumSeverity==null})#{rich:component('editDocument')}.hide();" />
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
