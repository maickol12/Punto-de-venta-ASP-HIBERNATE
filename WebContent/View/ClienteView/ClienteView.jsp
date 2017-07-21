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

</script>
<!-- INCLUYENDO EL MODAL PARA AGREGAR CLIENTES -->
<jsp:include page="ModalAgregarCliente.jsp" />