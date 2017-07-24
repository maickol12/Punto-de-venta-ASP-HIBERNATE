package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		System.out.println(operacion+" "+request.getParameter("id"));
		switch (operacion) {
			case "delete":
				System.out.println("delete "+request.getParameter("id") );
				deleteCaja(Integer.parseInt(request.getParameter("id")),response.getWriter());
			break;
			case "edit":
				editarCaja(request,response);
			break;
			case "addCaja":
				addCaja(request,response.getWriter());
			break;
		}
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
	String operacion = mg.comprobarSession(request, response);
		System.out.println(operacion+" "+request.getParameter("id"));
		switch (operacion) {
		case "getCajas":
			int start = Integer.parseInt(request.getParameter("start"));
			getCajas(start,response.getWriter());
		break;
		case "find":
			findCaja(request.getParameter("data"),response.getWriter());
		break;
		default:
			break;
		}
	}
	private void addCaja(HttpServletRequest request,PrintWriter out){
		String codigo_caja = request.getParameter("codigocaja");
		int open = Integer.parseInt(request.getParameter("isopen"));
		
		Transaction tx = null;
		
		try{
			tx = session.getTransaction();
			tx.begin();
			caja ca = new caja();
			ca.setCodigo_caja(codigo_caja);
			ca.setIs_open(open);
			ca.setIs_active(1);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ca.setF_alta(date);
			ca.setF_baja(date);
			ca.setF_update(date);
			
			session.save(ca);
			getCajas(0, out);
			tx.commit();
			
		}catch(Exception e){
			tx.rollback();
		}
		
	}
	public void editarCaja(HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("idcaja"));
		String codigo_caja = request.getParameter("codigocaja");
		int isopen = Integer.parseInt(request.getParameter("isopen"));
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			tx.begin();
			caja c = session.get(caja.class, id);
			c.setCodigo_caja(codigo_caja);
			c.setIs_open(isopen);
			c.setIs_active(1);
			session.update(c);
			getCajas(0, response.getWriter());
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}
	}
	private void findCaja(String data,PrintWriter out){
		List<caja> cajas = session.createCriteria(caja.class).
					 add(Restrictions.like("codigo_caja","%"+data+"%")).
					 setMaxResults(9).
					 list();
		
		out.print("<table style='padding:10px;' class='table table-hover table-inverse' id='tableCaja'>");
		out.print("<tr><td>Codigo caja</td><td>Abierta/Cerrada</td><td>Eliminar</td><td>Editar</td></tr>");
		for(caja cli:cajas){
			makeCol(out, cli);
		}
		
		
		out.print("</table>");
		out.print("<nav aria-label='Page navigation'>");
		out.print("<ul class='pagination'>");
		int count = getCountCajas();
		int contador = 1;
		   for(int i = 0;i<count;i+=10){
		    	out.print("<li><a href='#' onclick='return getCajas("+i+")'>"+contador+"</a></li>");
		    	contador++;
		    }
		out.print("</li>");
		out.print("</ul>");
		out.print("</nav>");
		out.println("</table>");
	}
	private void deleteCaja(int id,PrintWriter out){
		caja caj = session.get(caja.class, id);
		
		Transaction tx = null;
		try{
			tx = session.getTransaction();
			tx.begin();
			caj.setIs_active(0);
			session.update(caj);
			getCajas(0, out);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			
		}
	}
	private void getCajas(int start,PrintWriter out){
		List<caja> cajas = session.createCriteria(caja.class).
				add(Restrictions.eqOrIsNull("is_active", new Integer(1))).
				setFirstResult(start).
				setMaxResults(9).
				list();
		out.print("<table style='padding:10px;' class='table table-hover table-inverse' id='tableCaja'>");
		out.print("<tr><td>Codigo caja</td><td>Abierta/Cerrada</td><td>Eliminar</td><td>Editar</td></tr>");
		for(caja cli:cajas){
			makeCol(out, cli);
		}
		
		
		out.print("</table>");
		out.print("<nav aria-label='Page navigation'>");
		out.print("<ul class='pagination'>");
		int count = getCountCajas();
		int contador = 1;
		   for(int i = 0;i<count;i+=10){
		    	out.print("<li><a href='#' onclick='return getCajas("+i+")'>"+contador+"</a></li>");
		    	contador++;
		    }
		out.print("</li>");
		out.print("</ul>");
		out.print("</nav>");
		out.println("</table>");
		
	}
	
	
	//METODO PARA FORMAR UNA COLUMNA DE UNA TABLA
			public void makeCol(PrintWriter out,caja caj){
				String active = "";
				String mensaje = "No activa";
				if(caj.getIs_open() == 1){
					active = "success";
					mensaje = "Activa";
				}else{
					active = "danger";
				}
				out.print("<tr id='"+caj.getIdcaja()+"' class='"+active+"'>");
				out.print("<td id='codigocaja"+caj.getIdcaja()+"'>");
					out.print(caj.getCodigo_caja());
				out.print("</td>");	
				out.print("<td id='isopen"+caj.getIdcaja()+"' >");
					out.print(mensaje);
				out.print("</td>");	
				out.print("<td id='eliminar"+caj.getIdcaja()+"'>");
					out.print("<a href='#' onclick='return eliminar("+caj.getIdcaja()+")'><img width='30px' height='30px' src='../../img/delete.png'/></a>");
				out.print("</td>");
				out.print("<td id='editar"+caj.getIdcaja()+"'>");
					out.print("<a href='#' onclick='return editar("+caj.getIdcaja()+")'><img width='30px' height='30px' src='../../img/update.png'/></a>");
				out.print("</td>");
			out.print("</tr>");
		}
		public Integer getCountCajas(){
			return ((Long)session.createCriteria(caja.class).setProjection(Projections.rowCount()).add(Restrictions.eqOrIsNull("is_active",new Integer(1))).uniqueResult()).intValue();
		}
		
}
