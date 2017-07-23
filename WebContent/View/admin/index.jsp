<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrador</title>
<link href="../../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="../../css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="../../css/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<jsp:include page="../../jsp/auth.jsp"></jsp:include>
	<jsp:include page="../menu.jsp"></jsp:include>
	<%
		if(request.getParameter("opcion")==null){
	%>
			<jsp:include page="../opciones.jsp"></jsp:include>
	<%
		}else if(request.getParameter("opcion").equalsIgnoreCase("sucursales")){
	%>
			<jsp:include page="../sucursal/SucursalView.jsp"></jsp:include>
	<% 
		}else if(request.getParameter("opcion").equalsIgnoreCase("clientes")){
	%>
			<jsp:include page="../ClienteView/ClienteView.jsp"></jsp:include>
	<%
		}else if(request.getParameter("opcion").equalsIgnoreCase("cajas")){
	%>
			<jsp:include page="../CajaView/CajaView.jsp"></jsp:include>	
	<% 
		}else if(request.getParameter("opcion").equalsIgnoreCase("ventas")){
	%>
			<jsp:include page="../VentaView/VentaView.jsp"></jsp:include>
	<%
		}
	%>
</body>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/bootstrap.min.js"></script>
</html>
