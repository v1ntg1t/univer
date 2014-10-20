package students.web.forms;


import java.util.Collection;


import students.logic.Group;
import students.logic.Student;


public class MainFrameForm {
	private int year;
	private int groupId;
	private Collection<Group> groups;
	private Collection<Student> students;


	public MainFrameForm() {}
	

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}

	public Collection<Group> getGroups() {
		return groups;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}

	public Collection<Student> getStudents() {
		return students;
	}
}