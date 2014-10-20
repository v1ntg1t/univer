package students.web.forms;
 

import java.text.SimpleDateFormat;
import java.util.List;
 

import students.logic.Group;
import students.logic.Student;
 

public class StudentFrameForm {

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"dd.MM.yyyy");
	 
	private int id;
	private String name;
	private String patronymic;
	private String surname;
	private String birthDay;
	private int sex;
	private int groupId;
	private int educationYear;
	private List<Group> groups;


	public StudentFrameForm() {}

	public StudentFrameForm(Student student) {
		this();
		id = student.getId();
		name = student.getName();
		patronymic = student.getPatronymic();
		surname = student.getSurname();
		birthDay = sdf.format(student.getBirthDay());
		if (student.getSex() == 'М') {
			sex = 0;
		} else {
			sex = 1;
		}
		groupId = student.getGroupId();
		educationYear = student.getEducationYear();
	}

	public StudentFrameForm(Student student, List<Group> groups) {
		this(student);
		setGroups(groups);
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

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
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

	public void setEducationYear(int educationYear) {
		this.educationYear = educationYear;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Group> getGroups() {
		return groups;
	}
}