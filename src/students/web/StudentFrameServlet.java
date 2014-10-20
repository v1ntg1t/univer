package students.web;
 

import java.io.IOException;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import students.logic.Group;
import students.logic.ManagementSystem;
import students.logic.Student;

import students.web.forms.MainFrameForm;
import students.web.forms.StudentFrameForm;
 

public class StudentFrameServlet extends HttpServlet {

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"dd.MM.yyyy");
 

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}


	public void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ManagementSystem ms = ManagementSystem.getInstance();
				
		String sId = req.getParameter("id");
		if (sId != null && req.getParameter("OK") != null) {
			try {
				if (Integer.parseInt(sId) > 0) {
					updateStudent(req);
				} else {
					insertStudent(req);
				}
			} catch(SQLException e) {
				e.printStackTrace();
				throw new IOException(e.getMessage());
			} catch(ParseException e) {
				throw new IOException(e.getMessage());
			}
		}

		String gs = req.getParameter("groupId");
		String ys = req.getParameter("educationYear");

		int groupId = -1;
		if (gs != null) {
			groupId = Integer.parseInt(gs);
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (ys != null) {
			year = Integer.parseInt(ys);
		}

		MainFrameForm form = new MainFrameForm();
		try {
			List<Group> groups = ms.getGroups();
			List<Student> students = ms.getStudents(groupId, year);
			form.setGroupId(groupId);
			form.setYear(year);
			form.setGroups(groups);
			form.setStudents(students);
		} catch(SQLException e) {
			throw new IOException(e.getMessage());
		}
		req.setAttribute("form", form);
		getServletContext().getRequestDispatcher(
				"/MainFrame.jsp").forward(req, resp);
	}

	private void updateStudent(HttpServletRequest req) 
			throws SQLException, ParseException {
		Student s = prepareStudent(req);
		ManagementSystem.getInstance().updateStudent(s);
	}

	private void insertStudent(HttpServletRequest req) 
			throws SQLException, ParseException {
		Student s = prepareStudent(req);
		ManagementSystem.getInstance().insertStudent(s);
	}

	private Student prepareStudent(HttpServletRequest req) 
			throws ParseException {
		Student s = new Student();
		s.setId(Integer.parseInt(req.getParameter("id")));
		s.setName(req.getParameter("name").trim());
		s.setSurname(req.getParameter("surname").trim());
		s.setPatronymic(req.getParameter("patronymic").trim());
		s.setBirthDay(sdf.parse(req.getParameter("birthDay").trim()));
		if(req.getParameter("sex").equals("0")) {
			s.setSex('М');
		} else {
			s.setSex('Ж');
		}
		s.setGroupId(Integer.parseInt(req.getParameter("groupId").trim()));
		s.setEducationYear(Integer.parseInt(req.getParameter("educationYear").trim()));
		return s;
	}

}