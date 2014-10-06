package students.logic;


import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;


public class ManagementSystem {

	private List<Group> groups;
	private Collection<Student> students;
	
	private static ManagementSystem instance;
	
	private ManagementSystem() {
		loadGroups();
		loadStudents();
	}
	
	public static synchronized ManagementSystem getInstance() {
		if(instance == null) {
			instance = new ManagementSystem();
		}
		return instance;
	}
/*	
	public static void main(String[] args) {
		try {
			System.setOut(new PrintStream("out.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		ManagementSystem ms = ManagementSystem.getInstance();
		printGroups(ms);
		printAllStudents(ms);
		printStudentsByGroups(ms);
		
		Student student = new Student();
		student.setId(5);
		student.setName("Игорь");
		student.setPatronymic("Владимирович");
		student.setSurname("Перебежкин");
		student.setSex('М');
		Calendar calendar = Calendar.getInstance();
		calendar.set(1991, 8, 31);
		student.setBirthDate(calendar.getTime());
		student.setGroupId(1);
		student.setEducationYear(2006);
		printString("Добавление студента: " + student);
		printString("-----------");
		ms.insertStudent(student);
		printAllStudents(ms);
		
		student = new Student();
		student.setId(5);
		student.setName("Игорь");
		student.setPatronymic("Владимирович");
		student.setSurname("Новоеребежкин");
		student.setSex('М');
		calendar = Calendar.getInstance();
		calendar.set(1991, 8, 31);
		student.setBirthDate(calendar.getTime());
		student.setGroupId(1);
		student.setEducationYear(2006);
		printString("Редактирование данных студента: " + student);
		printString("-----------");
		ms.updateStudent(student);
		printAllStudents(ms);
		
		printString("Удаление студента: " + student);
		printString("-----------");
		ms.deleteStudent(student);
		printAllStudents(ms);
		
		Group group1 = ms.getGroups().get(0);
		Group group2 = ms.getGroups().get(1);
		printString("Перевод студентов из 1-ой группы во 2-ю группу");
		printString("-----------");
		ms.moveStudentsToGroup(group1, 2006, group2, 2007);
		printAllStudents(ms);
		
		printString("Удаление студентов из группы: " + group2 + " в 2006 году");
		printString("-----------");
		ms.removeStudentsFromGroup(group2, 2006);
		printAllStudents(ms);
	}
*/	
	public void loadGroups() {
		if(groups == null) {
			groups = new ArrayList<Group>();
		} else {
			groups.clear();
		}
		Group group = null;
		
		group = new Group();
		group.setId(1);
		group.setName("Первая");
		group.setCurator("Доктор Борменталь");
		group.setSpeciality("Создание собачек из человеков");
		groups.add(group);
		
		group = new Group();
		group.setId(2);
		group.setName("Вторая");
		group.setCurator("Профессор Преображенский");
		group.setSpeciality("Создание человеков из собачек");
		groups.add(group);
		
	}
	
	public void loadStudents() {
		if(students == null) {
			students = new TreeSet<Student>();
		} else {
			students.clear();
		}
		
		Student student = null;
		Calendar calendar = Calendar.getInstance();
		
		student = new Student();
		student.setId(1);
		student.setName("Иван");
		student.setPatronymic("Сергеевич");
		student.setSurname("Степанов");
		student.setSex('М');
		calendar.set(1990, 3, 20);
		student.setBirthDate(calendar.getTime());
		student.setGroupId(2);
		student.setEducationYear(2006);
		students.add(student);
		
		student = new Student();
		student.setId(2);
		student.setName("Наталья");
		student.setPatronymic("Андреевна");
		student.setSurname("Чичикова");
		student.setSex('Ж');
		calendar.set(1990, 6, 10);
		student.setBirthDate(calendar.getTime());
		student.setGroupId(2);
		student.setEducationYear(2006);
		students.add(student);
		
		student = new Student();
		student.setId(3);
		student.setName("Петр");
		student.setPatronymic("Викторович");
		student.setSurname("Сушкин");
		student.setSex('М');
		calendar.set(1991, 3, 12);
		student.setBirthDate(calendar.getTime());
		student.setGroupId(1);
		student.setEducationYear(2006);
		students.add(student);

		student = new Student();
		student.setId(4);
		student.setName("Вероника");
		student.setPatronymic("Сергеевна");
		student.setSurname("Ковалева");
		student.setSex('Ж');
		calendar.set(1991, 7, 19);
		student.setBirthDate(calendar.getTime());
		student.setGroupId(1);
		student.setEducationYear(2006);
		students.add(student);
	}

	public static void printGroups(ManagementSystem ms) {
		printString("Полный список групп");
		printString("-----------");
		List<Group> allGroups = ms.getGroups();
		for(Group group : allGroups) {
			printString(group);
		}
		printString();
	}
	
	public static void printAllStudents(ManagementSystem ms) {
		printString("Полный список студентов");
		printString("-----------");
		Collection<Student> allStudents = ms.getAllStudents();
		for(Student student : allStudents) {
			printString(student);
		}
		printString();
	}
	
	public static void printStudentsByGroups(ManagementSystem ms) {
		printString("Список студентов по группам");
		printString("-----------");
		List<Group> groups = ms.getGroups();
		for(Group group : groups) {
			printString("---> Группа: " + group.getName());
			Collection<Student> groupStudents 
					= ms.getStudentsFromGroup(group, 2006);
			for(Student student : groupStudents) {
				printString(student);
			}
		}
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public Collection<Student> getAllStudents() {
		return students;
	}

	public Collection<Student> getStudentsFromGroup(Group group, int year) {
		Collection<Student> groupStudents = new TreeSet<Student>();
		for(Student student : this.students) {
			if(student.getGroupId() == group.getId() 
					&& student.getEducationYear() == year
			) {
				groupStudents.add(student);
			}
		}
		return groupStudents;
	}

	public void insertStudent(Student student) {
		students.add(student);
	}

	public void updateStudent(Student newStudent) {
		Student updatedStudent = null;
		for(Student student : students) {
			if(student.getId() == newStudent.getId()) {
				updatedStudent = student;
				break;
			}
		}
		updatedStudent.setName(newStudent.getName());
		updatedStudent.setPatronymic(newStudent.getPatronymic());
		updatedStudent.setSurname(newStudent.getSurname());
		updatedStudent.setSex(newStudent.getSex());
		updatedStudent.setBirthDate(newStudent.getBirthDate());
		updatedStudent.setGroupId(newStudent.getGroupId());
		updatedStudent.setEducationYear(newStudent.getEducationYear());
	}

	public void deleteStudent(Student student) {
		Student deletedStudent = null;
		for(Student currentStudent : students) {
			if(currentStudent.getId() == student.getId()) {
				deletedStudent = currentStudent;
				break;
			}
		}
		students.remove(deletedStudent);
	}

	public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup,
			int newYear) {
		for(Student student : students) {
			if(student.getGroupId() == oldGroup.getId()
					&& student.getEducationYear() == oldYear) {
				student.setGroupId(newGroup.getId());
				student.setEducationYear(newYear);
			}
		}
	}

	public void removeStudentsFromGroup(Group group, int year) {
		Collection<Student> tmpCollection = new TreeSet<Student>();
		for(Student student : students) {
			if(student.getGroupId() != group.getId() 
					|| student.getEducationYear() != year) {
				tmpCollection.add(student);
			}
		}
		students = tmpCollection;
	}
	
	public static void printString(Object object) {
		System.out.println(object);
	}
	
	public static void printString() {
		System.out.println();
	}
	
}
