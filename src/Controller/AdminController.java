package Controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String opereacion = request.getParameter("operacion");
		String username = (String) request.getSession().getAttribute("username");
		
		if(username == null){
			response.sendRedirect("index.jsp");
			return;
		}
		if(opereacion == null){
			response.sendRedirect("View/admin");
			return;
		}
		switch (opereacion) {
		case "logout":
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		break;
		}
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
		
	}
}
