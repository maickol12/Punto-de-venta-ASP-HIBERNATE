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

		default:
			break;
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
		List<sucursal> sucursales = session.createCriteria(sucursal.class).setFirstResult(0).setMaxResults(10).list();
		out.print("<table class='table table-striped' id='tableSucursales'>");
		out.print("<tr><td>Nombre</td><td>Calle</td><td>Numero</td><td>Colonia</td><td>Ciudad</td><td>Municipio</td><td>Estado</td><td>Pais</td><td>Codigo postal</td></tr>");
		for(sucursal suc:sucursales){
			makeCol(out, suc);
		}
		out.print("</table>");
	}
	//METODO PARA FORMAR UNA COLUMNA DE UNA TABLA
	public void makeCol(PrintWriter out,sucursal suc){
		out.print("<tr>");
		out.print("<td>");
			out.print(suc.getNombre_sucursal());
		out.print("</td>");	
		out.print("<td>");
			out.print(suc.getCalle());
		out.print("</td>");
		out.print("<td>");
			out.print(suc.getNumero());
		out.print("</td>");
		out.print("<td>");
			out.print(suc.getColonia());
		out.print("</td>");
		out.print("<td>");
			out.print(suc.getCiudad());
		out.print("</td>");
		out.print("<td>");
			out.print(suc.getMunicipio());
		out.print("</td>");
		out.print("<td>");
			out.print(suc.getEstado());
		out.print("</td>");
		out.print("<td>");
			out.print(suc.getPais());
		out.print("</td>");
		out.print("<td>");
			out.print(suc.getCp());
		out.print("</td>");
	out.print("</tr>");
	}
}
