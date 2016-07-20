<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/images/css/style001/default.css" />
			<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/images/css/style001/Style.css" />
	</head>
	<body>
		<f:view>
			<table border="0" width="100%" cellspacing="5">
				<tr>
					<td colspan="2">
						<table cellspacing="0" cellpadding="5" border="0" width="100%">
						<tr>
						  <td>
						      <span class="Style3">
						      		<font> 
						    			<img  src="<%=request.getContextPath()%>/images/banner.jpg" align="top" border="1">
						    		</font> 
						   		    	        
						   	</span>
						      <hr>  </td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="middle"  align="center">
					<!--------------------------->
					<!-- insert here your body -->
					<!--------------------------->
					
						<f:loadBundle basename="MessageResources_fr" var="msg" />
						<div class="login">
							<h:form>		
								<h:panelGrid columns="2">									
									<h:outputText value="Login"></h:outputText>
									<h:inputText value="#{loginGed.login}" />
									
									<h:outputText value="Password"></h:outputText>
									<h:inputSecret value="#{loginGed.password}"/>
									
									<h:commandButton value="Valider" action="#{loginGed.log}"></h:commandButton>
								</h:panelGrid>
								<h:messages styleClass="errorMessages" showSummary="true" showDetail="true"/>
							</h:form>
						</div>
					
					<!--------------------------->		  		
					<!-- End body 			   -->
					<!--------------------------->
					</td>
				</tr>
				<tr>
					<td colspan="2" valign="bottom" >
						
					</td>
				</tr>
			</table>
		</f:view>
	</body>
</html>