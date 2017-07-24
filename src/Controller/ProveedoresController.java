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
import esqueletos.proveedor;
import generales.metodos_generales;
import hibernate.HibernateUtil;

@WebServlet("/ProveedoresController")
public class ProveedoresController extends HttpServlet{
	private SessionFactory factory;
	private Session session;
	private metodos_generales mg = new metodos_generales();
	
	public ProveedoresController(){
		super();
		factory = HibernateUtil.getSessionFactory();
		session = factory.openSession();
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String operacion = mg.comprobarSession(request, response);
		
		switch (operacion) {
		case "getProveedores":
			getProveedores(response.getWriter(),Integer.parseInt(request.getParameter("start")));
		break;

		default:
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	private void getProveedores(PrintWriter out,int start){
		List<proveedor> proveedores = session.createCriteria(proveedor.class).
				add(Restrictions.eq("is_active", new Integer(1))).
				setFirstResult(start).
				setMaxResults(9).
				list();
		
		out.print("<table style='padding:10px;' class='table table-hover table-inverse' id='tableSucursales'>");
		out.print("<tr><td>Razon social</td><td>Rfc</td><td>Calle</td><td>Numero</td><td>Ciudad</td><td>Municipio</td><td>Estado</td><td>Pais</td><td>Codigo postal</td><td>Eliminar</td><td>Editar</td></tr>");
		for(proveedor pro:proveedores){
			makeCol(out, pro);
		}
		out.print("</table>");
		out.print("<nav aria-label='Page navigation'>");
		out.print("<ul class='pagination'>");
		int count = getCountProveedores();
		int contador = 1;
		   for(int i = 0;i<count;i+=10){
		    	out.print("<li><a href='#' onclick='return getClientes("+i+")'>"+contador+"</a></li>");
		    	contador++;
		    }
		out.print("</li>");
		out.print("</ul>");
		out.print("</nav>");
		out.println("</table>");
	}
	
	//METODO PARA FORMAR UNA COLUMNA DE UNA TABLA
			public void makeCol(PrintWriter out,proveedor pro){
				out.print("<tr id='"+pro.getIdproveedor()+"'>");
				out.print("<td id='razonsocial"+pro.getIdproveedor()+"'>");
					out.print(pro.getRazon_social());
				out.print("</td>");	
				out.print("<td id='rfc"+pro.getIdproveedor()+"'>");
					out.print(pro.getRfc());
				out.print("</td>");
				out.print("<td id='calle"+pro.getIdproveedor()+"'>");
					out.print(pro.getCalle());
				out.print("</td>");
				out.print("<td id='numero"+pro.getIdproveedor()+"'>");
					out.print(pro.getNumero());
				out.print("</td>");
				out.print("<td id='ciudad"+pro.getIdproveedor()+"'>");
					out.print(pro.getCiudad());
				out.print("</td>");
				out.print("<td id='municipio"+pro.getIdproveedor()+"'>");
					out.print(pro.getMunicipio());
				out.print("</td>");
				out.print("<td id='estado"+pro.getIdproveedor()+"'>");
					out.print(pro.getEstado());
				out.print("</td>");
				out.print("<td id='pais"+pro.getIdproveedor()+"'>");
					out.print(pro.getPais());
				out.print("</td>");
				out.print("<td id='cp"+pro.getIdproveedor()+"'>");
					out.print(pro.getCp());
				out.print("</td>");
				out.print("<td id='eliminar"+pro.getIdproveedor()+"'>");
					out.print("<a href='#' onclick='return eliminar("+pro.getIdproveedor()+")'><img width='30px' height='30px' src='../../img/delete.png'/></a>");
				out.print("</td>");
				out.print("<td id='editar"+pro.getIdproveedor()+"'>");
					out.print("<a href='#' onclick='return editar("+pro.getIdproveedor()+")'><img width='30px' height='30px' src='../../img/update.png'/></a>");
				out.print("</td>");
			out.print("</tr>");
		}
		private Integer getCountProveedores(){
			return ((Long)session.createCriteria(proveedor.class).setProjection(Projections.rowCount()).add(Restrictions.eq("is_active", new Integer(1))).uniqueResult()).intValue();
		}
}
