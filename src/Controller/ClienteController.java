package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import esqueletos.cliente;
import esqueletos.sucursal;
import generales.metodos_generales;
import hibernate.HibernateUtil;

@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet{
	private metodos_generales gn = new metodos_generales();
	private SessionFactory factory;
	private Session session;
	
	public ClienteController(){
		super();
		factory = HibernateUtil.getSessionFactory();
		session = factory.openSession();
	}
	
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String operacion = gn.comprobarSession(request, response);
		
		switch (operacion) {
		case "getClientes":
			getClientes(response.getWriter(),Integer.parseInt(request.getParameter("start")));
		break;

		default:
			break;
		}
		
		
	}
	
	private void getClientes(PrintWriter out,int start){
		List<cliente> clientes = session.createCriteria(sucursal.class).
				add(Restrictions.eq("is_active", new Integer(1))).
				setFirstResult(start).
				setMaxResults(9).
				list();
		out.print("<table style='padding:10px;' class='table table-hover table-inverse' id='tableSucursales'>");
		out.print("<tr><td>Razon social</td><td>Rfc</td><td>Calle</td><td>Numero</td><td>Ciudad</td><td>Municipio</td><td>Estado</td><td>Pais</td><td>Codigo postal</td><td>Eliminar</td><td>Editar</td></tr>");
		for(cliente cli:clientes){
			makeCol(out, cli);
		}
		out.print("</table>");
		out.print("<nav aria-label='Page navigation'>");
		out.print("<ul class='pagination'>");
		int count = getCountClientes();
		int contador = 1;
		   for(int i = 0;i<count;i+=10){
		    	out.print("<li><a href='#' onclick='return getSucursales("+i+")'>"+contador+"</a></li>");
		    	contador++;
		    }
		out.print("</li>");
		out.print("</ul>");
		out.print("</nav>");
		out.println("</table>");
	}
	//METODO PARA FORMAR UNA COLUMNA DE UNA TABLA
		public void makeCol(PrintWriter out,cliente suc){
			out.print("<tr id='"+suc.getIdsucursal()+"'>");
			out.print("<td id='nombre"+suc.getIdsucursal()+"'>");
				out.print(suc.getNombre_sucursal());
			out.print("</td>");	
			out.print("<td id='calle"+suc.getIdsucursal()+"'>");
				out.print(suc.getCalle());
			out.print("</td>");
			out.print("<td id='numero"+suc.getIdsucursal()+"'>");
				out.print(suc.getNumero());
			out.print("</td>");
			out.print("<td id='colonia"+suc.getIdsucursal()+"'>");
				out.print(suc.getColonia());
			out.print("</td>");
			out.print("<td id='ciudad"+suc.getIdsucursal()+"'>");
				out.print(suc.getCiudad());
			out.print("</td>");
			out.print("<td id='municipio"+suc.getIdsucursal()+"'>");
				out.print(suc.getMunicipio());
			out.print("</td>");
			out.print("<td id='estado"+suc.getIdsucursal()+"'>");
				out.print(suc.getEstado());
			out.print("</td>");
			out.print("<td id='pais"+suc.getIdsucursal()+"'>");
				out.print(suc.getPais());
			out.print("</td>");
			out.print("<td id='cp"+suc.getIdsucursal()+"'>");
				out.print(suc.getCp());
			out.print("</td>");
			out.print("<td id='eliminar"+suc.getIdsucursal()+"'>");
				out.print("<a href='#' onclick='return eliminar("+suc.getIdsucursal()+")'><img width='30px' height='30px' src='../../img/delete.png'/></a>");
			out.print("</td>");
			out.print("<td id='editar"+suc.getIdsucursal()+"'>");
				out.print("<a href='#' onclick='return editar("+suc.getIdsucursal()+")'><img width='30px' height='30px' src='../../img/update.png'/></a>");
			out.print("</td>");
		out.print("</tr>");
	}
	//METODO PARA CONTAR TODOS LOS CLIENTES
	public Integer getCountClientes(){
		return ((Long)session.createCriteria(cliente.class).setProjection(Projections.rowCount()).add(Restrictions.eqOrIsNull("is_active", new Integer(1))).uniqueResult()).intValue();
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
		
	}

	
}
