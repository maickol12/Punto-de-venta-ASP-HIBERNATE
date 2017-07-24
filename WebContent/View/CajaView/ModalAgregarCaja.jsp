<div class="modal fade" id="Cajas" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel"><img width="40px" height="40px"  src="../../img/clients.png" /> Agregar Cliente</h4>
      </div>
      <div class="modal-body">
        <input name="operacion" value="agregar" type="hidden" />
          <div class="form-group">
            <label>Codigo caja...</label>
           <input id="codigocaja" class="form-control" placeholder="Codigo caja..." type="text"/>
          </div>
          <div class="form-group">
          	<label>Activa/No activa...</label>
         	<select id="activa" class="form-control">
         		<option value="activa">Activa</option>
         		<option value="noactiva">No activa</option>
         	</select>
          </div>
    	&nbsp;
          
      </div>
           <center>
           		<button type="submit" class="btn btn-primary" onclick="return agregarCaja();")>
           			Guardar Ciente
           		</button>
           </center>
           &nbsp;
      </div>
   
    </div>
  </div>
  
  