package students.logic;


import java.text.Collator;
import java.text.DateFormat;

import java.util.Date;
import java.util.Locale;


public class Student implements Comparable<Student> {
	private int id;
	private String name;
	private String surname;
	private String patronymic;
	private Date birthDate;
	private char sex;
	private int groupId;
	private int educationYear;
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public int getEducationYear() {
		return educationYear;
	}
	
	public void setEducationYear(int educationYear) {
		this.educationYear = educationYear;
	}
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
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
	
	public String toString() {
		return surname + " " + name + " " + patronymic + ", "
				+ DateFormat.getDateInstance(DateFormat.SHORT).format(birthDate)
				+ ", Группа ИД=" + groupId + " Год:" + educationYear;
	}
	
	public int compareTo(Student student) {
		Collator c = Collator.getInstance(new Locale("ru"));
		c.setStrength(Collator.PRIMARY);
		return c.compare(this.toString(), student.toString());
	}
}