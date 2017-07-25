<div class="modal fade" id="Ventas" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel"><img width="40px" height="40px"  src="../../img/sucursal.png" /> Agregar Sucursal</h4>
      </div>
      <div class="modal-body">
        <input name="operacion" value="agregar" type="hidden" />
          <div class="form-group">
            <label>Numero de ticket...</label>
           <input id="numeroticket" class="form-control" placeholder="Numero de ticket..." type="text"/>
          </div>
          <div class="form-group">
          	<label>Caja...</label>
         	<select id="idcaja">
         		<option value="1">caja 1</option>
         	</select>
          </div>
          <div class="form-group">
	        <label>Vendedor...</label>
	        <select id="idcaja">
         		<option value="1">Vendor x</option>
         	</select>
	      </div>
          <div class="form-group">
          	<label>Cliente...</label>
	        <select id="idcliente">
	       		<option value="1">cliente x</option>
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
      </div>
           <center>
           		<button type="submit" class="btn btn-primary" onclick="return agregarSucursal();")>
           			Guardar Sucursal
           		</button>
           </center>
           &nbsp;
      </div>
   
    </div>
  </div>
  
  