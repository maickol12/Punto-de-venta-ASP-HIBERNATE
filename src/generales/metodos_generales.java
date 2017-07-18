package generales;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class metodos_generales {
	public String comprobarSession(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//AQUI OBTENEMOS EL TIPO DE OPERACION QUE SE VA REALIZAR EN ESTE SERVLET
		String opereacion = request.getParameter("operacion");
		//AQUI OBTENEMOS EL NOMBRE DE USUARIO DE LA SESSION SI ES QUE EXISTE
		String username = (String) request.getSession().getAttribute("username");
				
	
		if(username == null){
			response.sendRedirect("index.jsp");
			return "null";
		}
		if(opereacion == null){
			response.sendRedirect("View/admin");
			return "null";
		}
		
		return opereacion;
	}
}
