package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import esqueletos.Usuario;
import hibernate.HibernateUtil;

@WebServlet("/LoginController")
public class UsuarioController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private SessionFactory factory = null;
	private Session session = null;
	
	public UsuarioController(){
		super();
		factory = HibernateUtil.getSessionFactory();
		session = factory.openSession();
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.getWriter().append("GET");
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("user");
		String password = request.getParameter("password");
		
		//List<Usuario> usuarios = session.createCriteria(Usuario.class).list();
		List<Usuario> usuario = session.createQuery("from Usuario WHERE username = '"+username+"' OR email='"+username+"' AND password = '"+password+"'").list();
		
		response.getWriter().append(" "+usuario.size());
	}
}
