package students.logic;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;


public class ManagementSystem {

	private static final String STUDENTS_DS = "java:comp/env/jdbc/StudentsDS";

	private static final String SQL_GET_GROUPS =
			"SELECT group_id, groupName, curator, speciality FROM groups";
	private static final String SQL_GET_STUDENTS =
			"SELECT student_id, firstName, patronymic, surName, sex, " +
			"dateOfBirth, group_id, educationYear FROM students " + 
			"WHERE group_id=? AND educationYear=? " +
			"ORDER BY surName, firstName, patronymic";
	private static final String SQL_MOVE_STUDENTS = 
			"UPDATE students SET group_id=?, educationYear=? " +
			"WHERE group_id=? AND educationYear=?";
	private static final String SQL_GET_STUDENT =
			"SELECT student_id, firstName, patronymic, surName, sex, " +
			"dateOfBirth, group_id, educationYear FROM students " +
			"WHERE student_id=?";
	private static final String SQL_INSERT_STUDENT = 
			"INSERT INTO students (firstName, patronymic, surName, " +
			"sex, dateOfBirth, group_id, educationYear) VALUES " +
			"(?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_STUDENT =
			"UPDATE students SET " + 
			"firstName=?, patronymic=?, surName=?, sex=?, " +
			"dateOfBirth=?, group_id=?, educationYear=? " + 
			"WHERE student_id=?";
	private static final String SQL_REMOVE_STUDENT =
			"DELETE FROM students WHERE student_id=?";


	private static Connection con;
	private static ManagementSystem instance;
	private static DataSource dataSource;

	
	private ManagementSystem() {}

	public static synchronized ManagementSystem getInstance() {
		if (instance == null) {
			try {
				instance = new ManagementSystem();
				Context ctx = new InitialContext();
				instance.dataSource = (DataSource) ctx.lookup(STUDENTS_DS);
				con = dataSource.getConnection();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	
	public List<Group> getGroups() throws SQLException {
		List<Group> groups = new ArrayList<Group>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(SQL_GET_GROUPS);
		while(rs.next()) {
			Group group = new Group();
			group.setId(rs.getInt(1));
			group.setName(rs.getString(2));
			group.setCurator(rs.getString(3));
			group.setSpeciality(rs.getString(4));
			groups.add(group);
		}
		rs.close();
		st.close();
		return groups;
	}

	public List<Student> getAllStudents() throws SQLException {
		List<Student> students = new ArrayList<Student>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select student_id, firstName, patronymic, " + 
					" surName, sex, dateOfBirth, group_id, educationYear " + 
					" from students order by surName, firstName, patronymic");
			while(rs.next()) {
				Student student = new Student(rs);
				students.add(student);
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
		}
		return students;
	}
	
	public List<Student> getStudents(int groupId, int year)
			throws SQLException {
		List<Student> students = new ArrayList<Student>();
		PreparedStatement ps = con.prepareStatement(SQL_GET_STUDENTS);
		ps.setInt(1, groupId);
		ps.setInt(2, year);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Student student = new Student(rs);
			students.add(student);
		}
		rs.close();
		ps.close();
		return students;
	}

	public List<Student> getStudents(Group group, int year)
			throws SQLException {
		return getStudents(group.getId(), year);
	}
	
	public void moveStudents(Group oldGroup, int oldYear, Group newGroup, 
			int newYear) throws SQLException {
		PreparedStatement ps = con.prepareStatement(SQL_MOVE_STUDENTS);
		ps.setInt(1, newGroup.getId());
		ps.setInt(2, newYear);
		ps.setInt(3, oldGroup.getId());
		ps.setInt(4, oldYear);
		ps.execute();
		ps.close();
	}

	public void removeStudents(Group group, int year) 
			throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"delete from students where group_id=? and educationYear=?"
			);
			ps.setInt(1, group.getId());
			ps.setInt(2, year);
			ps.execute();
		} finally {
			if(ps != null) {
				ps.close();
			}
		}
	}
	
	public Student getStudent(int id) throws SQLException {
		Student student = null;
		PreparedStatement ps = con.prepareStatement(SQL_GET_STUDENT);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			student = new Student(rs);
		}
		rs.close();
		ps.close();
		return student;
	}

	public void insertStudent(Student student) throws SQLException {
		PreparedStatement ps = con.prepareStatement(SQL_INSERT_STUDENT);
		ps.setString( 1, student.getName() );
		ps.setString( 2, student.getPatronymic() );
		ps.setString( 3, student.getSurname() );
		ps.setString( 4, new String(new char[]{student.getSex()}) );
		ps.setDate(  5, new Date( student.getBirthDay().getTime() )  );
		ps.setInt( 6, student.getGroupId() );
		ps.setInt( 7, student.getEducationYear() );
		ps.execute();
		ps.close();
	}
	
	public void updateStudent(Student student) throws SQLException {
		PreparedStatement ps = con.prepareStatement(SQL_UPDATE_STUDENT);
		ps.setString( 1, student.getName() );
		ps.setString( 2, student.getPatronymic() );
		ps.setString( 3, student.getSurname() );
		ps.setString( 4, new String(new char[]{student.getSex()}) );
		ps.setDate(  5, new Date( student.getBirthDay().getTime() )  );
		ps.setInt( 6, student.getGroupId() );
		ps.setInt( 7, student.getEducationYear() );
		ps.setInt( 8, student.getId() );
		ps.execute();
		ps.close();
	}

	public void removeStudent(Student student) throws SQLException {
		PreparedStatement ps = con.prepareStatement(SQL_REMOVE_STUDENT);
		ps.setInt(1, student.getId());
		ps.execute();
		ps.close();
	}
	
}
/*
public class ManagementSystem {

	public Collection getAllStudents() throws SQLException {
		Collection students = new ArrayList();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(
				“SELECT student_id, firstName, patronymic, surName, “+
				“sex, dateOfBirth, group_id, educationYear FROM students ORDER BY surName, firstName, patronymic”);
		while(rs.next()) {
			Student st = new Student(rs);
			students.add(st);
		}
		rs.close();
		stmt.close();
		return students;
	}


	public void removeStudentsFromGroup(Group group, int year) throws SQLException	{
		PreparedStatement stmt = con.prepareStatement(
		“DELETE FROM students WHERE group_id=? AND educationYear=?”);
		stmt.setInt(1, group.getGroupId());
		stmt.setInt(2, year);
		stmt.execute();
	}
}
*/