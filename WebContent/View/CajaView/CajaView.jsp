<div class="col-md-10 col-md-offset-1">
	<div id="espera" class="">
		
	</div>
</table>
</div>

<script type="text/javascript" src="../../js/jquery.js"></script>
<script>
	getAllCajas(0);
	
	
	function getAllCajas(start){
		$.ajax({
			url:'../../CajaController',
			data:{
				'start':start,
				'operacion':'getCajas'
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