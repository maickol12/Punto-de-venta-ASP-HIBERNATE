<%@page import="Controller.AdminController"%>
<div class="col-md-10 col-md-offset-1">
	<table class="table table-striped">
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
	<tr>
	
	</tr>
</table>
</div>

<script>
	$.ajax({
		url:'../../adminController',
		type:'GET',
		beforeSend:function(e){
			
		},
		success:function(){
			
		}
	});
</script>