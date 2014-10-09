package students.frame;


import java.util.Vector;

import java.awt.BorderLayout;
import java.awt.Container;
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


import students.logic.Group;
import students.logic.ManagementSystem;
import students.logic.Student;


public class StudentsFrame extends JFrame {

	ManagementSystem ms = null;
	private JList groups;
	private JList students;
	private JSpinner year;
	
	
	public StudentsFrame() throws Exception {
		Container root = getContentPane();
		root.setLayout(new BorderLayout());

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(new JLabel("Год обучения: "));
		SpinnerModel sm = new SpinnerNumberModel(2006, 1900, 2100, 1);
		year = new JSpinner(sm);
		top.add(year);
		root.add(top, BorderLayout.NORTH);
		
		ms = ManagementSystem.getInstance();
		JPanel bot = new JPanel();
		bot.setLayout( new BorderLayout() );
			JPanel left = new JPanel();
			left.setLayout( new BorderLayout() );
			left.setBorder( new BevelBorder(BevelBorder.RAISED) );
			left.add( new JLabel("Группы: "), BorderLayout.NORTH );
			Vector groups = new Vector<Group>( ms.getGroups() );
			this.groups = new JList(groups);
			left.add( new JScrollPane(this.groups), BorderLayout.CENTER );
			bot.add(left, BorderLayout.WEST);

			JPanel right = new JPanel();
			right.setLayout( new BorderLayout() );
			right.setBorder( new BevelBorder(BevelBorder.RAISED) );
			right.add( new JLabel("Студенты: "), BorderLayout.NORTH );
			Vector students = new Vector<Student>( ms.getAllStudents() );
			this.students = new JList(students);
			right.add( new JScrollPane(this.students), BorderLayout.CENTER );
			bot.add(right, BorderLayout.CENTER);
		root.add(bot, BorderLayout.CENTER);
		
		setBounds(100, 100, 600, 400);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentsFrame sf = new StudentsFrame();
					sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
					sf.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}