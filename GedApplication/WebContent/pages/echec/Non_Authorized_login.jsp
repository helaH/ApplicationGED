<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


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

		
	
	</head>
	<body>
			<table border="0" width="100%" cellspacing="5">
				<tr>
					<td colspan="2">				
							<tiles:insert definition="site.header" flush="false"/>						
					</td>
				</tr>
				<tr>
					<td>
						<table border="0" width="100%">
							<tr>
								
								<td colspan="2" valign="middle"  align="center">
								<!--------------------------->
								<!-- insert here your body -->
								<!--------------------------->
									<div align="center">
											You do not have access to this page. :( <p></p> You must be registered ! 	
									</div>							
								<!--------------------------->		  		
								<!-- End body 			   -->
								<!--------------------------->
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>

						
						

				</tr>
				
			</table>

			<div id="footer">
				<tiles:insert definition="site.footer" flush="false"/> 
			</div>
	</body>
</html>
