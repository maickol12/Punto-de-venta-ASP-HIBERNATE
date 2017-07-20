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
				$("#espera").html(e);
			}
		});
		return null;
	}
	function eliminar(id){
		if(confirmacion("Esta seguro que desea realizar la eliminacion?")){
			$.ajax({
				url:'../../SucursalesController?idsucursal='+id+"&operacion=delete",
				type:'DELETE',
				beforeSend:function(e){
					$("#espera").html("<img src='img/cargando.gif' />");
				},
				success:function(e){
					$("#espera").html(e);
				},
				error:function(e){
					$("#espera").html(e);
				}
			});
		}
		
		return null;
	}
	function editar(id){
		$("#nombre"+id).html('<input class="form-control" id="nombrei'+id+'" size="6" type="text" placeholder="Nombre..." value="'+$("#nombre"+id).text()+'" />');
		$("#calle"+id).html('<input class="form-control" id="callei'+id+'" size="6" type="text" placeholder="Calle..." value="'+$("#calle"+id).text()+'" />');
		$("#numero"+id).html('<input class="form-control" id="numeroi'+id+'" size="6" type="text" placeholder="Numero..." value="'+$("#numero"+id).text()+'" />');
		$("#colonia"+id).html('<input class="form-control" id="coloniai'+id+'" size="6" type="text" placeholder="Colonia..." value="'+$("#colonia"+id).text()+'" />');
		$("#ciudad"+id).html('<input class="form-control" id="ciudadi'+id+'" size="6" type="text" placeholder="Ciudad..." value="'+$("#ciudad"+id).text()+'" />');
		$("#municipio"+id).html('<input class="form-control" id="municipioi'+id+'" size="6" type="text" placeholder="Municipio..." value="'+$("#municipio"+id).text()+'" />');
		$("#estado"+id).html('<input class="form-control" size="6" id="estadoi'+id+'" type="text" placeholder="Estado..." value="'+$("#estado"+id).text()+'" />');
		$("#pais"+id).html('<input class="form-control" size="6" id="paisi'+id+'" type="text" placeholder="Pais..." value="'+$("#pais"+id).text()+'" />');
		$("#cp"+id).html('<input class="form-control" size="6" id="cpi'+id+'" type="text" placeholder="Cp..." value="'+$("#cp"+id).text()+'" />');
		$("#editar"+id).html('<button class="btn btn-primary" onclick="return editar_btn('+id+');">Editar</button>');
		return null;
	}
	function editar_btn(id){
		id = ""+id;
		data = {
				idsucursal:id,
				'operacion':'editarSucursal',
				'nombre':$("#nombrei"+id).val(),
				'calle':$("#callei"+id).val(),
				'ciudad':$("#ciudadi"+id).val(),
				'numero':$("#numeroi"+id).val(),
				'codigo_postal':$("#cpi"+id).val(),
				'colonia':$("#coloniai"+id).val(),
				'municipio':$("#municipioi"+id).val(),
				'estado':$("#estadoi"+id).val(),
				'pais':$("#paisi"+id).val()
			}
		if(confirm('Esta seguro que desea relizar la edicion?')){
			console.log(data);
			
			$.post("../../SucursalesController",data,function(e){
				$("#espera").html(e);
			});
		}
		return null;
	}
	function confirmacion(messaje){
		if(confirm(messaje)){
			return true;	
		}else{
			return false;
		}
	}
</script>


<!-- Modal para agregar sucursales que es llamado desde el menu prinicipal -->  
<jsp:include page="ModalAgregarSucursal.jsp"/>

