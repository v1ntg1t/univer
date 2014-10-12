package students.frame;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import javax.swing.border.BevelBorder;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import students.logic.Group;
import students.logic.ManagementSystem;
import students.logic.Student;


public class StudentsFrame extends JFrame 
		implements ActionListener, ChangeListener, ListSelectionListener {

	private static final String MOVE_GROUP = "moveGroup";
	private static final String CLEAR_GROUP = "clearGroup";
	private static final String INSERT_STUDENT = "insertStudent";
	private static final String UPDATE_STUDENT = "updateStudent";
	private static final String DELETE_STUDENT = "deleteStudent";
	private static final String ALL_STUDENTS = "allStudents";


	ManagementSystem managementSystem = null;
	private JList<Group> groups;
	private JTable students;
	private JSpinner yearSpinner;
	
	
	public StudentsFrame() throws Exception {
		initFields();
		getContentPane().setLayout(new BorderLayout());
		addContent();
		setBounds(100, 100, 700, 500);
	}
	

	private void initFields() throws Exception {
		setManagementSystem();
		setGroups();
		initStudents();
		setYearSpinner();
	}
	
	private void setManagementSystem() throws Exception {
		managementSystem = ManagementSystem.getInstance();
	}
	
	private void setGroups() throws SQLException {
		List<Group> groups = managementSystem.getGroups();
		Vector<Group> groupsVector = new Vector<Group>(groups);
		this.groups = new JList<Group>(groupsVector);
		this.groups.setSelectedIndex(0);
		this.groups.addListSelectionListener(this);
	}
	
	private void initStudents() {
		this.students = new JTable(1, 4);
	}
	
	private void setStudents(Group group, int year) throws SQLException {
		Collection<Student> students = managementSystem.getStudents(group,year);
		Vector<Student> studentsVector = new Vector<Student>(students);
		this.students.setModel(new StudentsTableModel(studentsVector));
	}
	
	private void setYearSpinner() {
		SpinnerModel sm = new SpinnerNumberModel(2006, 1900, 2100, 1);
		yearSpinner = new JSpinner(sm);
		yearSpinner.addChangeListener(this);
	}
	
	private void addContent() {
		setJMenuBar(getReportMenuBar());
		getContentPane().add(getTopPanel(), BorderLayout.NORTH);
		getContentPane().add(getBotPanel(), BorderLayout.CENTER);
	}
	
	private JMenuBar getReportMenuBar() {
		JMenuBar reportMenuBar = new JMenuBar();
		reportMenuBar.add(getReportMenu());
		return reportMenuBar;
	}
	
	private JMenu getReportMenu() {
		JMenu reportMenu = new JMenu("Отчеты");
		reportMenu.add(getReportMenuItem());
		return reportMenu;
	}
	
	private JMenuItem getReportMenuItem() {
		JMenuItem reportMenuItem = new JMenuItem("Все студенты");
		reportMenuItem.setName(ALL_STUDENTS);
		reportMenuItem.addActionListener(this);
		return reportMenuItem;
	}
	
	private JPanel getTopPanel() {
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(new JLabel("Год обучения: "));
		top.add(yearSpinner);
		return top;
	}
	
	private JPanel getBotPanel() {
		JPanel bot = new JPanel();
		bot.setLayout( new BorderLayout() );
		bot.add(getGroupsPanel(), BorderLayout.WEST);
		bot.add(getStudentsPanel(), BorderLayout.CENTER);
		return bot;
	}
	
	private GroupsPanel getGroupsPanel() {
		GroupsPanel groupsPanel = new GroupsPanel();
		groupsPanel.setLayout( new BorderLayout() );
		groupsPanel.setBorder( new BevelBorder(BevelBorder.LOWERED) );
		groupsPanel.add( new JLabel("Группы: "), BorderLayout.NORTH );
		groupsPanel.add( new JScrollPane(groups), BorderLayout.CENTER );
		groupsPanel.add(getGroupsButtonPanel(), BorderLayout.SOUTH);
		return groupsPanel;
	}
	
	private JPanel getGroupsButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(getButton(MOVE_GROUP, "Переместить"));
		buttonPanel.add(getButton(CLEAR_GROUP, "Очистить"));
		return buttonPanel;
	}
	
	private JPanel getStudentsPanel() {
		JPanel studentsPanel = new JPanel();
		studentsPanel.setLayout( new BorderLayout() );
		studentsPanel.setBorder( new BevelBorder(BevelBorder.LOWERED) );
		studentsPanel.add( new JLabel("Студенты: "), BorderLayout.NORTH );
		studentsPanel.add( new JScrollPane(students), BorderLayout.CENTER );
		studentsPanel.add(getStudentsButtonPanel(), BorderLayout.SOUTH);
		return studentsPanel;
	}
	
	private JPanel getStudentsButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3));
		buttonPanel.add(getButton(INSERT_STUDENT, "Добавить"));
		buttonPanel.add(getButton(UPDATE_STUDENT, "Изменить"));
		buttonPanel.add(getButton(DELETE_STUDENT, "Удалить"));
		return buttonPanel;
	}
	
	private JButton getButton(String name, String label) {
		JButton button = new JButton(label);
		button.setName(name);
		button.addActionListener(this);
		return button;
	}
	
/*
	private static final String MOVE_GROUP = "moveGroup";
	private static final String CLEAR_GROUP = "clearGroup";
	private static final String INSERT_STUDENT = "insertStudent";
	private static final String UPDATE_STUDENT = "updateStudent";
	private static final String DELETE_STUDENT = "deleteStudent";
	private static final String ALL_STUDENTS = "allStudents";

*/	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof Component) {
			Component c = (Component)e.getSource();
			if(MOVE_GROUP.equals(c.getName())) {
				moveGroup();
			}
			if(CLEAR_GROUP.equals(c.getName())) {
				clearGroup();
			}
			if(ALL_STUDENTS.equals(c.getName())) {
				showAllStudents();
			}
			if(INSERT_STUDENT.equals(c.getName())) {
				insertStudent();
			}
			if(UPDATE_STUDENT.equals(c.getName())) {
				updateStudent();
			}
			if(DELETE_STUDENT.equals(c.getName())) {
				deleteStudent();
			}
		}
	}
	
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			reloadStudents();
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		reloadStudents();
	}

	
	private void reloadStudents() {
		Thread t = new Thread() {
			public void run() {
				if(students != null) {
					Group group = groups.getSelectedValue();
					SpinnerNumberModel model = null;
					model = (SpinnerNumberModel)yearSpinner.getModel();
					int year = model.getNumber().intValue();
					try {
						setStudents(group, year);
					} catch(SQLException e) {
						JOptionPane.showMessageDialog(
								StudentsFrame.this, e.getMessage()
						);
					}
				}
			}
		};
		t.start();
	}
	
	private void moveGroup() {
		System.out.println("moveGroup");
		JOptionPane.showMessageDialog(this, "moveGroup");
	}
	
	private void clearGroup() {
		Thread t = new Thread() {
			public void run() {
				Group group = groups.getSelectedValue();
				if(group != null) {
					if(		JOptionPane.showConfirmDialog(
									StudentsFrame.this,
									"Удалить студентов из группы?",
									"Удаление студентов из группы",
									JOptionPane.YES_NO_OPTION
							) == JOptionPane.YES_OPTION
					) {
						SpinnerNumberModel snm = null;
						snm = (SpinnerNumberModel)yearSpinner.getModel();
						int year = snm.getNumber().intValue();
						try {
							managementSystem.removeStudents(group, year);
							reloadStudents();
						} catch(SQLException e) {
							JOptionPane.showMessageDialog(
									StudentsFrame.this, 
									e.getMessage()
							);
						}
					}
				} else {
					JOptionPane.showMessageDialog(
							StudentsFrame.this, 
							"Выделите группу в списке"
					);
				}
			}
		};
		t.start();
	}
	
	private void showAllStudents() {
		System.out.println("showAllStudents");
		JOptionPane.showMessageDialog(this, "showAllStudents");
	}

	private void insertStudent() {
		System.out.println("insertStudent");
		JOptionPane.showMessageDialog(this, "insertStudent");
	}
	
	private void updateStudent() {
		System.out.println("updateStudent");
		JOptionPane.showMessageDialog(this, "updateStudent");
	}
	
	private void deleteStudent() {
		Thread t = new Thread() {
			public void run() {
				if(students != null) {
					StudentsTableModel stm = null;
					stm = (StudentsTableModel)students.getModel();
					int studentRow = students.getSelectedRow();
					if(studentRow >= 0) {
						if(		JOptionPane.showConfirmDialog(
										StudentsFrame.this, 
										"Удалить студента?",
										"Удаление студента",
										JOptionPane.YES_NO_OPTION
								) == JOptionPane.YES_OPTION
						) {
							Student student = stm.getStudent(studentRow);
							try {
								managementSystem.deleteStudent(student);
								reloadStudents();
							} catch(SQLException e) {
								JOptionPane.showMessageDialog(
										StudentsFrame.this, 
										e.getMessage()
								);
							}
						}
					} else {
						JOptionPane.showMessageDialog(
								StudentsFrame.this, 
								"Выделите студента в списке"
						);
					}
				}
			}
		};
		t.start();		
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentsFrame sf = new StudentsFrame();
					sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
					sf.setVisible(true);
					sf.reloadStudents();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

class GroupsPanel extends JPanel {
	public Dimension getPreferredSize() {
		return new Dimension(250, 0);
	}
}