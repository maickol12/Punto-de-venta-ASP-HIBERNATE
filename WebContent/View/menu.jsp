<nav class="navbar navbar-default">
  <div class="container-fluid">
  
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.jsp">Inicio</a>
    </div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%= request.getParameter("opcion") %> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#" type="button" data-toggle="modal" data-target="#<%= request.getParameter("opcion") %>">Agregar <%= request.getParameter("opcion") %></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#"></a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="../../AdminController?operacion=logout">Salir  <i class="glyphicon glyphicon-log-out"></i></a></li>
      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group">
        	<%
        		String cad = "";
	    		try{
	    			cad = request.getParameter("opcion");
	    		}catch(Exception e){
	    			cad = "";
	    		}
	    		
        	%>
          <input type="text" id="buscar<%= cad %>" class="form-control" placeholder="Buscar <%= cad %>">
        </div>
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>