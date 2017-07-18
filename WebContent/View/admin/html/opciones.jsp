
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<% 
	class cajas{
		private String nombre;
		private String img;
		private String descripcion;
		public cajas(String img,String nombre,String descripcion){
			this.img = img;
			this.nombre = nombre;
			this.descripcion = descripcion;
		}
		
	}

	List<cajas> caja = new ArrayList();
	caja.add(new cajas("sucursal","Sucursales","Mas detalles"));
	caja.add(new cajas("apartment","Departamentos","Mas detalles"));
	caja.add(new cajas("supplier","Proveedores","Mas detalles"));
	caja.add(new cajas("product","Productos","Mas detalles"));
	caja.add(new cajas("promotions","Promociones","Mas detalles"));
	caja.add(new cajas("box","Cajas","Mas detalles"));
	caja.add(new cajas("users","Usuarios","Mas detalles"));
	caja.add(new cajas("clients","Clientes","Mas detalles"));
	caja.add(new cajas("ventas","Ventas","Mas detalles"));
	caja.add(new cajas("reportes","Reportes","Mas detalles"));
	caja.add(new cajas("factura","Facturas","Mas detalles"));
%>

<div class="row margenes">
		<% 
			PrintWriter pw = response.getWriter();
			for(int i = 0; i<caja.size();i++){
			cajas c = caja.get(i);
			%>
		<div class="col-lg-3 col-md-4">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                       <img src="img/<%= c.img %>.png">
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <h3><%= c.nombre %></h3>
                                    </div>
                                </div>
                            </div>
                            <a href="index.jsp?opcion=<%= c.nombre %>">
                                <div class="panel-footer">
                                    <span class="pull-left"><%= c.descripcion %></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
          </div>
          <% } %>
</div>