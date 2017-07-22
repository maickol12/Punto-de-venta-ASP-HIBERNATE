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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import esqueletos.caja;
import esqueletos.cliente;
import generales.metodos_generales;
import hibernate.HibernateUtil;

@WebServlet("/CajaController")
public class CajaController extends HttpServlet{
	private SessionFactory factory;
	private Session session;
	private metodos_generales mg = new metodos_generales();
	public CajaController(){
		super();
		factory = HibernateUtil.getSessionFactory();
		session = factory.openSession();
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String operacion = mg.comprobarSession(request, response);
		
		switch (operacion) {
		case "operacion":
			int start = Integer.parseInt(request.getParameter("start"));
			getCajas(start,response.getWriter());
			break;

		default:
			break;
		}
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response){
	
	}
	private void getCajas(int start,PrintWriter out){
		List<caja> cajas = session.createCriteria(caja.class).
				add(Restrictions.eqOrIsNull("is_active", new Integer(1))).
				setFirstResult(start).
				setMaxResults(9).
				list();
		
		
		
		out.print("</table>");
		out.print("<nav aria-label='Page navigation'>");
		out.print("<ul class='pagination'>");
		int count = getCountClientes();
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
			public void makeCol(PrintWriter out,cliente cli){
				out.print("<tr id='"+cli.getIdcliente()+"'>");
				out.print("<td id='razonsocial"+cli.getIdcliente()+"'>");
					out.print(cli.getRazon_social());
				out.print("</td>");	
				out.print("<td id='rfc"+cli.getIdcliente()+"'>");
					out.print(cli.getRfc());
				out.print("</td>");
				out.print("<td id='calle"+cli.getIdcliente()+"'>");
					out.print(cli.getCalle());
				out.print("</td>");
				out.print("<td id='numero"+cli.getIdcliente()+"'>");
					out.print(cli.getNumero());
				out.print("</td>");
				out.print("<td id='ciudad"+cli.getIdcliente()+"'>");
					out.print(cli.getCiudad());
				out.print("</td>");
				out.print("<td id='municipio"+cli.getIdcliente()+"'>");
					out.print(cli.getMunicipio());
				out.print("</td>");
				out.print("<td id='estado"+cli.getIdcliente()+"'>");
					out.print(cli.getEstado());
				out.print("</td>");
				out.print("<td id='pais"+cli.getIdcliente()+"'>");
					out.print(cli.getPais());
				out.print("</td>");
				out.print("<td id='cp"+cli.getIdcliente()+"'>");
					out.print(cli.getCp());
				out.print("</td>");
				out.print("<td id='correo"+cli.getIdcliente()+"'>");
					out.print(cli.getEmail());
				out.print("</td>");
				out.print("<td id='telefono"+cli.getIdcliente()+"'>");
					out.print(cli.getTelefono());
				out.print("</td>");
				out.print("<td id='eliminar"+cli.getIdcliente()+"'>");
					out.print("<a href='#' onclick='return eliminar("+cli.getIdcliente()+")'><img width='30px' height='30px' src='../../img/delete.png'/></a>");
				out.print("</td>");
				out.print("<td id='editar"+cli.getIdcliente()+"'>");
					out.print("<a href='#' onclick='return editar("+cli.getIdcliente()+")'><img width='30px' height='30px' src='../../img/update.png'/></a>");
				out.print("</td>");
			out.print("</tr>");
		}
		public Integer getCountCajas(){
			return ((Long)session.createCriteria(caja.class).setProjection(Projections.rowCount()).add(Restrictions.eqOrIsNull("is_active",new Integer(1))).uniqueResult()).intValue();
		}

}
