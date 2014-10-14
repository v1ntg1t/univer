package students.web;


import java.io.IOException;
import java.io.PrintWriter;

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
			getServletContext().getRequestDispatcher("/testjdbc.jsp").forward(
					request, response);
/*			
			for(Group group : groups) {
				out.print("<tr>");
				out.print("<td>" + group.getId() + "</td>");
				out.print("<td>" + group.getName() + "</td>");
				out.print("<td>" + group.getCurator() + "</td>");
				out.print("<td>" + group.getSpeciality() + "</td>");
				out.print("</tr>");
			}
*/			
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

}