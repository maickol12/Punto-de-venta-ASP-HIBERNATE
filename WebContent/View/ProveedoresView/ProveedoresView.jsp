<div class="col-md-10 col-md-offset-1">
	<div id="espera" class="">
		
	</div>
</div>



<script type="text/javascript" src="../../js/jquery.js"></script>
<script>
	getProveedores(0);
	
	
	
	function getProveedores(start){
		$.ajax({
			url:'../../ProveedoresController',
			type:'GET',
			data:{
				'operacion':'getProveedores',
				'start':start
			},
			beforeSend:function(e){
				$("#espera").html("<center><img width='200px' height='200px' src='../../img/cargando.gif' /></center>");
			},
			success:function(e){
				$("#espera").html(e);
			}
		})
	}
	function agregarProveedor(){
		data = {
				'operacion':'addProveedor',
				'razonsocial':$("#razonsocial").val(),
				'rfc':$("#rfc").val(),
				'calle':$("#calle").val(),
				'numero':$("#numero").val(),
				'ciudad':$("#ciudad").val(),
				'numero':$("#numero").val(),
				'cp':$("#cp").val(),
				'municipio':$("#municipio").val(),
				'estado':$("#estado").val(),
				'pais':$("#pais").val()
			}
		$.ajax({
			url:'../../ProveedoresController',
			type:'POST',
			data:data,
			beforeSend:function(e){
				$('#Proveedores').modal('toggle');
				$("#espera").html("<center><img width='200px' height='200px' src='../../img/cargando.gif' /></center>");
			},
			success:function(e){
				$("#espera").html(e);
			}
		});
	}
	function editar(id){
		$("#razonsocial"+id).html('<input class="form-control" id="razonsociali'+id+'" size="6" type="text" placeholder="Razon social..." value="'+$("#razonsocial"+id).text()+'" />');
		$("#rfc"+id).html('<input class="form-control" id="rfci'+id+'" size="6" type="text" placeholder="Rfc..." value="'+$("#rfc"+id).text()+'" />');
		$("#calle"+id).html('<input class="form-control" id="callei'+id+'" size="6" type="text" placeholder="Calle..." value="'+$("#calle"+id).text()+'" />');
		$("#numero"+id).html('<input class="form-control" id="numeroi'+id+'" size="6" type="text" placeholder="Numero..." value="'+$("#numero"+id).text()+'" />');
		$("#cp"+id).html('<input class="form-control" id="cpi'+id+'" size="6" type="text" placeholder="Codigo postal..." value="'+$("#cp"+id).text()+'" />');
		$("#ciudad"+id).html('<input class="form-control" id="ciudadi'+id+'" size="6" type="text" placeholder="Ciudad..." value="'+$("#ciudad"+id).text()+'" />');
		$("#municipio"+id).html('<input class="form-control" id="municipioi'+id+'" size="6" type="text" placeholder="Municipio..." value="'+$("#municipio"+id).text()+'" />');
		$("#estado"+id).html('<input class="form-control" size="6" id="estadoi'+id+'" type="text" placeholder="Estado..." value="'+$("#estado"+id).text()+'" />');
		$("#pais"+id).html('<input class="form-control" size="6" id="paisi'+id+'" type="text" placeholder="Pais..." value="'+$("#pais"+id).text()+'" />');
		$("#editar"+id).html('<button class="btn btn-primary" onclick="return editar_btn('+id+');">Editar</button>');
	}
	function editar_btn(id){
		data = {
				'idproveedor':id,
				'operacion':'editarProveedor',
				'razonsocial':$("#razonsociali"+id).val(),
				'rfc':$("#rfci"+id).val(),
				'calle':$("#callei"+id).val(),
				'numero':$("#numeroi"+id).val(),
				'cp':$("#cpi"+id).val(),
				'ciudad':$("#ciudadi"+id).val(),
				'municipio':$("#municipioi"+id).val(),
				'estado':$("#estadoi"+id).val(),
				'pais':$("#paisi"+id).val(),
				'correo':$("#correoi"+id).val(),
				'telefono':$("#telefonoi"+id).val()
			}
		$.ajax({
			url:'../../ProveedoresController',
			type:'POST',
			data:data,
			beforeSend:function(e){
				$("#espera").html("<center><img width='200px' height='200px' src='../../img/cargando.gif' /></center>");
			},
			success:function(e){
				$("#espera").html(e);
			}
		});
	}
	$("#buscarProveedores").keyup(function(e){
		$.ajax({
			url:'../../ProveedoresController',
			type:'GET',
			data:{
				'operacion':'findProveedor',
				'data':$("#buscarProveedores").val()
			},
			beforeSend:function(e){
				$("#espera").html("<center><img width='200px' height='200px' src='../../img/cargando.gif' /></center>");
			},
			success:function(e){
				$("#espera").html(e);
			}
			
		});
	});
	function eliminar(id){
		if(confirm("Estas seguro que deseas realizar la eliminacion?")){
			$.ajax({
				url:'../../ProveedoresController',
				type:'POST',
				data:{
					'id':id,
					'operacion':'deleteProveedor'
				},
				beforeSend:function(e){
					$("#espera").html("<center><img width='200px' height='200px' src='../../img/cargando.gif' /></center>");
				},
				success:function(e){
					$("#espera").html(e);
				}
			});
		}
	}
</script>

<jsp:include page="ModalAgregarProveedor.jsp"/>