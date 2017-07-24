package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import esqueletos.sucursal;
import esqueletos.venta;
import generales.metodos_generales;
import hibernate.HibernateUtil;

@WebServlet("/VentaController")
public class VentaController extends HttpServlet{
	private SessionFactory factory;
	private Session session;
	private metodos_generales mg = new metodos_generales();
	
	public VentaController(){
		super();
		factory = HibernateUtil.getSessionFactory();
		session = factory.openSession();
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String operacion = mg.comprobarSession(request, response);
		
		switch (operacion) {
		case "getventas":
			getVentas(Integer.parseInt(request.getParameter("start")),response.getWriter());
		break;

		default:
			break;
		}
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	private void getVentas(int start,PrintWriter out){
		List<venta> ventas = new ArrayList<>();
		
		ventas = session.createCriteria(venta.class).
				add(Restrictions.eq("is_active", new Integer(1))).
				setFirstResult(start).
				setMaxResults(9).
				list();
		
		
		out.print("<table style='padding:10px;' class='table table-hover table-inverse' id='tableSucursales'>");
		out.print("<tr><td>numero ticket</td><td>caja</td><td>vendedor</td><td>cliente</td><td>subtotal</td><td>descuento</td><td>iva</td><td>o retenciones</td><td>total</td><td>monto recibido</td><td>monto cambio</td><td>moneda</td><td>t cambio</td><td>comentario</td></tr>");
		for(venta ven:ventas){
			System.out.println("id cliente "+ven.getCli().getIdcliente());
			makeCol(out, ven);
		}
		out.print("</table>");
		out.print("<nav aria-label='Page navigation'>");
		out.print("<ul class='pagination'>");
		int count = getCountVentas();
		System.out.println(count);
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
		public void makeCol(PrintWriter out,venta ven){
			out.print("<tr id='"+ven.getIdventa()+"'>");
			out.print("<td id='numeroticket"+ven.getIdventa()+"'>");
				out.print(ven.getIdventa());
			out.print("</td>");	
			out.print("<td id='caja"+ven.getIdventa()+"'>");
				out.print(ven.getCaj().getCodigo_caja());
			out.print("</td>");
			//out.print("<td id='vendedor"+ven.getIdventa()+"'>");
				//out.print(ven.getVendedor().getNombre());
			//out.print("</td>");
			out.print("<td id='cliente"+ven.getIdventa()+"'>");
				out.print(ven.getCli().getRazon_social());
			out.print("</td>");
			out.print("<td id='subtotal"+ven.getIdventa()+"'>");
				out.print(ven.getSubtotal());
			out.print("</td>");
			out.print("<td id='descuento"+ven.getIdventa()+"'>");
				out.print(ven.getDescuento());
			out.print("</td>");
			out.print("<td id='iva"+ven.getIdventa()+"'>");
				out.print(ven.getIva());
			out.print("</td>");
			out.print("<td id='o_retenciones"+ven.getIdventa()+"'>");
				out.print(ven.getO_retenciones());
			out.print("</td>");
			out.print("<td id='total"+ven.getIdventa()+"'>");
				out.print(ven.getTotal());
			out.print("</td>");
				out.print("<td id='monto_recibido"+ven.getIdventa()+"'>");
			out.print(ven.getMonto_recibido());
			out.print("</td>");
				out.print("<td id='monto_cambio"+ven.getMonto_cambio()+"'>");
			out.print(ven.getTotal());
			out.print("</td>");
			out.print("<td id='moneda"+ven.getIdventa()+"'>");
				out.print(ven.getMoneda());
			out.print("</td>");
			out.print("<td id='t_cambio"+ven.getIdventa()+"'>");
				out.print(ven.getT_cambio());
			out.print("</td>");
			out.print("<td id='comentario"+ven.getIdventa()+"'>");
				out.print(ven.getComentario());
			out.print("</td>");
			
			out.print("<td id='eliminar"+ven.getIdventa()+"'>");
				out.print("<a href='#' onclick='return eliminar("+ven.getIdventa()+")'><img width='30px' height='30px' src='../../img/delete.png'/></a>");
			out.print("</td>");
			out.print("<td id='editar"+ven.getIdventa()+"'>");
				out.print("<a href='#' onclick='return editar("+ven.getIdventa()+")'><img width='30px' height='30px' src='../../img/update.png'/></a>");
			out.print("</td>");
		out.print("</tr>");
		}
	private Integer getCountVentas(){
		return ((Long)session.createCriteria(venta.class).setProjection(Projections.rowCount()).add(Restrictions.eq("is_active", new Integer(1))).uniqueResult()).intValue();
	}
}
