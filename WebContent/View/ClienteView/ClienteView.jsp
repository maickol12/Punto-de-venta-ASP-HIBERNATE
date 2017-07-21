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

</script>
<!-- INCLUYENDO EL MODAL PARA AGREGAR CLIENTES -->
<jsp:include page="ModalAgregarCliente.jsp" />