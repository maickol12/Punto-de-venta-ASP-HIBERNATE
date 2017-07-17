<!-- Hacemos las importaciones de los archivos nesesarios -->
<%@page import="hibernate.HibernateUtil" %>
<%
	HibernateUtil.buildSessionFactory();
%>
<div class="col-md-5 col-md-offset-3 col-sm-6 col-sm-offset-3 col-lg-6 col-lg-offset-3 margen_login"d>
	<div class="background-login">
		<h3 class="center-text text-white">Punto de venta</h3>
	</div>
	<div class="formulario-login">
		<form>
			<div class="form-group">
				<label>Usuario/Correo</label>
				<input type="text" class="form-control" placeholder="Usuario/Correo...">
			</div>
			<div class="form-group">
				<label>Contraseña</label>
				<input type="passoword" class="form-control" placeholder="Contraseña..."/>
			</div>
			<center>
				<button class="btn btn-default cen">Iniciar Session <i class="glyphicon glyphicon-ok"></i></button>
			</center>
		</form>
	</div>
	<div class="background-login">
		<h3 class="center-text text-white">
			Sistema de punto de venta
		</h3>
	</div>
</div>