<!-- Hacemos las importaciones de los archivos nesesarios -->
<%@page import="javax.persistence.criteria.CriteriaBuilder.In"%>
<div class="col-md-5 col-md-offset-3 col-sm-6 col-sm-offset-3 col-lg-6 col-lg-offset-3 margen_login"d>
	<div class="background-login">
		<h3 class="center-text text-white">Punto de venta</h3>
	</div>
	<div class="formulario-login">
		<form action="LoginController" method="POST">
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
				<div class="alert alert-danger alert-dismissible" role="alert">
  					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
 					<strong>Warning!</strong> Better check yourself, you're not looking too good.
				</div>
			<% 
				}
			%>
		</form>
	</div>
	<% 
		
		HttpSession ses = request.getSession();
		String username = (String) ses.getAttribute("usuario");
		
		if(!username.isEmpty()){
			int tipoUsuario = (Integer) ses.getAttribute("tipo_usuario");
			if(tipoUsuario == 1){
	%>
				<script type="text/javascript">
					document.location = "View/admin"
				</script>
	<%	
			}			
		}
	%>
	<div class="background-login">
		<h3 class="center-text text-white">
			Sistema de punto de venta
		</h3>
	</div>
</div>