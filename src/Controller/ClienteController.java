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
		case "find":
			findCliente(request.getParameter("data"),response.getWriter());
		break;
		default:
			break;
		}
		
		
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String operacion = gn.comprobarSession(request, response);
		
		switch (operacion) {
		case "addCliente":
			insertarSucursal(request, response.getWriter());
		break;
		case "delete":
			deleteCliente(Integer.parseInt(request.getParameter("id")),response.getWriter());
		break;
		case "editarCliente":
			editarCliente(request, response);
		break;
		default:
			break;
		}
	}
	public void editarCliente(HttpServletRequest request,HttpServletResponse response){
		String razonsocial = request.getParameter("razonsocial");
		String rfc = request.getParameter("rfc");
		String calle = request.getParameter("calle");
		String numero = request.getParameter("numero");
		String cp = request.getParameter("cp");
		String ciudad = request.getParameter("ciudad");
		String municipio = request.getParameter("municipio");
		String estado = request.getParameter("estado");
		String pais = request.getParameter("pais");
		String correo = request.getParameter("correo");
		String telefono = request.getParameter("telefono");
		int id = Integer.parseInt(request.getParameter("idcliente"));
		
		
		Transaction tx = null;
		try{
			tx = session.getTransaction();
			tx.begin();
			
			cliente cli = session.get(cliente.class, id);
			
			
			cli.setRazon_social(razonsocial);
			cli.setRfc(rfc);
			cli.setCalle(calle);
			cli.setCiudad(ciudad);
			cli.setNumero(Integer.parseInt(numero));
			cli.setCp(cp);
			cli.setMunicipio(municipio);
			cli.setEstado(estado);
			cli.setPais(pais);
			cli.setCiudad(ciudad);
			cli.setEmail(correo);
			cli.setTelefono(telefono);
			session.update(cli);
			
			getClientes(response.getWriter(), 0);
			
			tx.commit();
			
		}catch(Exception e){
			tx.rollback();
			System.out.println("fdfjk "+e.getMessage());
		}
	}
	public void deleteCliente(int id,PrintWriter out){
		
		
		Transaction tx = null;
		
		try{
			
			tx = session.getTransaction();
			
			tx.begin();
			
			cliente cli = session.get(cliente.class, id);
			
			cli.setIs_active(0);
			
			session.update(cli);
			
			getClientes(out, 0);
			
			tx.commit();
			
		}catch(Exception e){
			tx.rollback();
			System.out.println(e.getMessage());
		}
	}
	
	private void insertarSucursal(HttpServletRequest request,PrintWriter out){
		
		cliente cli = new cliente();
		
		cli.setRazon_social(request.getParameter("razonsocial"));
		cli.setRfc(request.getParameter("rfc"));
		cli.setCalle(request.getParameter("calle"));
		cli.setNumero(Integer.parseInt(request.getParameter("numero")));
		cli.setCiudad(request.getParameter("ciudad"));
		cli.setNumero(Integer.parseInt(request.getParameter("numero")));
		cli.setCp(request.getParameter("cp"));
		cli.setMunicipio(request.getParameter("municipio"));
		cli.setEstado(request.getParameter("estado"));
		cli.setPais(request.getParameter("pais"));
		cli.setTelefono(request.getParameter("telefono"));
		cli.setEmail(request.getParameter("correo"));
		
		cli.setIs_active(1);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		cli.setF_alta(date);
		cli.setF_baja(date);
		
		Transaction tx = null;
		
		try{
			tx = session.getTransaction();
			tx.begin();
			session.save(cli);
			tx.commit();
			
			if(session==null){
				out.println("null");
			}else{
				getClientes(out, 0);
			}
		}catch(Exception e){
			tx.rollback();
			System.out.println(e);
		}
		
		
		
		
	}
	private void findCliente(String data,PrintWriter out){
		List<cliente> clientes = session.createCriteria(cliente.class).
				add(Restrictions.like("razon_social", "%"+data+"%")).
				setMaxResults(9).
				list();
		
		out.print("<table style='padding:10px;' class='table table-hover table-inverse' id='tableSucursales'>");
		out.print("<tr><td>Razon social</td><td>Rfc</td><td>Calle</td><td>Numero</td><td>Ciudad</td><td>Municipio</td><td>Estado</td><td>Pais</td><td>Codigo postal</td><td>Correo</td><td>Telefono</td><td>Eliminar</td><td>Editar</td></tr>");
		for(cliente cli:clientes){
			makeCol(out, cli);
		}
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
	private void getClientes(PrintWriter out,int start){
		List<cliente> clientes = session.createCriteria(cliente.class).
				add(Restrictions.eq("is_active", new Integer(1))).
				setFirstResult(start).
				setMaxResults(9).
				list();
		out.print("<table style='padding:10px;' class='table table-hover table-inverse' id='tableSucursales'>");
		out.print("<tr><td>Razon social</td><td>Rfc</td><td>Calle</td><td>Numero</td><td>Ciudad</td><td>Municipio</td><td>Estado</td><td>Pais</td><td>Codigo postal</td><td>Correo</td><td>Telefono</td><td>Eliminar</td><td>Editar</td></tr>");
		for(cliente cli:clientes){
			makeCol(out, cli);
		}
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
	//METODO PARA CONTAR TODOS LOS CLIENTES
	public Integer getCountClientes(){
		return ((Long)session.createCriteria(cliente.class).setProjection(Projections.rowCount()).add(Restrictions.eqOrIsNull("is_active", new Integer(1))).uniqueResult()).intValue();
	}
	

	
}
