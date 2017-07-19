<div class="col-md-10 col-md-offset-1">
	<div id="espera">
		
	</div>
</table>
</div>

<script type="text/javascript" src="../../js/jquery.js"></script>
<script>
	//METODO QUE SE TRAE LAS SUCURSALES POR PRIMERA VEZ CUANDO SE CARGA LA PAGINA
	getSucursales(0);
	
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
	function getSucursales(start){
		$.ajax({
			url:'../../SucursalesController',
			type:'GET',
			data:{
				'operacion':'getSucursales',
				'start':start
			},
			beforeSend:function(e){
				$("#espera").html("<center><img width='200px' height='200px' src='img/cargando.gif' /></center>");
			},
			success:function(e){
				$("#espera").html("");
				$("#espera").append(e);
			}
		});
		return null;
	}
	function eliminar(id){
		$.ajax({
			url:'../../SucursalesController?idsucursal='+id+"&operacion=delete",
			type:'DELETE',
			beforeSend:function(e){
				$("#espera").html("<img src='img/cargando.gif' />");
			},
			success:function(e){
				$("#espera").html("");
				$("#espera").append(e);
			},
			error:function(e){
				$("#espera").html("");
				$("#espera").append(e);
			}
		});
		return null;
	}
	function editar(id){
		$.ajax({
			url:'../../SucursalesController',
			type:'PUT'
		});
		return null;
	}
</script>


<!-- Modal para agregar sucursales que es llamado desde el menu prinicipal -->  
<jsp:include page="ModalAgregarSucursal.jsp"/>

