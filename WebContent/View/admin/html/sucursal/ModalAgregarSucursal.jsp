
<div class="modal fade" id="Sucursales" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel"><img width="40px" height="40px"  src="img/sucursal.png" /> Agregar Sucursal</h4>
      </div>
      <div class="modal-body">
        <input name="operacion" value="agregar" type="hidden" />
          <div class="form-group">
            <label>Nombre...</label>
           <input class="form-control" placeholder="nombre..." type="text" name="nombre"/>
          </div>
          <div class="form-group">
          	<label>Calle...</label>
          	<input name="calle" class="form-control" placeholder="calle..." type="text" />
          </div>
          <div class="form-group">
	        <div class="col-md-12">
	         	<div class="col-md-6">
	         		<label>numero...</label>
	          		<input name="numero" class="form-control" placeholder="numero..." type="number" />
	         	</div>
	          	<div class="col-md-6">
	          		<label>Codigo Postal...</label>
	          		<input type="text" name="cp" class="form-control" placeholder="codigo postal..." />
	          	</div>
          	</div>
          </div>
          &nbsp; &nbsp;
          <div class="form-group">
          	<label>colonia...</label>
          	<input type="text" name="colonia" class="form-control" placeholder="colonia..." />
          </div>
          <div class="form-group">
          	<label>ciudad...</label>
          	<input type="text" name="ciudad" class="form-control" placeholder="ciudad..." />
          </div>
          <div class="form-group">
	        <div class="col-md-12">
	         <div class="col-md-4">
	          	<label>municipio...</label>
	          	<input type="text" name="municipio" class="form-control" placeholder="municipio..." />
          	 </div>
	          <div class="col-md-4">
	          	<label>estado...</label>
	          	<input type="text" name="estado" class="form-control" placeholder="estado..." />
	          </div>
	          <div class="col-md-4">
	          	<label>pais...</label>
	          	<input type="text" name="pais" class="form-control" placeholder="pais..." />
	          </div>
          	</div>
          </div>
    	&nbsp;
          
      </div>
           <center><input type="submit" class="btn btn-primary" value="Guardar Sucursal"/></center>
           &nbsp;
      </div>
   
    </div>
  </div>
  