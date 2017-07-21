<div class="col-md-10 col-md-offset-1">
	<div id="espera" class="">
		
	</div>
</table>
</div>



<script type="text/javascript" src="../../js/jquery.js"></script>
<script>
	
	
	
	function getAllClients(start){
		$.ajax({
			url:'../../ClienteController',
			type:'POST',
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