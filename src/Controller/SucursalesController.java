package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
			getSucursales(response.getWriter());
		break;

		default:
			break;
		}
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
		
	}
	//METODO PARA TRAER TODAS LAS SUCURSALES
	public void getSucursales(PrintWriter out){
		List<sucursal> sucursales = session.createCriteria(sucursal.class).setFirstResult(0).setMaxResults(10).list();
		System.out.println(""+sucursales.size());
		for(sucursal suc:sucursales){
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
}
