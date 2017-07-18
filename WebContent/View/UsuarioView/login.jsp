<!-- Hacemos las importaciones de los archivos nesesarios -->
<%@page import="org.apache.jasper.JasperException"%>
<%@page import="javax.persistence.criteria.CriteriaBuilder.In"%>
<div class="col-md-5 col-md-offset-3 col-sm-6 col-sm-offset-3 col-lg-6 col-lg-offset-3 margen_login"d>
	<div class="background-login">
		<h3 class="center-text text-white">Punto de venta</h3>
	</div>
	<div class="formulario-login">
		<form action="UsuarioController" method="POST">
			<input type="hidden" value="login" name="operacion"/>
			<div class="form-group">
				<label>Usuario/Correo</label>
				<input type="text" name="user" class="form-control" placeholder="Usuario/Correo...">
			</div>
			<div class="form-group">
				<label>Contraseña</label>
				<input type="password" name="password" class="form-control" placeholder="Contraseña..."/>
			</div>
			<center>
				<button class="btn btn-default cen">Iniciar Session <i class="glyphicon glyphicon-ok"></i></button>
			</center>
			<%	
				if(request.getParameter("err")!=null){
			%>
				<br><div class="alert alert-danger alert-dismissible" role="alert">
  					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
 					<strong>Cuidado!</strong> Ocurrio un error al intentar ingresar
				</div>
			<% 
				}
			%>
		</form>
	</div>
	<% 
		//OBTENEMOS LA SESSION PARA COMPROBAR SI UN USUARIO ESTA INICIADO SESSION
		HttpSession ses = request.getSession();
		
		//SACAMOS EL NOMBRE DEL USUARIO SI ES QUE EXISTE
		String username = (String) ses.getAttribute("username");
		//SI TODO SALIO BIEN COMPROBAMOS QUE EL NOMBRE NO ESTE VACIO
		if(username != null){
			System.out.print("si");
			//SACAMOS EL TIPO DE USUARIO PARA DEPENDIENDO EL TIPO TE REDIRECCIONE A TAL PAGINA
			int tipoUsuario = (Integer) ses.getAttribute("tipo_usuario");
			//SI EL TIPO ES UNO ENTONCES SIGNIFICA QUE ES ADMINISTRADOR
			//USAMOS LA FUNCION DE JAVASCRIPT PARA REDIRECCIONAR POR QUE LA DE JSP DIO PROBLEMAS
			if(tipoUsuario == 1){
	%>
				<script type="text/javascript">
					document.location = "View/admin"
				</script>
	<%	
				}			
			}else{
				System.out.print("No existe");
			}
	%>
	<div class="background-login">
		<h3 class="center-text text-white">
			Sistema de punto de venta
		</h3>
	</div>
</div>