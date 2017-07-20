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

import org.apache.lucene.analysis.Analyzer.ReuseStrategy;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import esqueletos.sucursal;
import generales.metodos_generales;
import hibernate.HibernateUtil;

@WebServlet("/SucursalesController")
public class SucursalesController extends HttpServlet{
	metodos_generales mg = new metodos_generales();
	private SessionFactory factory;
	private Session session;
	
	public SucursalesController(){
		super();
		factory = HibernateUtil.getSessionFactory();
		session = factory.openSession();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String opereacion = mg.comprobarSession(request, response);
		if(opereacion.equals("null")){
			return;
		}
		
		switch (opereacion) {
		case "getSucursales":
			getSucursales(response.getWriter(),Integer.parseInt(request.getParameter("start")));
		break;

		default:
			break;
		}
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String operacion = mg.comprobarSession(request, response);
		if(operacion.equals("null")){
			response.getWriter().print("null");
			return;
		}
		switch (operacion) {
		case "addSucursal":
			insertarSucursal(request,response.getWriter());
		break;
		case "editarSucursal":
			editarSucursal(request, response);
		break;

		default:
			break;
		}
	}
	
	public void editarSucursal(HttpServletRequest request,HttpServletResponse response){
		String nombre = request.getParameter("nombre");
		String calle = request.getParameter("calle");
		String ciudad = request.getParameter("ciudad");
		String numero = request.getParameter("numero");
		String cp = request.getParameter("codigo_postal");
		String colonia = request.getParameter("colonia");
		String municipio = request.getParameter("municipio");
		String estado = request.getParameter("estado");
		String pais = request.getParameter("pais");
		int id = Integer.parseInt(request.getParameter("idsucursal"));
		
		
		Transaction tx = null;
		try{
			tx = session.getTransaction();
			tx.begin();
			
			sucursal suc = session.get(sucursal.class, id);
			
			
			suc.setNombre_sucursal(nombre);
			suc.setCalle(calle);
			suc.setCiudad(ciudad);
			suc.setNumero(12);
			suc.setCp(cp);
			suc.setColonia(colonia);
			suc.setMunicipio(municipio);
			suc.setEstado(estado);
			suc.setPais(pais);
			
			session.update(suc);
			
			getSucursales(response.getWriter(), 0);
			
			tx.commit();
			
		}catch(Exception e){
			tx.rollback();
			System.out.println("fdfjk "+e.getMessage());
		}
	}
	
	protected void doDelete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		mg.comprobarSession(request, response);
		eliminarSucursal(request,response.getWriter());
	}
	public void eliminarSucursal(HttpServletRequest request,PrintWriter out){
		int id = Integer.parseInt(request.getParameter("idsucursal"));
		Transaction tx = null;
		try{
			tx = session.getTransaction();
			tx.begin();
			
			sucursal suc = (sucursal) session.get(sucursal.class, id);
			
			suc.setIs_active(0);
			
			session.update(suc);
			
			getSucursales(out, 0);
			
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			System.out.println(e);
		}
	}
	private void insertarSucursal(HttpServletRequest request,PrintWriter out){
		sucursal suc = new sucursal();
		suc.setNombre_sucursal(request.getParameter("nombre"));
		suc.setCalle(request.getParameter("calle"));
		suc.setNumero(Integer.parseInt(request.getParameter("numero")));
		suc.setCp(request.getParameter("codigo_postal"));
		suc.setCiudad(request.getParameter("ciudad"));
		suc.setColonia(request.getParameter("colonia"));
		suc.setMunicipio(request.getParameter("municipio"));
		suc.setEstado(request.getParameter("estado"));
		suc.setPais(request.getParameter("pais"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		suc.setF_alta(date);
		suc.setF_baja(date);
		
		Transaction tx = null;
		
		try{
			tx = session.getTransaction();
			tx.begin();
			session.save(suc);
			tx.commit();
			
			if(session==null){
				out.println("null");
			}else{
				getSucursales(out, 0);
			}
		}catch(Exception e){
			tx.rollback();
			System.out.println(e);
		}
		
		
		
		
	}
	//METODO PARA TRAER TODAS LAS SUCURSALES
	public void getSucursales(PrintWriter out,int start){
		List<sucursal> sucursales = session.createCriteria(sucursal.class).
				add(Restrictions.eq("is_active", new Integer(1))).
				setFirstResult(start).
				setMaxResults(9).
				list();
		out.print("<table class='table table-hover' id='tableSucursales'>");
		out.print("<tr><td>Nombre</td><td>Calle</td><td>Numero</td><td>Colonia</td><td>Ciudad</td><td>Municipio</td><td>Estado</td><td>Pais</td><td>Codigo postal</td><td>Eliminar</td><td>Editar</td></tr>");
		for(sucursal suc:sucursales){
			makeCol(out, suc);
		}
		out.print("</table>");
		out.print("<nav aria-label='Page navigation'>");
		out.print("<ul class='pagination'>");
		int count = getCountSucursales();
		int contador = 1;
		   for(int i = 0;i<count;i+=10){
		    	out.print("<li><a href='#' onclick='return getSucursales("+i+")'>"+contador+"</a></li>");
		    	contador++;
		    }
		out.print("</li>");
		out.print("</ul>");
		out.print("</nav>");
	}
	//METODO PARA FORMAR UNA COLUMNA DE UNA TABLA
	public void makeCol(PrintWriter out,sucursal suc){
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
			out.print("<a href='#' onclick='return eliminar("+suc.getIdsucursal()+")'><img width='30px' height='30px' src='img/delete.png'/></a>");
		out.print("</td>");
		out.print("<td id='editar"+suc.getIdsucursal()+"'>");
			out.print("<a href='#' onclick='return editar("+suc.getIdsucursal()+")'><img width='30px' height='30px' src='img/update.png'/></a>");
		out.print("</td>");
	out.print("</tr>");
	}
	//METODO PARA CONTAR TODOS LOS REGISTROS QUE HAY EN LA TABLA DE SUCURSALES
	public Integer getCountSucursales(){
		return  ((Long)session.createCriteria(sucursal.class).setProjection(Projections.rowCount()).add(Restrictions.eq("is_active",new Integer(1))).uniqueResult()).intValue();
	}
}
