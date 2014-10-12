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
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<b>Список групп</b>");
		out.println("<table border=1>");
		try {
			List<Group> groups = ManagementSystem.getInstance().getGroups();
			for(Group group : groups) {
				out.print("<tr>");
				out.print("<td>" + group.getId() + "</td>");
				out.print("<td>" + group.getName() + "</td>");
				out.print("<td>" + group.getCurator() + "</td>");
				out.print("<td>" + group.getSpeciality() + "</td>");
				out.print("</tr>");
			}
		} catch(Exception e) {
			throw new ServletException(e);
		}
		out.println("</table>");
	}

}