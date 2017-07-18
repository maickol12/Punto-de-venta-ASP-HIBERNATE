
<div class="modal fade" id="Sucursales" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel"><img width="40px" height="40px"  src="img/sucursal.png" /> Agregar Sucursal</h4>
      </div>
      <div class="modal-body">
        <form action="../CarrerasController" method="POST">
        <input name="operacion" value="agregar" type="hidden" />
          <div class="form-group">
            <label>Nombre...</label>
           <input class="form-control" placeholder="nombre..." type="text" name="nombre"/>
          </div>
          <div class="form-group">
          	<label>Creditos...</label>
          	<input class="form-control" placeholder="creditos..." type="number" name="creditos" />
          </div>
                    </div>
           <center><input type="submit" class="btn btn-primary" value="Guardar"/></center>
           &nbsp;
        </form>
      </div>
   
    </div>
  </div>
  