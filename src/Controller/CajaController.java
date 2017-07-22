package Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
		
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response){
	
	}

}
