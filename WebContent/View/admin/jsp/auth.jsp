<%
	HttpSession auth = request.getSession();
	

	String username = (String) auth.getAttribute("username");
	
	
	if(username == null){
		response.sendRedirect("../..");
		return;
	}
	
	
%>