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
			type:'GET',
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
	function eliminar(id){
		if(confirm("Estas seguro que desea realizar la eliminacion?")){
			$.ajax({
				url:'../../CajaController',
				type:'POST',
				data:{
					'id':id,
					'operacion':'delete'
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
	function editar(id){
		var activa = $("#isopen"+id).text();
		var select = "<select id='isopeni"+id+"' class='form-control'>";
		if(activa == "Activa"){
			select += "<option value='1'>Activa</option>"; 
			select += "<option value='0'>No activa</option>";
		}else{
			select += "<option value='0'>No activa</option>";
			select += "<option value='1'>Activa</option>"; 
		}
		select += "</select>";
		
		$("#codigocaja"+id).html('<input class="form-control" id="codigocajai'+id+'" size="6" type="text" placeholder="Codigo caja..." value="'+$("#codigocaja"+id).text()+'" />');
		$("#isopen"+id).html(select);
		$("#editar"+id).html('<button class="btn btn-primary" onclick="return editar_btn('+id+');">Editar</button>');
	}
	function confirmacion(messaje){
		if(confirm(messaje)){
			return true;	
		}else{
			return false;
		}
	}
	function editar_btn(id){
		data = {
			'idcaja':id,
			'codigocaja':$("#codigocajai"+id).val(),
			'isopen':$("#isopeni"+id).val(),
			'operacion':'edit'
		}
		if(confirm("Estas seguro que desea llevar acabo la edicion?")){
			$.ajax({
				url:'../../CajaController',
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
	}
	$("#buscarCajas").keyup(function(e){
		$.ajax({
			url:'../../CajaController',
			type:'GET',
			data:{
				'operacion':'find',
				'data':$("#buscarCajas").val()
			},
			beforeSend:function(e){
				$("#espera").html("<center><img width='200px' height='200px' src='../../img/cargando.gif' /></center>");
			},
			success:function(e){
				$("#espera").html(e);
			}
		});
	});
	function agregarCaja(){
		var codigo_caja = $("#codigocaja").val();
	
		var open = ($("#activa").val()=='activa')?1:0;
		if(confirm("Esta seguro que desea agregar la caja?")){

			$.ajax({
				url:'../../CajaController',
				type:'POST',
				data:{
					'operacion':'addCaja',
					'codigocaja':codigo_caja,
					'isopen':open
				},
				beforeSend:function(e){
					$("#Cajas").modal('toggle');
					$("#espera").html("<center><img width='200px' height='200px' src='../../img/cargando.gif' /></center>");
				},
				success:function(e){
					$("#espera").html(e);
				}
			});
		}
	}
</script>
<jsp:include page="ModalAgregarCaja.jsp"/>