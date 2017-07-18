<%@page import="Controller.AdminController"%>
<div class="col-md-10 col-md-offset-1">
	<table class="table table-striped" id="tableSucursales">
	<tr>
		<td>Nombre</td>
		<td>Calle</td>
		<td>Numero</td>
		<td>Colonia</td>
		<td>Ciudad</td>
		<td>Municipio</td>
		<td>Estado</td>
		<td>Pais</td>
		<td>Codigo postal</td>
	</tr>
</table>
</div>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script>
	$.ajax({
		url:'../../SucursalesController',
		type:'GET',
		data:{
			'operacion':'getSucursales'
		},
		beforeSend:function(e){
			
		},
		success:function(e){
			$("#tableSucursales").append(e);
		}
	});
</script>