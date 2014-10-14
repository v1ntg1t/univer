package students.web;


import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import students.logic.Group;
import students.logic.ManagementSystem;


public class TestJDBCServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			List<Group> groups = ManagementSystem.getInstance().getGroups();
			request.setAttribute("groups", groups);

			List<String> strings = new ArrayList<String>();
			for(int i = 0; i < 5; i++) {
				strings.add("string " + i);
			}
			request.setAttribute("strings", strings);

			getServletContext().getRequestDispatcher("/testjdbc.jsp").forward(
//			getServletContext().getRequestDispatcher("/testjdbcanswer").forward(
					request, response);

					
/*			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<table border=1>");
			for(Group group : groups) {
				out.print("<tr>");
				out.print("<td>" + group.getId() + "</td>");
				out.print("<td>" + group.getName() + "</td>");
				out.print("<td>" + group.getCurator() + "</td>");
				out.print("<td>" + group.getSpeciality() + "</td>");
				out.print("</tr>");
			}
			out.println("</table>");
*/			
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

}