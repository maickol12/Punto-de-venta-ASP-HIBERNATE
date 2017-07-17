package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import esqueletos.Usuario;
import esqueletos.tipo_usuario;
import hibernate.HibernateUtil;

@WebServlet("/UsuarioController")
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
		Usuario us = session.get(Usuario.class, 2);
		if(us==null){
			return;
		}
		tipo_usuario tu = us.getT_u();
		response.getWriter().append("GET "+tu.getDescripcion());
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("user");
		String password = request.getParameter("password");
		
		//List<Usuario> usuarios = session.createCriteria(Usuario.class).list();
		List<Usuario> usuario = session.createQuery("from Usuario WHERE username = '"+username+"' OR email='"+username+"' AND password = '"+password+"'").list();
		
		if(!usuario.isEmpty()){
			HttpSession ses = request.getSession();
			
			Usuario user = usuario.get(0);
			ses.setAttribute("tipo_usuario", user.getTipo_usuario());
			ses.setAttribute("id_usuario",user.getIdusuario());
			ses.setAttribute("username", user.getUsername());
			response.sendRedirect("View/admin");
		}else{
			response.sendRedirect("index.jsp?err=true");
			
		}
	}
}