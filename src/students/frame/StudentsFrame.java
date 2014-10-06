package students.frame;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import javax.swing.border.BevelBorder;

import java.util.Vector;


import students.logic.Group;
import students.logic.ManagementSystem;
import students.logic.Student;


public class StudentsFrame extends JFrame {
	ManagementSystem ms = ManagementSystem.getInstance();
	private JList groupsList;
	private JList studentsList;
	private JSpinner spYear;
	
	public StudentsFrame() {
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getTopPanel(), BorderLayout.NORTH);
		getContentPane().add(getBottomPanel(ms), BorderLayout.CENTER);
		setBounds(100, 100, 600, 400);
	}
	
	private JPanel getTopPanel() {
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(new JLabel("Год обучения: "));
		SpinnerModel sm = new SpinnerNumberModel(2006, 1900, 2100, 1);
		spYear = new JSpinner(sm);
		top.add(spYear);
		return top;
	}
	
	private JPanel getBottomPanel(ManagementSystem ms) {
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		bottom.add(getLeftPanel(ms), BorderLayout.WEST);
		bottom.add(getRightPanel(ms), BorderLayout.CENTER);
		return bottom;
	}
	
	private JPanel getLeftPanel(ManagementSystem ms) {
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		left.setBorder(new BevelBorder(BevelBorder.RAISED));
		left.add(new JLabel("Группы: "), BorderLayout.NORTH);
		Vector<Group> groups = new Vector<Group>(ms.getGroups());
		groupsList = new JList(groups);
		left.add(new JScrollPane(groupsList), BorderLayout.CENTER);
		return left;
	}
	
	private JPanel getRightPanel(ManagementSystem ms) {
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		right.setBorder(new BevelBorder(BevelBorder.RAISED));
		right.add(new JLabel("Студенты: "), BorderLayout.NORTH);
		Vector<Student> students = new Vector<Student>(ms.getAllStudents());
		studentsList = new JList(students);
		right.add(new JScrollPane(studentsList), BorderLayout.CENTER);
		return right;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StudentsFrame sf = new StudentsFrame();
				sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
				sf.setVisible(true);
			}
		});
	}
}