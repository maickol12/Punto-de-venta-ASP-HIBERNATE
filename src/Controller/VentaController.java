package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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

import esqueletos.Usuario;
import esqueletos.caja;
import esqueletos.caja_dep;
import esqueletos.cliente;
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
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String operacion = mg.comprobarSession(request, response);
		
		switch (operacion) {
			case "makeSelectVendedor":
				makeSelectVendedor(response.getWriter());
			break;
			case "makeSelectCajaDep":
				makeSelectCajaDep(response.getWriter());
			break;
			case "makeSelectCliente":
				makeSelectCliente(response.getWriter());
			break;
			case "addVenta":
				addVenta(request,response);
			break;
			case "delete":
				deleteVenta(request,response);
			break;
		}
	}
	private void deleteVenta(HttpServletRequest request,HttpServletResponse response){
		venta ven = session.get(venta.class, Integer.parseInt(request.getParameter("id")));
		Transaction tx = null;
		try{
			tx = session.getTransaction();
			tx.begin();
			ven.setIs_active(0);
			getVentas(0, response.getWriter());
			response.setStatus(200);
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			response.setStatus(400);
			tx.rollback();
		}
	}
	
	private void getVentas(int start,PrintWriter out){
		List<venta> ventas = new ArrayList<>();
		
		ventas = session.createCriteria(venta.class).
				add(Restrictions.eq("is_active", new Integer(1))).
				setFirstResult(start).
				setMaxResults(9).
				list();
		
		
		out.print("<table style='padding:10px;' class='table table-hover table-inverse' id='tableSucursales'>");
		//VENDEDOR ES EL USUARIO
		out.print("<tr><td>numero ticket</td><td>caja</td><td>vendedor</td><td>cliente</td><td>subtotal</td><td>descuento</td><td>iva</td><td>o retenciones</td><td>total</td><td>monto recibido</td><td>monto cambio</td><td>moneda</td><td>t cambio</td><td>comentario</td><td>Editar</td><td>Eliminar</td></tr>");
		
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
		    	out.print("<li><a href='#' onclick='return getVentas("+i+")'>"+contador+"</a></li>");
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
				out.print(ven.getN_ticket());
			out.print("</td>");	
			out.print("<td id='caja"+ven.getIdventa()+"'>");
				out.print(ven.getCaja_d().getCaj().getCodigo_caja());
			out.print("</td>");
			out.print("<td id='vendedor"+ven.getIdventa()+"'>");
				out.print(ven.getUsu().getNombre());
			out.print("</td>");
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
	private void makeSelectVendedor(PrintWriter out){
		List<Usuario> usuarios = session.createCriteria(Usuario.class).
				add(Restrictions.eq("is_active", new Integer(1))).
				list();
		
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario user = usuarios.get(i);

			out.println("<option value='"+user.getIdusuario()+"'>"+user.getUsername()+"</option>");
		}
	}
	private void makeSelectCajaDep(PrintWriter out){
		List<caja_dep> cajas = session.createCriteria(caja_dep.class).
				add(Restrictions.eq("is_active", new Integer(1))).
				list();
		for (int i = 0; i < cajas.size(); i++) {
			caja_dep cp = cajas.get(i);
			System.out.println(i);
			out.println("<option value='"+cp.getIdcaja_dep()+"'>"+cp.getCaj().getCodigo_caja()+"</option>");
		}
	}
	private void makeSelectCliente(PrintWriter out){
		List<cliente> clientes = session.createCriteria(cliente.class).
				add(Restrictions.eq("is_active", new Integer(1))).
				list();
		for (int i = 0; i < clientes.size(); i++) {
			cliente cli = clientes.get(i);
			out.println("<option value='"+cli.getIdcliente()+"'>"+cli.getRazon_social()+"</option>");
		}
	}
	private void addVenta(HttpServletRequest request,HttpServletResponse response){
		String numeroticket = request.getParameter("numeroticket");
		int idcaja = Integer.parseInt(request.getParameter("idcaja"));
		int idvendedor = Integer.parseInt(request.getParameter("idvendedor"));
		int idcliente = Integer.parseInt(request.getParameter("idcliente"));
		double subtotal = Double.parseDouble(request.getParameter("subtotal"));
		double descuento = Double.parseDouble(request.getParameter("descuento"));
		double iva = Double.parseDouble(request.getParameter("iva"));
		double retenciones = Double.parseDouble(request.getParameter("retenciones"));
		double total = Double.parseDouble(request.getParameter("total"));
		double montorecibido = Double.parseDouble(request.getParameter("montorecibido"));
		double montocambio = Double.parseDouble(request.getParameter("montocambio"));
		String moneda = request.getParameter("moneda");
		double tcambio = Double.parseDouble(request.getParameter("tcambio"));
		String comentario = request.getParameter("comentario");
		
		caja_dep caj = session.get(caja_dep.class, idcaja);
		Usuario usu = session.get(Usuario.class, idvendedor);
		cliente cli = session.get(cliente.class,idcliente);
		
		venta ven = new venta();
		
		ven.setN_ticket(numeroticket);
		ven.setCaja_d(caj);
		ven.setUsu(usu);
		ven.setCli(cli);
		ven.setSubtotal(subtotal);
		ven.setDescuento(descuento);
		ven.setIva(iva);
		ven.setO_retenciones(retenciones);
		ven.setTotal(total);
		ven.setMonto_recibido(montorecibido);
		ven.setMonto_cambio(montocambio);
		ven.setMoneda(moneda);
		ven.setT_cambio(tcambio);
		ven.setComentario(comentario);
		ven.setF_alta(new Date());
		ven.setF_baja(new Date());
		ven.setIs_active(1);
		Transaction tx = null;
		
		try{
			tx = session.getTransaction();
			tx.begin();
			session.save(ven);
			getVentas(0, response.getWriter());
			tx.commit();
		
		}catch(Exception e){
			tx.rollback();
			System.out.println(e.getMessage());
		}
		
	}
	private Integer getCountVentas(){
		return ((Long)session.createCriteria(venta.class).setProjection(Projections.rowCount()).add(Restrictions.eq("is_active", new Integer(1))).uniqueResult()).intValue();
	}
}
