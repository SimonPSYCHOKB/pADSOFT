package gui.panels;

import gui.panels.controllers.TablePopUp;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import users.Student;

/**
 * This class makes reference to the panel that contains the information
 * of the registered, pending and expelled students in a course.
 * 
 * @author Simon, Blanca
 *
 */
public class StudentsCourse extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTable tableR;
	private JTable tableE;
	private JMenuItem reject;
	private JMenuItem accept;
	private JTable tableP;
	private JMenuItem expel;
	private JMenuItem readmit;
	
	/**
	 * Constructor
	 * @param registered - list with the registered students in the course
	 * @param pending - list with the students that have pending applications in the course
	 * @param expelled - list with the expelled students in the course
	 */
	public StudentsCourse(List<Student> registered, List<Student> pending, List<Student> expelled){
		super(new FlowLayout());
		
		JTabbedPane root = new JTabbedPane();
		
		JPanel reg = new JPanel(new FlowLayout());
		JPanel pend = new JPanel(new FlowLayout());
		JPanel exp = new JPanel(new FlowLayout());
		
	
		Object[][] objs = new Object[registered.size()][1];
		String[] title = {"Title"};
		int i = 0;
		for(Student s : registered){
			objs[i][0] = s.getName();
			i += 1;
		}
		DefaultTableModel tableModel = new DefaultTableModel(objs, title) {

			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tableR = new JTable(tableModel);
		tableR.setTableHeader(null);
		JPopupMenu popup = new JPopupMenu();
		expel = new JMenuItem("Expel");
		popup.add(expel);
		popup.addPopupMenuListener(new TablePopUp(tableR, popup));
		tableR.setComponentPopupMenu(popup);

		
		objs = new Object[pending.size()][1];
		i = 0;
		for(Student s : pending){
			objs[i][0] = s.getName();
			i += 1;
		}
		tableModel = new DefaultTableModel(objs, title) {

			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tableP = new JTable(tableModel);
		tableP.setTableHeader(null);
		popup = new JPopupMenu();
		reject = new JMenuItem("Reject");
		accept = new JMenuItem("Accept");
		popup.add(reject);
		popup.add(accept);
		popup.addPopupMenuListener(new TablePopUp(tableP, popup));
		tableP.setComponentPopupMenu(popup);
		
		
		objs = new Object[expelled.size()][1];
		i = 0;
		for(Student s : expelled){
			objs[i][0] = s.getName();
			i += 1;
		}
		tableModel = new DefaultTableModel(objs, title) {

			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tableE = new JTable(tableModel);
		tableE.setTableHeader(null);
		popup = new JPopupMenu();
		readmit = new JMenuItem("Readmit");
		popup.add(readmit);
		popup.addPopupMenuListener(new TablePopUp(tableE, popup));
		tableE.setComponentPopupMenu(popup);
		
		JScrollPane scrollR = new JScrollPane(tableR);
		JScrollPane scrollP = new JScrollPane(tableP);
		JScrollPane scrollE = new JScrollPane(tableE);

		reg.add(scrollR);
		pend.add(scrollP);
		exp.add(scrollE);
		
		root.addTab("Registered", reg);
		root.addTab("Pending", pend);
		root.addTab("Expelled", exp);

		add(root);
		
		setVisible(true);
		
	}
	
	/**
	 * This method returns the table that contains the registered students
	 * @return table with the registered students
	 */
	public JTable getTableRegistered(){
		return tableR;
	}
	
	/**
	 * This method returns the table that contains the pending students
	 * @return table with the pending students
	 */
	public JTable getTablePending(){
		return tableP;
	}
	
	/**
	 * This method returns the table that contains the expelled students
	 * @return table with the expelled students
	 */
	public JTable getTableExpelled(){
		return tableE;
	}
	
	/**
	 * This method adds the controller to the accept option 
	 * @param al - the controller
	 */
	public void addControllerAccept(ActionListener al){
		accept.addActionListener(al);
	}
	
	/**
	 * This method adds the controller to the reject option 
	 * @param al - the controller
	 */
	public void addControllerReject(ActionListener al){
		reject.addActionListener(al);
	}
	
	/**
	 * This method adds the controller to the readmit option 
	 * @param al - the controller
	 */
	public void addControllerReadmit(ActionListener al){
		readmit.addActionListener(al);
	}
	
	/**
	 * This method adds the controller to the expel option 
	 * @param al - the controller
	 */
	public void addControllerExpel(ActionListener al){
		expel.addActionListener(al);
	}
	
}
