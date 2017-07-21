<div class="col-md-10 col-md-offset-1">
	<div id="espera" class="">
		
	</div>
</table>
</div>



<script type="text/javascript" src="../../js/jquery.js"></script>
<script>
	
	getAllClients(0);
	
	function getAllClients(start){
		$.ajax({
			url:'../../ClienteController',
			type:'GET',
			data:{
				'operacion':'getClientes',
				'start':start
			},
			beforeSend:function(e){
				$("#espera").html("<center><img width='200px' height='200px' src='../../img/cargando.gif' /></center>");
			},
			success:function(e){
				$("#espera").html(e);
			}
		});
	}
	function agregarCliente(){
		data = {
			'operacion':'addCliente',
			'razonsocial':$("#razonsocial").val(),
			'rfc':$("#rfc").val(),
			'calle':$("#calle").val(),
			'numero':$("#numero").val(),
			'ciudad':$("#ciudad").val(),
			'numero':$("#numero").val(),
			'cp':$("#cp").val(),
			'municipio':$("#municipio").val(),
			'estado':$("#estado").val(),
			'pais':$("#pais").val(),
			'correo':$("#correo").val(),
			'telefono':$("#telefono").val()
		}
		console.log(data);
		$.ajax({
			url:'../../ClienteController',
			type:'POST',
			data:data,
			beforeSend:function(e){
				$('#Clientes').modal('toggle');
				$("#espera").html("<img src='../../img/cargando.gif' />");
				
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
		$("#correo"+id).html('<input class="form-control" size="6" id="correoi'+id+'" type="text" placeholder="Correo..." value="'+$("#correo"+id).text()+'" />');
		$("#telefono"+id).html('<input class="form-control" size="6" id="telefonoi'+id+'" type="text" placeholder="Telegono..." value="'+$("#telefono"+id).text()+'" />');
		$("#editar"+id).html('<button class="btn btn-primary" onclick="return editar_btn('+id+');">Editar</button>');
	}
	function eliminar(id){
		if(confirmacion("Esta seguro que desea realizar la eliminacion?")){
			$.ajax({
				url:'../../ClienteController',
				type:'POST',
				data:{
					'operacion':'delete',
					'id':id
				},
				beforeSend:function(e){
					$("#espera").html("<img src='../../img/cargando.gif' />");
				},
				success:function(e){
					$("#espera").html(e);
				}
			});
		}
			
	}
	function editar_btn(id){
			id = ""+id;
			data = {
					'idcliente':id,
					'operacion':'editarCliente',
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
			if(confirm('Esta seguro que desea relizar la edicion?')){
				console.log(data);
				
				$.ajax({
					url:'../../ClienteController',
					type:'POST',
					data:data,
					beforeSend:function(e){
						$("#espera").html("<img src='../../img/cargando.gif' />");
					},
					success:function(e){
						$("#espera").html(e);
					}
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
	
	$("#buscar<%= request.getParameter("opcion")%>").keyup(function(e){
		$.ajax({
			url:'../../ClienteController',
			type:'GET',
			data:{
				'operacion':'find',
				'data':$("#buscar<%= request.getParameter("opcion") %>").val()
			},
			beforeSend:function(e){
				$("#espera").html("<img src='../../img/cargando.gif' />");		
			},
			success:function(e){
				$("#espera").html(e);
			}
		});
	});
	
	function getClientes(start){
		$.ajax({
			url:'../../ClienteController',
			type:'GET',
			data:{
				'operacion':'getClientes',
				'start':start
			},
			beforeSend:function(e){
				$("#espera").html("<center><img width='200px' height='200px' src='../../img/cargando.gif' /></center>");
			},
			success:function(e){
				$("#espera").html(e);
			}
		});
	}
</script>
<!-- INCLUYENDO EL MODAL PARA AGREGAR CLIENTES -->
<jsp:include page="ModalAgregarCliente.jsp" />