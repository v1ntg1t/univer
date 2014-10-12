package students.frame;


import java.text.DateFormat;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;


import students.logic.Student;


public class StudentsTableModel extends AbstractTableModel {

	private static final String[] COLUMNS = {
			"Фамилия", "Имя", "Отчество", "Дата"
	};


	private Vector<Student> students;
	
	
	public StudentsTableModel(Vector<Student> students) {
		this.students = students;
	}
	
	
	public int getRowCount() {
		if(students != null) {
			return students.size();
		}
		return 0;
	}
	
	public int getColumnCount() {
		return 4;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(students != null) {
			Student student = students.get(rowIndex);
			switch(columnIndex) {
				case 0:
					return student.getSurname();
				case 1:
					return student.getName();
				case 2:
					return student.getPatronymic();
				case 3:
					DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
					return f.format(student.getBirthDay());
			}
		}
		return null;
	}
	

	public String getColumnName(int columnIndex) {
		return COLUMNS[columnIndex];
	}
	
	public Student getStudent(int rowIndex) {
		if(students != null) {
			if(rowIndex < students.size() && rowIndex >= 0) {
				return students.get(rowIndex);
			}
		}
		return null;
	}

}