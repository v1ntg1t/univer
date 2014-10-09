package students.logic;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ManagementSystem {

	private static Connection con;
	private static ManagementSystem instance;

	
	private ManagementSystem() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/students";
			con = DriverManager.getConnection(url, "root", "rootpassword");
		} catch(ClassNotFoundException e) {
			throw new Exception(e);
		} catch(SQLException e) {
			throw new Exception(e);		
		}
	}
	
	public static synchronized ManagementSystem getInstance() 
			throws Exception {
		if(instance == null) {
			instance = new ManagementSystem();
		}
		return instance;
	}

	
	public List<Group> getGroups() throws SQLException {
		List<Group> groups = new ArrayList<Group>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select group_id, groupName, curator, " + 
					" speciality from groups");
			while(rs.next()) {
				Group group = new Group();
				group.setId(rs.getInt(1));
				group.setName(rs.getString(2));
				group.setCurator(rs.getString(3));
				group.setSpeciality(rs.getString(4));
				groups.add(group);
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
		}
		return groups;
	}

	public Collection<Student> getAllStudents() throws SQLException {
		Collection<Student> students = new ArrayList<Student>();
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
	
	public Collection<Student> getStudentsFromGroup(Group group, int year)
			throws SQLException {
		Collection<Student> students = new ArrayList<Student>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(
					"select student_id, firstName, patronymic, surName, sex, " +
					"dateOfBirth, group_id, educationYear from students " + 
					"where group_id=? and educationYear=? " +
					"order by surName, firstName, patronymic"
			);
			st.setInt(1, group.getId());
			st.setInt(2, year);
			rs = st.executeQuery();
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
	
	public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, 
			int newYear) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"update students set group_id=?, educationYear=? " +
					"where group_id=? and educationYear=?"
			);
			ps.setInt(1, newGroup.getId());
			ps.setInt(2, newYear);
			ps.setInt(3, oldGroup.getId());
			ps.setInt(4, oldYear);
			ps.execute();
		} finally {
			if(ps != null) {
				ps.close();
			}
		}
	}
	
	public void removeStudentsFromGroup(Group group, int year) 
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
	
	public void insertStudent(Student student) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"insert into students (firstName, patronymic, surName, " +
					"sex, dateOfBirth, group_id, educationYear) values " +
					"(?, ?, ?, ?, ?, ?, ?)"
			);
			ps.setString( 1, student.getName() );
			ps.setString( 2, student.getPatronymic() );
			ps.setString( 3, student.getSurname() );
			ps.setString( 4, new String(new char[]{student.getSex()}) );
			ps.setDate(  5, new Date( student.getBirthDay().getTime() )  );
			ps.setInt( 6, student.getGroupId() );
			ps.setInt( 7, student.getEducationYear() );
			ps.execute();
		} finally {
			if(ps != null) {
				ps.close();
			}
		}
	}
	
	public void updateStudent(Student student) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"update students set " + 
					"firstName=?, patronymic=?, surName=?, sex=?, " +
					"dateOfBirth=?, group_id=?, educationYear=? " + 
					"where student_id=?"
			);
			ps.setString( 1, student.getName() );
			ps.setString( 2, student.getPatronymic() );
			ps.setString( 3, student.getSurname() );
			ps.setString( 4, new String(new char[]{student.getSex()}) );
			ps.setDate(  5, new Date( student.getBirthDay().getTime() )  );
			ps.setInt( 6, student.getGroupId() );
			ps.setInt( 7, student.getEducationYear() );
			ps.setInt( 8, student.getId() );
			ps.execute();
		} finally {
			if(ps != null) {
				ps.close();
			}
		}
	}
	
	public void deleteStudent(Student student) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"delete from student where students_id=?"
			);
			ps.setInt( 1, student.getId() );
			ps.execute();
		} finally {
			if(ps != null) {
				ps.close();
			}
		}
	}
	
}