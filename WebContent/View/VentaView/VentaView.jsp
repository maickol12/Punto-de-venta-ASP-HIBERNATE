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
</script>
<jsp:include page="ModalAgregarVenta.jsp"></jsp:include>