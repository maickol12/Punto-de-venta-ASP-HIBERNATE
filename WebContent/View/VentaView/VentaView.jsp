<div class="col-md-10 col-md-offset-1">
	<div id="espera" class="">
		
	</div>
</table>
</div>

<script type="text/javascript" src="../../js/jquery.js"></script>
<script>
	getVentas(0);
	
	
	
	function getVentas(start){
		$.ajax({
			url:'../../VentaController',
			type:'GET',
			data:{
				'operacion':'getventas',
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
	
	function agregarVenta(){
		data = {
		 	'numeroticket':$("#numeroticket").val(),
		 	'idcaja':$("#idcaja").val(),
		 	'idvendedor':$("#idvendedor").val(),
		 	'idcliente':$("#idcliente").val(),
		 	'subtotal':$("#subtotal").val(),
		 	'descuento':$("#descuento").val(),
		 	'iva':$("#iva").val(),
		 	'retenciones':$("#retenciones").val(),
		 	'total':$("#total").val(),
		 	'montorecibido':$("#montorecibido").val(),
		 	'montocambio':$("#montocambio").val(),
		 	'moneda':$("#moneda").val(),
		 	'tcambio':$("#tcambio").val(),
		 	'comentario':$("#comentario").val(),
		 	'operacion':'addVenta'
		};
		
		$.ajax({
			url:'../../VentaController',
			type:'POST',
			data:data,
			beforeSend:function(e){
				$('#Ventas').modal('toggle');
				$("#espera").html("<center><img width='200px' height='200px' src='../../img/cargando.gif' /></center>");
			},
			success:function(e){
				$("#espera").html(e);
			}
		});
		return null;
	}
</script>
<jsp:include page="ModalAgregarVenta.jsp"></jsp:include>