package GUI;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public abstract class General extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JMenu options;
	JMenuItem logOut;
	JMenuItem courses;
	JPanel central;
	JPanel top;
	
	public General() {
		super("Toodle");
		
		this.getContentPane().setLayout(new BorderLayout());
		
		SpringLayout layout = new SpringLayout();
		top = new JPanel();
		top.setLayout(layout);
		
		JMenuBar bar = new JMenuBar();
		bar.add(Box.createHorizontalGlue());
		
		options = new JMenu("User", true);
		options.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
		
		bar.add(options);
				
		courses = new JMenuItem("Courses");
		options.add(courses);
		logOut = new JMenuItem("Log out");
		options.add(logOut);
		
		setJMenuBar(bar);
		
		top.setBorder(BorderFactory.createLineBorder(getForeground(), 1));
		top.setPreferredSize(new Dimension(250, 50));
		top.setVisible(true);
		
		central = new JPanel();
		
		this.getContentPane().add(BorderLayout.NORTH, top);
		this.getContentPane().add(BorderLayout.WEST, new JPanel());
		
		this.setSize(1800, 1000);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void addPanel(JPanel p){
		central.setVisible(false);
		p.setVisible(true);
		central = p;
		this.getContentPane().add(BorderLayout.CENTER, p);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
//	public void addNotifications(JPanel p){
//		this.getContentPane().add(BorderLayout.WEST, p);
//		SwingUtilities.updateComponentTreeUI(this);
//	}
	
	public void addControllerLogOut(ActionListener al){
		logOut.addActionListener(al);
	}
	
	public void addControllerCourses(ActionListener al){
		courses.addActionListener(al);
	}
	
	public void addOption(JMenuItem mi){
		options.add(mi, 0);
	}
}
