<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
	
		<title>My JSF 'MyJsp.jsp' starting page</title>
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
	
	</head>
	<body>
		<f:view>
			<table border="0" width="100%" cellspacing="5">
				<tr>
						<tiles:insert definition="site.header" flush="false"/> 
				</tr>
				<tr>
					<td width="140" valign="top">
						<tiles:insert definition="site.menu" flush="false"/> 
					</td>
					<td valign="top"  align="left">
					<!--------------------------->
					<!-- insert here your body -->
					<!--------------------------->
					

					
					<!--------------------------->		  		
					<!-- End body 			   -->
					<!--------------------------->
					</td>
				</tr>
				<tr>
						<tiles:insert definition="site.footer" flush="false"/> 
				</tr>
			</table>
		</f:view>
	</body>
</html>