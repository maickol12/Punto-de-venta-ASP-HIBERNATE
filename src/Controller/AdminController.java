package Controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generales.metodos_generales;

@WebServlet("/AdminController")


public class AdminController extends HttpServlet{
	metodos_generales mg = new metodos_generales();
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String operacion = mg.comprobarSession(request, response);

		if(operacion.equals("null")){
			return;
		}
		
		switch (operacion) {
		case "logout":
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		break;
		}
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
		
	}
}
