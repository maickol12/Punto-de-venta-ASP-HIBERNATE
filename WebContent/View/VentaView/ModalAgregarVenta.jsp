<div class="modal fade" id="Ventas" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel"><img width="40px" height="40px"  src="../../img/ventas.png" /> Agregar Venta</h4>
      </div>
      <div class="modal-body">
        <input name="operacion" value="agregar" type="hidden" />
          <div class="form-group">
            <label>Numero de ticket...</label>
           <input id="numeroticket" class="form-control" placeholder="Numero de ticket..." type="text"/>
          </div>
          <div class="form-group">
          	<label>Caja...</label>
         	<select id="idcaja" class="form-control">
         	
         	</select>
          </div>
          <div class="form-group">
	        <label>Vendedor...</label>
	        <select id="idvendedor" class="form-control">
         		
         	</select>
	      </div>
          <div class="form-group">
          	<label>Cliente...</label>
	        <select id="idcliente" class="form-control">
	       		
	        </select>
          </div>
         <div class="form-group">
          	<label>Subtotal...</label>
	         <input id=subtotal class="form-control" placeholder="Subtotal..." type="text"/>
         </div>
         <div class="form-group">
          	<label>Descuento...</label>
	         <input id="descuento" class="form-control" placeholder="Descuento..." type="text"/>
         </div> 
         <div class="form-group">
          	<label>Iva...</label>
	         <input id="iva" class="form-control" placeholder="iva..." type="text"/>
         </div> 
         <div class="form-group">
          	<label>Retenciones...</label>
	         <input id="retenciones" class="form-control" placeholder="retenciones..." type="text"/>
         </div> 
         <div class="form-group">
          	<label>Total...</label>
	         <input id="total" class="form-control" placeholder="Total..." type="text"/>
         </div> 
         <div class="form-group">
          	<label>Monto recibido...</label>
	         <input id="montorecibido" class="form-control" placeholder="Monto recibido..." type="text"/>
         </div> 
         <div class="form-group">
          	<label>Monto cambio...</label>
	         <input id="montocambio" class="form-control" placeholder="Monto cambio..." type="text"/>
         </div> 
         <div class="form-group">
          	<label>Moneda...</label>
	        <select id="moneda" class="form-control">
	        	<option value="MX">MX</option>
	        	<option value="US">US</option>
	        </select>
         </div> 
         <div class="form-group">
          	<label>Total cambio...</label>
	        <input id="tcambio" class="form-control" placeholder="tcambio..." type="text"/>
         </div> 
          <div class="form-group">
          	<label>Comentario...</label>
	      	<textarea id="comentario" class="form-control" placeholder="comentario..." ></textarea>
         </div> 
         
         
      </div>
           <center>
           		<button type="submit" class="btn btn-primary" onclick="return agregarVenta();")>
           			Guardar Venta
           		</button>
           </center>
           &nbsp;
      </div>
   
    </div>
  </div>
  <script type="text/javascript">
	//PETICIONES PARA LLENAR LOS SELECTS DEL MODAL
  $.ajax({
		url:'../../VentaController',
		type:'POST',
		data:{
			operacion:'makeSelectVendedor'
		},
		success:function(e){
			$("#idvendedor").append(e);
		}
	});
  $.ajax({
		url:'../../VentaController',
		type:'POST',
		data:{
			operacion:'makeSelectCajaDep'
		},
		success:function(e){
			$("#idcaja").append(e);
		}
	});
  $.ajax({
		url:'../../VentaController',
		type:'POST',
		data:{
			operacion:'makeSelectCliente'
		},
		success:function(e){
			$("#idcliente").append(e);
		}
	});
  </script>
  