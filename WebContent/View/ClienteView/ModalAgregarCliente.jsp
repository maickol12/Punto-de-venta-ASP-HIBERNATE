<div class="modal fade" id="Clientes" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel"><img width="40px" height="40px"  src="../../img/sucursal.png" /> Agregar Sucursal</h4>
      </div>
      <div class="modal-body">
        <input name="operacion" value="agregar" type="hidden" />
          <div class="form-group">
            <label>Razon social...</label>
           <input id="razonsocial" class="form-control" placeholder="Razon social..." type="text"/>
          </div>
          <div class="form-group">
          	<label>Rfc...</label>
          	<input id="rfc" class="form-control" placeholder="rfc" type="text" />
          </div>
           <div class="form-group">
          	<label>Calle...</label>
          	<input id="calle" name="calle" class="form-control" placeholder="calle..." type="text" />
          </div>
          <div class="form-group">
          	<label>Numero...</label>
          	<input id="numero" class="form-control" placeholder="numero..." type="text" />
          </div>
          <div class="form-group">
          	<label>Codigo Postal...</label>
	        <input id="cp" type="text" class="form-control" placeholder="codigo postal..." />
          </div>
 
          <div class="form-group">
          	<label>ciudad...</label>
          	<input id="ciudad" type="text" class="form-control" placeholder="ciudad..." />
          </div>
          <div class="form-group">
          	<label>municipio...</label>
	        <input id="municipio" type="text" class="form-control" placeholder="municipio..." />
          </div>
          <div class="form-group">
          	<label>estado...</label>
	        <input id="estado" type="text" class="form-control" placeholder="estado..." />
          </div>
          <div class="form-group">
         		<label>pais...</label>
	          	<input id="pais" type="text" class="form-control" placeholder="pais..." />
          </div>
         <div class="form-group">
          	<label>Codigo postal...</label>
	        <input id="cp" class="form-control" placeholder="codigo postal..." type="number" />
          </div>
           <div class="form-group">
          	<label>Correo electronico...</label>
	        <input id="correo" class="form-control" placeholder="correo electronico..." type="email" />
          </div>
          <div class="form-group">
          	<label>Telefono...</label>
	        <input id="telefono" class="form-control" placeholder="telefono..." type="number" />
          </div>
    	&nbsp;
          
      </div>
           <center>
           		<button type="submit" class="btn btn-primary" onclick="return agregarCliente();")>
           			Guardar Ciente
           		</button>
           </center>
           &nbsp;
      </div>
   
    </div>
  </div>
  
  