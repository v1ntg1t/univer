package students.logic;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.Collator;
import java.text.DateFormat;

import java.util.Date;
import java.util.Locale;


public class Student implements Comparable<Student> {

	private int id;
	private String name;
	private String patronymic;
	private String surname;
	private char sex;
	private Date birthDay;
	private int groupId;
	private int educationYear;


	public Student() {}
	
	public Student(ResultSet rs) throws SQLException {
		setId(rs.getInt(1));
		setName(rs.getString(2));
		setPatronymic(rs.getString(3));
		setSurname(rs.getString(4));
		setSex(rs.getString(5).charAt(0));
		setBirthDay(rs.getDate(6));
		setGroupId(rs.getInt(7));
		setEducationYear(rs.getInt(8));
	}

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getPatronymic() {
		return patronymic;
	}
	
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public char getSex() {
		return sex;
	}
	
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	public Date getBirthDay() {
		return birthDay;
	}
	
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public int getEducationYear() {
		return educationYear;
	}
	
	public void setEducationYear(int year) {
		educationYear = year;
	}

	
	public String toString() {
		return surname + " " + name + " " + patronymic + ", "
				+ DateFormat.getDateInstance(DateFormat.SHORT).format(birthDay)
				+ ", Группа ИД = " + groupId + " Год: " + educationYear;
	}
	
	public int compareTo(Student student) {
		Collator c = Collator.getInstance(new Locale("ru"));
		c.setStrength(Collator.PRIMARY);
		return c.compare(this.toString(), student.toString());
	}
	
}
