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
		case "findProveedor":
			findProveedor(request,response.getWriter());
		break;
		default:
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String operacion = mg.comprobarSession(request, response);
		
		switch (operacion) {
		case "addProveedor":
			addProveedor(request,response.getWriter());
		break;
		case "editarProveedor":
			editProveedor(request,response);
		break;
		case "deleteProveedor":
			deleteProovedor(request,response.getWriter());
		break;
		default:
			break;
		}
	}
	private void findProveedor(HttpServletRequest request,PrintWriter out){
		List<proveedor> proveedores = session.createCriteria(proveedor.class).
				add(Restrictions.eq("is_active", new Integer(1))).
				add(Restrictions.like("razon_social", "%"+request.getParameter("data")+"%")).
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
	private void deleteProovedor(HttpServletRequest request,PrintWriter out){
		int id = Integer.parseInt(request.getParameter("id"));
		
		Transaction tx = null;
		
		try{
			tx = session.getTransaction();
			tx.begin();
			proveedor pro = session.get(proveedor.class, id);
			pro.setIs_active(0);
			session.update(pro);
			getProveedores(out, 0);
			tx.commit();
			
			
		}catch(Exception e){
			tx.rollback();
		}
	}
	private void editProveedor(HttpServletRequest request,HttpServletResponse response){
		String razonsocial = request.getParameter("razonsocial");
		String rfc = request.getParameter("rfc");
		String calle = request.getParameter("calle");
		String numero = request.getParameter("numero");
		String cp = request.getParameter("cp");
		String ciudad = request.getParameter("ciudad");
		String municipio = request.getParameter("municipio");
		String estado = request.getParameter("estado");
		String pais = request.getParameter("pais");
		int id = Integer.parseInt(request.getParameter("idproveedor"));
		proveedor pro;
		Transaction tx = null;
		try{
			tx = session.getTransaction();
			tx.begin();
			pro = session.get(proveedor.class, id);
			pro.setRazon_social(razonsocial);
			pro.setRfc(rfc);
			pro.setCalle(calle);
			pro.setNumero(numero);
			pro.setCp(cp);
			pro.setCiudad(ciudad);
			pro.setMunicipio(municipio);
			pro.setEstado(estado);
			pro.setPais(pais);
			
			session.update(pro);
			getProveedores(response.getWriter(), 0);
			tx.commit();
			
		}catch(Exception e){
			tx.rollback();
		}
	}
	private void addProveedor(HttpServletRequest request,PrintWriter out){
		
		proveedor pro = new proveedor();
		
		pro.setRazon_social(request.getParameter("razonsocial"));
		pro.setRfc(request.getParameter("rfc"));
		pro.setCalle(request.getParameter("calle"));
		pro.setCiudad(request.getParameter("ciudad"));
		pro.setCp(request.getParameter("cp"));
		pro.setMunicipio(request.getParameter("municipio"));
		pro.setEstado(request.getParameter("estado"));
		pro.setPais(request.getParameter("pais"));
	
		pro.setIs_active(1);
	

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		pro.setF_alta(date);
		pro.setF_baja(date);
		pro.setF_update(date);
		
		Transaction tx = null;
		
		try{
			tx = session.getTransaction();
			tx.begin();
			session.save(pro);
			getProveedores(out, 0);
			tx.commit();
			
		}catch(Exception e){
			tx.rollback();
			System.out.println(e.getMessage());
		}
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
