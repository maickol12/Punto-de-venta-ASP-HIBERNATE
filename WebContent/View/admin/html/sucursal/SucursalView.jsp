<div class="col-md-10 col-md-offset-1">
	<div id="espera">
		
	</div>
</table>
</div>

<script type="text/javascript" src="../../js/jquery.js"></script>
<script>
	$.ajax({
		url:'../../SucursalesController',
		type:'GET',
		data:{
			'operacion':'getSucursales',
			'start':'0'
		},
		beforeSend:function(e){
			$("#espera").html("<img src='img/cargando.gif' />");
		},
		success:function(e){
			$("#espera").html("");
			$("#espera").append(e);
		}
	});
	function agregarSucursal(){
		data = {
			'operacion':'addSucursal',
			'nombre':$("#nombre").val(),
			'calle':$("#calle").val(),
			'ciudad':$("#ciudad").val(),
			'numero':$("#numero").val(),
			'codigo_postal':$("#cp").val(),
			'colonia':$("#colonia").val(),
			'municipio':$("#municipio").val(),
			'estado':$("#estado").val(),
			'pais':$("#pais").val()
		}
		$.ajax({
			url:'../../SucursalesController',
			type:'POST',
			data:data,
			beforeSend:function(e){
				$('#Sucursales').modal('toggle');
				$("#espera").html("<img src='img/cargando.gif' />");
				
			},
			success:function(e){
				if(e!="null"){
					$("#espera").html(e);
					$("#espera").html("");
					$("#espera").append(e);
				}else{
					alert(e);
					$("#espera").html(e);
				}
			},
			error:function(e){
				alert(e);
				$("#espera").html(e);
			}
			
		});
		return null;
	}
</script>


<!-- Modal para agregar sucursales que es llamado desde el menu prinicipal -->  
<jsp:include page="ModalAgregarSucursal.jsp"/>

